package cn.topear.hifi.bean;

/**
 * Created by clx-zj on 2016/3/12.
 */
public class NewsBean {
    /**
     * articleid : 2015041300122
     * commentCount : 44
     * likeCount : 15
     * mainpicture : /Uploads/20150415/2015041512045634794.png
     * title : 手机扬声器HiFi之路为何这么难
     */

    private String articleid;
    private int commentCount;
    private int likeCount;
    private String mainpicture;
    private String title;

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setMainpicture(String mainpicture) {
        this.mainpicture = mainpicture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleid() {
        return articleid;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getMainpicture() {
        return mainpicture;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "NewsBean{" +
                "articleid='" + articleid + '\'' +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", mainpicture='" + mainpicture + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
