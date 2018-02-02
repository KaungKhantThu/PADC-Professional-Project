package xyz.kkt.padcprofessionalproject.mvp.views;

import android.content.Context;

/**
 * Created by Lenovo on 1/28/2018.
 */

public interface AddNewsView {

    Context getContext();

    void showUploadedNewsPhoto(String photoPath);

    void showErrorMessage(String msg);
}
