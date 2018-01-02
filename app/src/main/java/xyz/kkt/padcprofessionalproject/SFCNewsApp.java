package xyz.kkt.padcprofessionalproject;

import android.app.Application;

import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.utils.ConfigUtils;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class SFCNewsApp extends Application {

    public static final String LOG_TAG = "SFCNewsApp";

    @Override
    public void onCreate() {
        super.onCreate();
        ConfigUtils.initConfigUtils(getApplicationContext());
        NewsModel.getInstance().startLoadingMMNews(getApplicationContext());
    }

}
