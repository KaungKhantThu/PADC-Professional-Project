package xyz.kkt.padcprofessionalproject.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.mvp.presenters.NewsListPresenter;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgent;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgentImpl;

/**
 * Created by Lenovo on 1/6/2018.
 */
@Module
public class AppModule {

    private SFCNewsApp mApp;

    public AppModule(SFCNewsApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public NewsModel provideNewsModel(Context context) {
        return new NewsModel(context);
    }

    @Provides
    public NewsListPresenter provideNewsListPresenter() {
        return new NewsListPresenter();
    }

}
