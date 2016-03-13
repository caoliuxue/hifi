package cn.topear.hifi.http;

/**
 * Created by clx-zj on 2016/3/13.
 */
public interface Constant {
    String BASE_URL = "http://123.57.216.21:80/hifi-shop";
    String NEWS_TOP = "/app/article/findArticleByFlagType.action?flagType=4&currentPage=1&lineSize=4";
    String NEWS_PAGED = "/app/article/findArticleByFlagType.action?flagType=2&lineSize=20&currentPage=";
    String NEWS_DETAIL = "/app/article/findArticleById.action?articleId=";
    String COMMENT_BY_ARTICLE = "/app/comment/findCommentsByArticle.action";

    interface INTENT_EXTRA{
        String ARTICLEID = "articleid";
    }

    interface REQUEST_CODE{
        int NEWS_DETAIL = 0xA001;
    }
}
