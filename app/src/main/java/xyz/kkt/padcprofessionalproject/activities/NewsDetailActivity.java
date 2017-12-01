package xyz.kkt.padcprofessionalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.adapters.NewsAdpater;
import xyz.kkt.padcprofessionalproject.adapters.NewsImagePagerAdapter;
import xyz.kkt.padcprofessionalproject.adapters.RelatedNewsAdapter;
import xyz.kkt.padcprofessionalproject.contents.content;

/**
 * Created by Lenovo on 11/11/2017.
 */

public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.rv_related_news)
    RecyclerView rvRelatedNews;

    @BindView(R.id.tv_news_detail)
    TextView tvNewsDetail;

    RelatedNewsAdapter mRelatedNewsAdapter;

    public final static String ID = "ID";
    public content mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this, this);

        NewsImagePagerAdapter newsImagePagerAdapter = new NewsImagePagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagePagerAdapter);

        rvRelatedNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mRelatedNewsAdapter = new RelatedNewsAdapter(getApplicationContext());
        rvRelatedNews.setAdapter(mRelatedNewsAdapter);

        Intent startIntent = getIntent();
        Bundle bundle = startIntent.getExtras();
        if (bundle != null) {
            String Id = bundle.getString(ID);
            mContent = content.getItem(Id);

            bindData(mContent);
        }
    }

    private void bindData(content mContent) {
        tvNewsDetail.setText(mContent.get(content.Field.DETAILNEWS));
    }

    public static Intent newIntent(Context context, content content) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.ID, content.getId());
        return intent;
    }
}
