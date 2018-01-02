package xyz.kkt.padcprofessionalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.adapters.NewsAdpater;
import xyz.kkt.padcprofessionalproject.adapters.NewsImagePagerAdapter;
import xyz.kkt.padcprofessionalproject.adapters.RelatedNewsAdapter;
import xyz.kkt.padcprofessionalproject.contents.content;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;

/**
 * Created by Lenovo on 11/11/2017.
 */

public class NewsDetailActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.rv_related_news)
    RecyclerView rvRelatedNews;

    @BindView(R.id.tv_news_detail)
    TextView tvNewsDetail;

    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;

    @BindView(R.id.tv_publish_date)
    TextView tvPublishDate;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;


    RelatedNewsAdapter mRelatedNewsAdapter;
    NewsImagePagerAdapter newsImagePagerAdapter;

    private static final String IE_NEWS_ID = "IE_NEWS_ID";
    private static final int NEWS_DETAILS_LOADER_ID = 1002;


    private String mNewsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this, this);

        newsImagePagerAdapter = new NewsImagePagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagePagerAdapter);

        rvRelatedNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mRelatedNewsAdapter = new RelatedNewsAdapter(getApplicationContext());
        rvRelatedNews.setAdapter(mRelatedNewsAdapter);

        mNewsId = getIntent().getStringExtra(IE_NEWS_ID);
        if (TextUtils.isEmpty(mNewsId)) {
            throw new UnsupportedOperationException("newsId required for NewsDetailsActivity");
        } else {
            getSupportLoaderManager().initLoader(NEWS_DETAILS_LOADER_ID, null, this);
        }

    }

    public static Intent newIntent(Context context, String newsId) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(IE_NEWS_ID, newsId);
        return intent;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MMNewsContract.NewsEntry.CONTENT_URI,
                null,
                MMNewsContract.NewsEntry.COLUMN_NEWS_ID + " = ?", new String[]{mNewsId},
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            NewsVO news = NewsVO.parseFromCursor(getApplicationContext(), data);
            bindData(news);
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void bindData(NewsVO news) {
        tvNewsDetail.setText(news.getDetails());
        tvPublicationName.setText(news.getPublication().getTitle());
        tvPublishDate.setText(news.getPostedDate());

        Glide
                .with(ivPublicationLogo.getContext())
                .load(news.getPublication().getLogo())
                .into(ivPublicationLogo);

        newsImagePagerAdapter.setImages(news.getImages());
    }

}
