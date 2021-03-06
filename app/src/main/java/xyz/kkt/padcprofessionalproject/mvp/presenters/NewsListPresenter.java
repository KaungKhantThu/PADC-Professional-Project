package xyz.kkt.padcprofessionalproject.mvp.presenters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.activities.NewsDetailActivity;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;
import xyz.kkt.padcprofessionalproject.mvp.views.NewsListView;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {

    @Inject
    NewsModel mNewsModel;
    //private NewsListView mView;

    public NewsListPresenter() {

    }

    @Override
    public void onCreate(NewsListView view) {
        super.onCreate(view);
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {
//        List<NewsVO> newsList = mNewsModel.getNews();
//        if (!newsList.isEmpty()) {
//            mView.displayNewsList(newsList);
//        } else {
//            mView.setTrueSwipeRefreshLayout();
//        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    public void onNewsListEndReach(Context context) {
        mNewsModel.loadMoreNews(context);
    }

    public void onForceRefresh(Context context) {
        mNewsModel.forceRefreshNews(context);
    }

    public void onDataLoaded(Context context, Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<NewsVO> newsList = new ArrayList<>();
            do {
                NewsVO news = NewsVO.parseFromCursor(context, data);
                newsList.add(news);
            } while (data.moveToNext());
            {
                mView.displayNewsList(newsList);
            }

        }
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatics() {

    }

    @Override
    public void onTapNews(NewsVO newsVO) {
        mView.navigateToNewsDetails(newsVO);
    }

    public void onSuccessGoogleSignIn(GoogleSignInAccount signInAccount) {
        mNewsModel.authenticateUserWithGoogleAccount(signInAccount, new NewsModel.UserAuthenticateDelegate() {
            @Override
            public void onSuccessAuthenticate(GoogleSignInAccount account) {
                Log.d(SFCNewsApp.LOG_TAG, "onSuccessAuthenticate : " + account.getDisplayName());
            }

            @Override
            public void onFailureAuthenticate(String errorMsg) {
                Log.d(SFCNewsApp.LOG_TAG, "onFailureAuthenticate : " + errorMsg);
            }
        });
    }

    public void onStartPublishingNews() {
        if (mNewsModel.isUserAuthenticate()) {
            mView.showAddNewsScreen();
        } else {
            mView.signInGoogle();
        }
    }

}
