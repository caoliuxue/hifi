package cn.topear.hifi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.topear.hifi.R;
import cn.topear.hifi.adapter.CommentsAdapter;
import cn.topear.hifi.bean.CommentItemBean;
import cn.topear.hifi.bean.NewsDetailBean;
import cn.topear.hifi.http.Constant;
import cn.topear.hifi.http.HiFiRestClient;
import cz.msebera.android.httpclient.Header;

/**
 * News detail
 * Created by clx-zj on 2016/3/13.
 */
public class NewsDetailActivity extends FragmentActivity implements Constant {
    private static final String LOG_TAG = "NewsDetailActivity";


    @Bind(R.id.comments)
    ListView comments;
    @Bind(R.id.content_title)
    TextView contentTitleTextView;
    @Bind(R.id.contents)
    WebView contentWebView;

    private String articleId;
    private Context mContext;

    private CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initView();
        super.onCreate(savedInstanceState);
    }

    void initView() {
        mContext = this;
        articleId = getIntent().getExtras().getString(INTENT_EXTRA.ARTICLEID);
        contentWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        comments.setAdapter(commentsAdapter=new CommentsAdapter(this));
        loadNewsDetailByArticleId();
        loadComments();
    }

    void loadNewsDetailByArticleId() {
        HiFiRestClient.get(NEWS_DETAIL + articleId, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i(LOG_TAG, "loadNewsDetailByArticleId" + response);
                NewsDetailBean newsDetailBean = new NewsDetailBean();
                try {
                    JSONObject article = response.getJSONObject("response").getJSONObject("article");
                    newsDetailBean.setArticleid(article.getString("articleid"));
                    newsDetailBean.setCommentCount(article.getInt("commentCount"));
                    newsDetailBean.setLikeCount(article.getInt("likeCount"));
                    newsDetailBean.setMainpicture(article.getString("mainpicture"));
                    newsDetailBean.setTitle(article.getString("title"));
                    newsDetailBean.setContentvalue(article.getString("contentvalue"));
                    contentTitleTextView.setText(newsDetailBean.getTitle());
                    String contentValue = newsDetailBean.getContentvalue();
                    contentValue = "<img src=\"" + HiFiRestClient.BASE_URL + newsDetailBean.getMainpicture() + "\" alt=\"\" border=\"0\" /></p>" + contentValue;
                    contentValue = contentValue.replace("<img", "<img style=\"width:100%;height:auto\"");
                    contentWebView.loadData(contentValue, "text/html;charset=UTF-8", null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(LOG_TAG, "responseString" + responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    void loadComments() {
        RequestParams params = new RequestParams();
        params.add("articleId", articleId);
        params.add("currentPage", "1");
        params.add("lineSize", "10");
        HiFiRestClient.post(COMMENT_BY_ARTICLE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i(LOG_TAG, "loadComments" + response);
                try {
                    JSONArray page = response.getJSONObject("response").getJSONObject("page").getJSONArray("result");
                    List<CommentItemBean> commentItemBeans = new ArrayList<>();
                    if (page.length() > 0) {
                        for (int i = 0; i < page.length(); i++) {
                            JSONObject comment = page.getJSONObject(i);
                            CommentItemBean commentItemBean = new CommentItemBean();
                            commentItemBean.setCommentcontent(comment.getString("commentcontent"));
                            commentItemBean.setCommentid(comment.getString("commentid"));
                            commentItemBean.setGoodsid(comment.getString("goodsid"));
                            commentItemBean.setGoodsname(comment.getString("goodsname"));
                            commentItemBean.setLikeCount(comment.getString("likeCount"));
                            commentItemBean.setPosttime(comment.getString("posttime"));
                            commentItemBean.setReplyid(comment.getString("replyid"));
                            commentItemBean.setReplyorcommentuserid(comment.getString("replyorcommentuserid"));
                            commentItemBean.setReplyorcommentusername(comment.getString("replyorcommentusername"));
                            commentItemBean.setSource(comment.getString("source"));
                            commentItemBeans.add(commentItemBean);
                        }
                        commentsAdapter.addData(commentItemBeans);
                    }
                    System.out.println("commentItemBeans.size():" + commentItemBeans.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(LOG_TAG, "loadComments onFailure" + responseString);
            }

        });
    }

    @OnClick(R.id.title_bar_back) public void finish(){
        super.finish();
    }

}
