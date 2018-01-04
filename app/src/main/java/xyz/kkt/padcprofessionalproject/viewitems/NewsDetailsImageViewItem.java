package xyz.kkt.padcprofessionalproject.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;

/**
 * Created by Lenovo on 1/4/2018.
 */

public class NewsDetailsImageViewItem extends FrameLayout {

    @BindView(R.id.iv_news_details_image)
    ImageView ivNewsDetailsImage;

    public NewsDetailsImageViewItem(@NonNull Context context) {
        super(context);
    }

    public NewsDetailsImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsDetailsImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewsDetailsImageViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setData(String imageUrl) {
        Glide.with(ivNewsDetailsImage.getContext())
                .load(imageUrl)
                .into(ivNewsDetailsImage);
    }

}
