package xyz.kkt.padcprofessionalproject.mvp.views;

import android.content.Context;

import java.util.List;

import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;

/**
 * Created by Lenovo on 1/6/2018.
 */

public interface NewsListView {

    void displayNewsList(List<NewsVO> newsList);

    void setTrueSwipeRefreshLayout();

    void navigateToNewsDetails(NewsVO newsVO);

    void showAddNewsScreen();

    void signInGoogle();

    Context getContext();

}
