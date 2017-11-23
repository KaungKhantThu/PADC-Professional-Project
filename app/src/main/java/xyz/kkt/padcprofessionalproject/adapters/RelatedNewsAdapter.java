package xyz.kkt.padcprofessionalproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;
import xyz.kkt.padcprofessionalproject.viewHolders.NewsViewHolder;
import xyz.kkt.padcprofessionalproject.viewHolders.RelatedNewsViewHolder;

/**
 * Created by Lenovo on 11/23/2017.
 */

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsViewHolder> {

    private LayoutInflater mLayoutInflater;

    public RelatedNewsAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RelatedNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View relatedNewsItemView = mLayoutInflater.inflate(R.layout.view_item_related_news, parent, false);
        return new RelatedNewsViewHolder(relatedNewsItemView);
    }

    @Override
    public void onBindViewHolder(RelatedNewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
