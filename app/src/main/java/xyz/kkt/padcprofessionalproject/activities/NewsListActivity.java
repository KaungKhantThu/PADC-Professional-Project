package xyz.kkt.padcprofessionalproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.adapters.NewsAdpater;
import xyz.kkt.padcprofessionalproject.components.EmptyViewPod;
import xyz.kkt.padcprofessionalproject.components.SmartRecyclerView;
import xyz.kkt.padcprofessionalproject.components.SmartScrollListener;
import xyz.kkt.padcprofessionalproject.contents.content;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;

public class NewsListActivity extends AppCompatActivity implements NewsItemDelegate {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.rv_news_list)
    SmartRecyclerView rvNews;

    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;

    private SmartScrollListener mSmartScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.openDrawer(GravityCompat.START);
                Intent intent = LoginRegisterActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        rvNews.setEmptyView(vpEmptyNews);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        NewsAdpater newsAdpater = new NewsAdpater(getApplicationContext(), this);
        rvNews.setAdapter(newsAdpater);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvNews, "This is all the data for Now.", Snackbar.LENGTH_LONG).show();
            }
        });

        rvNews.addOnScrollListener(mSmartScrollListener);
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
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatics() {

    }

    @Override
    public void onTapNews(content content) {
        Intent intent = NewsDetailActivity.newIntent(getApplicationContext(), content);
        startActivity(intent);
    }
}
