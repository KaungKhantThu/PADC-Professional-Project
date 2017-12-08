package xyz.kkt.padcprofessionalproject.delegates;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lenovo on 11/26/2017.
 */

public interface LoginRegisterDelegate{
    void onTapLogin();

    void onTapForgotPassword();

    void onTapToRegister();

    void setScreenTitile(String title);
}
