package xyz.kkt.padcprofessionalproject.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.utils.AppConstants;

/**
 * Created by Lenovo on 11/11/2017.
 */

public class NewsImagePagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private ImageView iv;
    private List<String> images;

    public NewsImagePagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        iv = (ImageView) mLayoutInflater.inflate(R.layout.view_item_news_details_image, container, false);
        Glide
                .with(iv.getContext())
                .load(images.get(position))
                .into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    public void setImages(List<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }
}
