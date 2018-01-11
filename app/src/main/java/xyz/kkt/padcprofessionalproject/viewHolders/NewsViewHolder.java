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
    private NewsVO news;

    public NewsViewHolder(View itemView, NewsItemDelegate newsItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mNewsItemDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {
        news = data;

//        if (data.getBrief() != null) {
//            tvBriefNews.setText(data.getBrief());
//        }
//
//        if (data.getPublication() != null) {
//            if (data.getPublication().getTitle() != null) {
//                tvPublicationName.setText(data.getPublication().getTitle());
//            }
//            if (data.getPublication().getLogo() != null) {
//                Glide.with(ivPublicationLogo.getContext())
//                        .load(data.getPublication().getLogo())
//                        .into(ivPublicationLogo);
//            }
//        }
//
//
//        if (data.getPostedDate() != null) {
//            tvPublishDate.setText(data.getPostedDate());
//        }
//
//        int like = 0;
//        int comment = 0;
//        int sentTo = 0;
//
//
//        if (data.getFavoriteActions() != null)
//
//        {
//            like = data.getFavoriteActions().size();
//        }
//
//        if (data.getComments() != null)
//
//        {
//            comment = data.getComments().size();
//        }
//
//        if (data.getSendTos() != null)
//
//        {
//            sentTo = data.getSendTos().size();
//        }
//
//        tvNewsStatisticalData.setText(like + " Likes- " + comment + " Comments - Send to " + sentTo + " people");
//
//        if (data.getImages() != null) {
//            if (!data.getImages().isEmpty()) {
//                Glide.with(ivNewsHeroImage.getContext())
//                        .load(data.getImages().get(0))
//                        .into(ivNewsHeroImage);
//            } else {
//                ivNewsHeroImage.setVisibility(View.GONE);
//            }
//
//        }
        if (data != null) {

            if (data.getPublication().getLogo() != null) {
                ivPublicationLogo.setVisibility(View.VISIBLE);
                Glide
                        .with(ivPublicationLogo.getContext())
                        .load(data.getPublication().getLogo())
                        .into(ivPublicationLogo);
            } else {
                ivPublicationLogo.setVisibility(View.GONE);
            }

            if (data.getPublication().getTitle() != null) {
                tvPublicationName.setVisibility(View.VISIBLE);
                tvPublicationName.setText(data.getPublication().getTitle());
            } else {
                tvPublicationName.setVisibility(View.GONE);
            }

            if (data.getPostedDate() != null) {
                tvPublishDate.setVisibility(View.VISIBLE);
                tvPublishDate.setText(data.getPostedDate());
            } else {
                tvPublishDate.setVisibility(View.GONE);
            }

            if (data.getBrief() != null) {
                tvBriefNews.setVisibility(View.VISIBLE);
                tvBriefNews.setText(data.getBrief());
            } else {
                tvBriefNews.setVisibility(View.GONE);
            }

            if (!data.getImages().isEmpty()) {
                ivNewsHeroImage.setVisibility(View.VISIBLE);
                Glide
                        .with(ivNewsHeroImage.getContext())
                        .load(data.getImages().get(0))
                        .into(ivNewsHeroImage);
            } else {
                ivNewsHeroImage.setVisibility(View.GONE);
            }

            String newsStatistics = "";
            if (data.getFavoriteActions() != null) {
                newsStatistics += String.valueOf(data.getFavoriteActions().size()) + " likes - ";
            } else {
                newsStatistics += "0 likes - ";
            }
            if (data.getComments() != null) {
                newsStatistics += String.valueOf(data.getComments().size()) + " comments - ";
            } else {
                newsStatistics += "0 comments - ";
            }
            if (data.getSendTos() != null) {
                newsStatistics += "Send to " + String.valueOf(data.getSendTos().size()) + " people";
            } else {
                newsStatistics += "Send to 0 people";
            }
            tvNewsStatisticalData.setText(newsStatistics);
        }
    }

    @Override
    public void onClick(View view) {
        mNewsItemDelegate.onTapNews(news);

        EventBus.getDefault().post(new TapNewsEvent());
    }
}
