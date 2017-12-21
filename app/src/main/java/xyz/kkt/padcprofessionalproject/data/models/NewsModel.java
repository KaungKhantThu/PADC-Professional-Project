package xyz.kkt.padcprofessionalproject.data.models;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.events.RestApiEvents;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgentImpl;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsProvider;
import xyz.kkt.padcprofessionalproject.utils.AppConstants;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private List<NewsVO> mNews;

    private int mmNewsPageIndex = 1;



    private NewsModel() {
        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();
    }

    public static NewsModel getInstance() {
        if (objInstance == null) {
            objInstance = new NewsModel();
        }
        return objInstance;
    }

    public void startLoadingMMNews(Context context) {
        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex, context);
    }

    public List<NewsVO> getNews() {
        return mNews;
    }

    public void loadMoreNews(Context context) {
        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex, context);
    }

    public void forceRefreshNews(Context context) {
        mNews = new ArrayList<>();
        mmNewsPageIndex = 1;
        startLoadingMMNews(context);
    }

    @Subscribe
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        mmNewsPageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] newsCVs = new ContentValues[event.getLoadNews().size()];
        for (int index = 0; index < newsCVs.length; index++) {
            newsCVs[index] = event.getLoadNews().get(index).parseToContentValues();
        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(MMNewsContract.NewsEntry.CONTENT_URI,
                newsCVs);
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedRows);
    }
}
