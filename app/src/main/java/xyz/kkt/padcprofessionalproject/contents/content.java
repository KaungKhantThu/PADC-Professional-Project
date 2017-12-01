package xyz.kkt.padcprofessionalproject.contents;

import android.text.TextUtils;
import android.util.Log;

import xyz.kkt.padcprofessionalproject.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Lenovo on 11/26/2017.
 */

public class content {

    // The fields associated to the person
    private final String mId, mShortNews, mDetailNews;

    content(String Id, String shortNews, String detailNews) {
        mId = Id;
        mShortNews = shortNews;
        mDetailNews = detailNews;
    }

    public static final content[] CONTENTS = new content[]{
            new content("1", "Hello vvjdlkvjeslvjiojvslkvmslv", "Hello vvjdlkvjeslvjiojvslkvmslvHello vvjdlkvjeslvjiojvslkvmslvHello vvjdlkvjeslvjiojvslkvmslvHello vvjdlkvjeslvjiojvslkvmslv"),
            new content("2", "Hi a ljaljflksdjviojaevklmsllvjmaklevmelvmvm", "Hi a ljaljflksdjviojaevklmsllvjmaklevmelvmvmHi a ljaljflksdjviojaevklmsllvjmaklevmelvmvmHi a ljaljflksdjviojaevklmsllvjmaklevmelvmvmHi a ljaljflksdjviojaevklmsllvjmaklevmelvmvm"),
            new content("3", "Yo avaovj;lvjd;vpakdv", "Yo avaovj;lvjd;vpakdvYo avaovj;lvjd;vpakdvYo avaovj;lvjd;vpakdvYo avaovj;lvjd;vpakdvYo avaovj;lvjd;vpakdvYo avaovj;lvjd;vpakdv"),

    };

    public static content getItem(String id) {
        for (content item : CONTENTS) {
            if (TextUtils.equals(item.getId(), id))
                return item;
        }
        return null;

    }

    public String getId() {
        return mId;
    }

    public static enum Field {
        SHORTNEWS, DETAILNEWS, ID
    }

    public String get(Field f) {
        switch (f) {
            case SHORTNEWS:
                return mShortNews;
            case DETAILNEWS:
                return mDetailNews;
            case ID:
                return mId;
            default:
                return mId;
        }
    }

}
