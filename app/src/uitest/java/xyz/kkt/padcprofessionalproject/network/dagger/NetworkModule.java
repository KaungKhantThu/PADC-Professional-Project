package xyz.kkt.padcprofessionalproject.network.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.padcprofessionalproject.network.MMNewsDataAgent;
import xyz.kkt.padcprofessionalproject.network.UITestDataAgentImpl;

/**
 * Created by Lenovo on 1/10/2018.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public MMNewsDataAgent provideMMNewsDataAgent(Context context) {
        return new UITestDataAgentImpl();
    }

}
