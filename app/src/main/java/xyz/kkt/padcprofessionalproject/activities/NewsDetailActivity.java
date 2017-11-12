package xyz.kkt.padcprofessionalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.adapters.NewsImagePagerAdapter;

/**
 * Created by Lenovo on 11/11/2017.
 */

public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this, this);

        NewsImagePagerAdapter newsImagePagerAdapter = new NewsImagePagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagePagerAdapter);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        return intent;
    }
}
