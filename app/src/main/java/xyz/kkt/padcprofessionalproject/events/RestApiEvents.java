package xyz.kkt.padcprofessionalproject.events;

import android.content.Context;

import java.util.List;

import xyz.kkt.padcprofessionalproject.data.vo.NewsVO;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class NewsDataLoadedEvent {
        private int loadedPageIndex;
        private List<NewsVO> loadNews;
        private Context context;

        public NewsDataLoadedEvent(int loadedPageIndex, List<NewsVO> loadNews,Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadNews = loadNews;
            this.context=context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<NewsVO> getLoadNews() {
            return loadNews;
        }

        public Context getContext() {
            return context;
        }
    }

}
