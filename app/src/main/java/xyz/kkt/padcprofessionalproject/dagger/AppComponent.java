package xyz.kkt.padcprofessionalproject.dagger;

import javax.inject.Singleton;

import dagger.Component;
import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.activities.AddNewsActivity;
import xyz.kkt.padcprofessionalproject.activities.NewsListActivity;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.mvp.presenters.AddNewsPresenter;
import xyz.kkt.padcprofessionalproject.mvp.presenters.NewsListPresenter;
import xyz.kkt.padcprofessionalproject.dagger.NetworkModule;

/**
 * Created by Lenovo on 1/6/2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(SFCNewsApp app);

    void inject(NewsModel newsModel);

    void inject(NewsListPresenter newsListPresenter);

    void inject(NewsListActivity newsListActivity);

    void inject(AddNewsPresenter addNewsPresenter);

    void inject(AddNewsActivity addNewsActivity);

}
