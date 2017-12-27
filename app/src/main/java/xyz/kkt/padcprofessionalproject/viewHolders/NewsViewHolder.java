package xyz.kkt.padcprofessionalproject.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.padcprofessionalproject.R;
import xyz.kkt.padcprofessionalproject.contents.content;
import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;
import xyz.kkt.padcprofessionalproject.delegates.NewsItemDelegate;
import xyz.kkt.padcprofessionalproject.events.TapNewsEvent;

/**
 * Created by Lenovo on 11/4/2017.
 */

public class NewsViewHolder extends BaseViewHolder<NewsVO> {
    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;
    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;
    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;
    @BindView(R.id.tv_publish_date)
    TextView tvPublishDate;
    @BindView(R.id.iv_news_hero_image)
    ImageView ivNewsHeroImage;
    @BindView(R.id.tv_news_statistical_data)
    TextView tvNewsStatisticalData;

    private NewsItemDelegate mNewsItemDelegate;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mNewsItemDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {


        if (data.getBrief() != null) {
            tvBriefNews.setText(data.getBrief());
        }

        if (data.getPublication() != null) {
            if (data.getPublication().getTitle() != null) {
                tvPublicationName.setText(data.getPublication().getTitle());
            }
            if (data.getPublication().getLogo() != null) {
                Glide.with(ivPublicationLogo.getContext())
                        .load(data.getPublication().getLogo())
                        .into(ivPublicationLogo);
            }
        }


        if (data.getPostedDate() != null) {
            tvPublishDate.setText(data.getPostedDate());
        }

        int like = 0;
        int comment = 0;
        int sentTo = 0;


        if (data.getFavoriteActions() != null)

        {
            like = data.getFavoriteActions().size();
        }

        if (data.getComments() != null)

        {
            comment = data.getComments().size();
        }

        if (data.getSendTos() != null)

        {
            sentTo = data.getSendTos().size();
        }

        tvNewsStatisticalData.setText(like + " Likes- " + comment + " Comments - Send to " + sentTo + " people");

        if (data.getImages() != null) {
            if (!data.getImages().isEmpty()) {
                Glide.with(ivNewsHeroImage.getContext())
                        .load(data.getImages().get(0))
                        .into(ivNewsHeroImage);
            } else {
                ivNewsHeroImage.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public void onClick(View view) {
        mNewsItemDelegate.onTapNews();

        EventBus.getDefault().post(new TapNewsEvent());
    }
}
