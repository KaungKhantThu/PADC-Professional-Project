package xyz.kkt.padcprofessionalproject.events;

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

        public NewsDataLoadedEvent(int loadedPageIndex, List<NewsVO> loadNews) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadNews = loadNews;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<NewsVO> getLoadNews() {
            return loadNews;
        }
    }

}
