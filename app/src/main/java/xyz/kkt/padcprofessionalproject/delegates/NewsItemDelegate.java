package xyz.kkt.padcprofessionalproject.delegates;

import xyz.kkt.padcprofessionalproject.contents.content;

/**
 * Created by Lenovo on 11/11/2017.
 */

public interface NewsItemDelegate {
    void onTapComment();

    void onTapSendTo();

    void onTapFavorite();

    void onTapStatics();

    void onTapNews(content content);
}
