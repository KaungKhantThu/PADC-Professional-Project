package xyz.kkt.padcprofessionalproject.data.models;


import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.data.vo.FavoriteActionVO;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.data.vo.PublicationVO;
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        mmNewsPageIndex = event.getLoadedPageIndex() + 1;

        //TODO Logic to save the data in Persistence Layer

        ContentValues[] newsCVs = new ContentValues[event.getLoadNews().size()];
        List<ContentValues> publicationCVList = new ArrayList<>();
        List<ContentValues> imagesInNewsCVList = new ArrayList<>();
        List<ContentValues> favoriteActionCVList = new ArrayList<>();
        List<ContentValues> usersInActionCVList = new ArrayList<>();

        for (int index = 0; index < newsCVs.length; index++) {
            NewsVO news = event.getLoadNews().get(index);
            newsCVs[index] = news.parseToContentValues();

            PublicationVO publication = news.getPublication();
            publicationCVList.add(publication.parseToContentValues());

            for (String imageUrl : news.getImages()) {
                ContentValues imagesInNewsCV = new ContentValues();
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGES_NEWS_ID, news.getNewsId());
                imagesInNewsCV.put(MMNewsContract.ImagesInNewsEntry.COLUMN_IMAGE_URL, imageUrl);
                imagesInNewsCVList.add(imagesInNewsCV);
            }

            for (FavoriteActionVO favoriteAction : news.getFavoriteActions()) {
                ContentValues favoriteActionCV = favoriteAction.parseToContentValues(news.getNewsId());
                favoriteActionCVList.add(favoriteActionCV);

                ContentValues usersInActionCV = favoriteAction.getActedUser().parseToContentValues();
                usersInActionCVList.add(usersInActionCV);
            }

        }

        int insertedRows = event.getContext().getContentResolver().bulkInsert(MMNewsContract.NewsEntry.CONTENT_URI,
                newsCVs);
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedRows);

        int insertedPublications = event.getContext().getContentResolver().bulkInsert(MMNewsContract.PublicationEntry.CONTENT_URI,
                publicationCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedPublications);

        int insertedImagesInNews = event.getContext().getContentResolver().bulkInsert(MMNewsContract.ImagesInNewsEntry.CONTENT_URI,
                imagesInNewsCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedImagesInNews);

        int insertedFavoriteAction = event.getContext().getContentResolver().bulkInsert(MMNewsContract.FavoriteActionEntry.CONTENT_URI,
                favoriteActionCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedFavoriteAction);

        int insertedUsersInAction = event.getContext().getContentResolver().bulkInsert(MMNewsContract.ActedUserEntry.CONTENT_URI,
                usersInActionCVList.toArray(new ContentValues[0]));
        Log.d(SFCNewsApp.LOG_TAG, "Inserted Rows : " + insertedUsersInAction);

    }
}
