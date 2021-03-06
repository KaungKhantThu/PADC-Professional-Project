package xyz.kkt.padcprofessionalproject.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.SFCNewsApp;
import xyz.kkt.padcprofessionalproject.adapters.NewsAdpater;
import xyz.kkt.padcprofessionalproject.components.EmptyViewPod;
import xyz.kkt.padcprofessionalproject.components.SmartRecyclerView;
import xyz.kkt.padcprofessionalproject.components.SmartScrollListener;
import xyz.kkt.padcprofessionalproject.data.models.NewsModel;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;
import xyz.kkt.padcprofessionalproject.events.RestApiEvents;
import xyz.kkt.padcprofessionalproject.events.TapNewsEvent;
import xyz.kkt.padcprofessionalproject.mvp.presenters.NewsListPresenter;
import xyz.kkt.padcprofessionalproject.mvp.views.NewsListView;
import xyz.kkt.padcprofessionalproject.network.persistence.MMNewsContract;
import xyz.kkt.padcprofessionalproject.viewitems.NewsDetailsImageViewItem;

public class NewsListActivity extends BaseActivity implements
        LoaderManager.LoaderCallbacks<Cursor>,
        NewsListView, GoogleApiClient.OnConnectionFailedListener {

    private static final int NEWS_LIST_LOADER_ID = 1;
    protected static final int RC_GOOGLE_SIGN_IN = 1236;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.rv_news_list)
    SmartRecyclerView rvNews;

    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;

    @BindView(R.id.swipe_refreshing_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdpater mNewsAdpater;

    private SmartScrollListener mSmartScrollListener;

    protected GoogleApiClient mGoogleApiClient;

    @Inject
    NewsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this, this);

        SFCNewsApp sfcNewsApp = (SFCNewsApp) getApplicationContext();
        sfcNewsApp.getSFCAppComponent().inject(this);

        //mPresenter = new NewsListPresenter();
        mPresenter.onCreate(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvNews.setEmptyView(vpEmptyNews);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mNewsAdpater = new NewsAdpater(getApplicationContext(), mPresenter);
        rvNews.setAdapter(mNewsAdpater);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvNews, "Loading new data.", Snackbar.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(true);

                mPresenter.onNewsListEndReach(getApplicationContext());
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onForceRefresh(getApplicationContext());
            }
        });

        rvNews.addOnScrollListener(mSmartScrollListener);

        getSupportLoaderManager().initLoader(NEWS_LIST_LOADER_ID, null, this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1065495677892-0apm4e0av08bsc78ak879kjdbsuh8esh.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this /*FragmentActivity*/, this /*OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            processGoogleSignInResult(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvNews, event.getErrorMsg(), BaseTransientBottomBar.LENGTH_INDEFINITE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(),
                MMNewsContract.NewsEntry.CONTENT_URI,
                null, null,
                null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        /*
        don't close cursor because it needs to be refreshed
        when the new data is loaded from network
         */
        mPresenter.onDataLoaded(getApplicationContext(), data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public void displayNewsList(List<NewsVO> newsList) {
        mNewsAdpater.setNewData(newsList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setTrueSwipeRefreshLayout() {
        swipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void navigateToNewsDetails(NewsVO newsVO) {
        Intent intent = NewsDetailActivity.newIntent(getApplicationContext(), newsVO.getNewsId());
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showAddNewsScreen() {
        Intent intent = AddNewsActivity.newIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), "onConnectionFailed : " + connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.fab)
    public void onTapFab(View view) {
        //signInWithGoogle();
        mPresenter.onStartPublishingNews();
    }

    private void processGoogleSignInResult(GoogleSignInResult signInResult) {
        if (signInResult.isSuccess()) {
            // Google Sign-In was successful, authenticate with Firebase
            GoogleSignInAccount account = signInResult.getSignInAccount();
            mPresenter.onSuccessGoogleSignIn(account);
        } else {
            // Google Sign-In failed
            Log.e(SFCNewsApp.LOG_TAG, "Google Sign-In failed.");
            Snackbar.make(swipeRefreshLayout, "Your Google sign-in failed.", Snackbar.LENGTH_LONG).show();
        }
    }
}
