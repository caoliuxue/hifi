package cn.topear.hifi.bean;

/**
 * Created by clx-zj on 2016/3/13.
 */
public class CommentItemBean {


    /**
     * commentcontent : 法师// 回复asukanoir:多发
     * commentid : 20150724002025
     * goodsid : 2015041300121
     * goodsname : 极致轻薄，乐享HiFi——X5Max轻体验
     * likeCount : 0
     * posttime : 2015-07-24 14:13:08
     * replyid :
     * replyorcommentuserid : 201504280036
     * replyorcommentusername : asukanoir
     * source : 0
     */

    private String commentcontent;
    private String commentid;
    private String goodsid;
    private String goodsname;
    private String likeCount;
    private String posttime;
    private String replyid;
    private String replyorcommentuserid;
    private String replyorcommentusername;
    private String source;

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }


    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public void setReplyorcommentuserid(String replyorcommentuserid) {
        this.replyorcommentuserid = replyorcommentuserid;
    }

    public void setReplyorcommentusername(String replyorcommentusername) {
        this.replyorcommentusername = replyorcommentusername;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public String getCommentid() {
        return commentid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }


    public String getPosttime() {
        return posttime;
    }

    public String getReplyid() {
        return replyid;
    }

    public String getReplyorcommentuserid() {
        return replyorcommentuserid;
    }

    public String getReplyorcommentusername() {
        return replyorcommentusername;
    }

}
