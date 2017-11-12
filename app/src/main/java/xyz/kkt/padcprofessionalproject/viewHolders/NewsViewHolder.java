package xyz.kkt.padcprofessionalproject.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private NewsItemDelegate mNewsItemDelegate;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mNewsItemDelegate = newsItemDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsItemDelegate.onTapNews();
            }
        });
    }
}
