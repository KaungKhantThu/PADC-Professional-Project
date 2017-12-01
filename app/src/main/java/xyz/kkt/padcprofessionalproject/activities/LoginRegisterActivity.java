package xyz.kkt.padcprofessionalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.delegates.LoginRegisterDelegate;
import xyz.kkt.padcprofessionalproject.fragments.LoginFragment;
import xyz.kkt.padcprofessionalproject.fragments.RegisterFragment;

/**
 * Created by Lenovo on 11/26/2017.
 */

public class LoginRegisterActivity extends AppCompatActivity implements LoginRegisterDelegate {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, LoginFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onTapLogin() {

    }

    @Override
    public void onTapForgotPassword() {

    }

    @Override
    public void onTapToRegister() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fl_container, RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setScreenTitile(String titile) {
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(titile);
    }
}
