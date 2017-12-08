package xyz.kkt.padcprofessionalproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.contents.content;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;
import xyz.kkt.padcprofessionalproject.viewHolders.NewsViewHolder;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class NewsAdpater extends BaseRecycleAdapter<NewsViewHolder, NewsVO> {

    private NewsItemDelegate mNewsItemDelegate;

    public NewsAdpater(Context context, NewsItemDelegate newsItemDelegate) {
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = mLayoutInflater.inflate(R.layout.view_item_news, parent, false);
        return new NewsViewHolder(newsItemView, mNewsItemDelegate);
    }

}
