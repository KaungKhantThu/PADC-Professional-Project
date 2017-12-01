package xyz.kkt.padcprofessionalproject.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.contents.content;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;
    private NewsItemDelegate mNewsItemDelegate;

    private content mContent;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mNewsItemDelegate = newsItemDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsItemDelegate.onTapNews(mContent);
            }
        });
    }

    public void bind(content data) {
        tvBriefNews.setText(data.get(content.Field.SHORTNEWS));
        mContent = data;
    }

}
