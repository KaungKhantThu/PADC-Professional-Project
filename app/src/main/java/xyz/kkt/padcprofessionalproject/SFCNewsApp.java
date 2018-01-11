package xyz.kkt.padcprofessionalproject;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import xyz.kkt.padcprofessionalproject.dagger.AppComponent;
import xyz.kkt.padcprofessionalproject.dagger.AppModule;
import xyz.kkt.padcprofessionalproject.dagger.DaggerAppComponent;
import xyz.kkt.padcprofessionalproject.dagger.NetworkModule;
import xyz.kkt.padcprofessionalproject.dagger.UtilsModule;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.utils.ConfigUtils;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class SFCNewsApp extends Application {


    public static final String LOG_TAG = "SFCNewsApp";

    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate() {
        super.onCreate();
       // ConfigUtils.initConfigUtils(getApplicationContext());

        mAppComponent = initDagger();//dagger init
        mAppComponent.inject(this); //register consumer

        Log.d(LOG_TAG, "mContext : " + mContext);

        mNewsModel.startLoadingMMNews(getApplicationContext());


    }

    private AppComponent initDagger() {
        //return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getSFCAppComponent() {
        return mAppComponent;
    }

}
