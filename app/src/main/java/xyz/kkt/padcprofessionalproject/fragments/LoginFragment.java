package xyz.kkt.padcprofessionalproject.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.activities.LoginRegisterActivity;
import xyz.kkt.padcprofessionalproject.delegates.LoginRegisterDelegate;

/**
 * Created by Lenovo on 11/26/2017.
 */

public class LoginFragment extends Fragment {

    private LoginRegisterDelegate mLoginRegisterDelegate;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);//context is the host activity(LoginRegisterActivity)
        mLoginRegisterDelegate = (LoginRegisterDelegate) context;

    }

    @Override
    public void onStart() {
        super.onStart();
        mLoginRegisterDelegate.setScreenTitile("Login");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, loginView);
        return loginView;
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view) {
        mLoginRegisterDelegate.onTapToRegister();
    }
}
