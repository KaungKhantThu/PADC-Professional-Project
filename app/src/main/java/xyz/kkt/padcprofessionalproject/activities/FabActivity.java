package xyz.kkt.padcprofessionalproject.activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.kkt.padcprofessionalproject.R;

/**
 * Created by Lenovo on 11/29/2017.
 */

public class FabActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.fab)
    public void movingFab() {

        ObjectAnimator animX = ObjectAnimator.ofFloat(fab, "x", 50f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(fab, "y", 100f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.setDuration(500);
        animSetXY.setInterpolator(new DecelerateInterpolator());
        animSetXY.playTogether(animX, animY);
        animSetXY.start();

    }


}
