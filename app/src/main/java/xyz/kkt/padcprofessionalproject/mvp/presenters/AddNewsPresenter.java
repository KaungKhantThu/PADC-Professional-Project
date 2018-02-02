package xyz.kkt.padcprofessionalproject.mvp.presenters;

import javax.inject.Inject;

import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.mvp.views.AddNewsView;

/**
 * Created by Lenovo on 1/28/2018.
 */

public class AddNewsPresenter extends BasePresenter<AddNewsView> {

    @Inject
    NewsModel mNewsModel;

    @Override
    public void onCreate(AddNewsView view) {
        super.onCreate(view);
        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public void onTapPublish(String photoPath, String newsContent) {
        mNewsModel.publishNews(photoPath, newsContent);
        /*
        mNewsModel.uploadFile(photoPath, new NewsModel.UploadFileCallback() {
            @Override
            public void onUploadSucceeded(String uploadedPaths) {
                mView.showUploadedNewsPhoto(uploadedPaths);
            }

            @Override
            public void onUploadFailed(String msg) {
                mView.showErrorMsg(msg);
            }
        });
        */
    }

}
