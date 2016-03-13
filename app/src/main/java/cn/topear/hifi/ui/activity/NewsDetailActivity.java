package cn.topear.hifi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.topear.hifi.R;
import cn.topear.hifi.bean.CommentItemBean;
import cn.topear.hifi.bean.NewsDetailBean;
import cn.topear.hifi.http.Constant;
import cn.topear.hifi.http.HiFiRestClient;
import cn.topear.hifi.util.Json2Bean;
import cz.msebera.android.httpclient.Header;

/**
 * News detail
 * Created by clx-zj on 2016/3/13.
 */
public class NewsDetailActivity extends FragmentActivity implements Constant, View.OnClickListener {
    private static final String LOG_TAG = "NewsDetailActivity";

    private TextView contentTitleTextView, titleBarBack;
    private WebView contentWebview;
    private String articleId;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        initView();
        super.onCreate(savedInstanceState);
    }

    void initView() {
        mContext = this;
        articleId = getIntent().getExtras().getString(INTENT_EXTRA.ARTICLEID);
        titleBarBack = (TextView) findViewById(R.id.title_bar_back);
        contentTitleTextView = (TextView) findViewById(R.id.content_title);
        contentWebview = (WebView) findViewById(R.id.contents);
        contentWebview.getSettings().setDefaultTextEncodingName("UTF -8");
        loadNewsDetailByArticleId();
        loadComments();
        titleBarBack.setOnClickListener(this);
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
                    contentWebview.loadData(contentValue, "text/html;charset=UTF-8", null);
//                    System.out.println(newsDetailBean.getContentvalue());
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
                    List<CommentItemBean> commentItemBeans = Json2Bean.getCommentFromJson(page.toString());
                    System.out.println("commentItemBeans.size():" + commentItemBeans.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(LOG_TAG, "loadComments onFailure" + responseString);
//                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.title_bar_back: {
                finish();
                break;
            }
            default:
                break;
        }
    }
}
