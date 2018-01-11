package xyz.kkt.padcprofessionalproject.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.padcprofessionalproject.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/9/2018.
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    public ConfigUtils provideConfigUtils(Context context) {
        return new ConfigUtils(context);
    }
}
