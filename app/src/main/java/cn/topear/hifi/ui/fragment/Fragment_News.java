package cn.topear.hifi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.topear.hifi.R;
import cn.topear.hifi.adapter.PagedNewsAdapter;
import cn.topear.hifi.adapter.ViewPagerAdapter;
import cn.topear.hifi.http.Constant;
import cn.topear.hifi.http.HiFiRestClient;
import cn.topear.hifi.bean.NewsBean;
import cn.topear.hifi.ui.activity.NewsDetailActivity;
import cz.msebera.android.httpclient.Header;

/**
 * news fragment
 * Created by clx-zj on 2016/3/12.
 */
public class Fragment_News extends Fragment implements Constant , AdapterView.OnItemClickListener{
    private static final String LOG_TAG = "Fragment_News";

    private ViewPager mHearNewsViewPager;
    private ViewPagerAdapter mHearVewAdapter;
    private PagedNewsAdapter pagedNewsAdapter;
    private List<NewsBean> headerNewsBeanList;
    private List<NewsBean> pagedNewsBeanList;
    private ListView mNewsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_news, null);
        Log.d(LOG_TAG, "onCreateView");
        initView(contentView);
        return contentView;
    }

    void initView(View contentView) {
        mNewsList = (ListView) contentView.findViewById(R.id.news_list);
        mNewsList.setOnItemClickListener(this);
        pagedNewsAdapter = new PagedNewsAdapter(getContext(), R.layout.paged_news_item);
        mNewsList.setAdapter(pagedNewsAdapter);
        loadHeaderNews();
        loadPagedNews();
    }

    /**
     * 显示轮播图新闻
     *
     * @param newsBeanList
     */
    void initHeadNews(List<NewsBean> newsBeanList) {
        mHearNewsViewPager = (ViewPager) LayoutInflater.from(getActivity()).inflate(R.layout.header_news, null).findViewById(R.id.header_news);
        if (null == mHearVewAdapter) {
            mHearVewAdapter = new ViewPagerAdapter(newsBeanList, getActivity());
        }
        mHearNewsViewPager.setAdapter(mHearVewAdapter);
        mNewsList.addHeaderView(mHearNewsViewPager);
    }


    /**
     * 加载header news
     */
    void loadHeaderNews() {
        HiFiRestClient.get(NEWS_TOP, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i(LOG_TAG, "JSONObject" + response);
                int headerNewsLength = response.length();
                if (headerNewsLength > 0) {
                    headerNewsBeanList = new ArrayList<>();
                    for (int i = 0; i < headerNewsLength; i++) {
                        NewsBean newsBean = new NewsBean();
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            newsBean.setArticleid(jsonObject.getString("articleid"));
                            newsBean.setCommentCount(jsonObject.getInt("commentCount"));
                            newsBean.setLikeCount(jsonObject.getInt("likeCount"));
                            newsBean.setMainpicture(jsonObject.getString("mainpicture"));
                            newsBean.setTitle(jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        headerNewsBeanList.add(newsBean);
                    }
                    initHeadNews(headerNewsBeanList);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i(LOG_TAG, "JSONObject" + errorResponse);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.i(LOG_TAG, "JSONArray" + errorResponse);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(LOG_TAG, "responseString" + responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    /**
     * 加载分页新闻
     */
    private int currentPage = 1;

    void loadPagedNews() {
        HiFiRestClient.get(NEWS_PAGED + currentPage, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i(LOG_TAG, "loadPagedNews" + response);
                int pagedNewsLength = response.length();
                if (pagedNewsLength > 0) {
                    pagedNewsBeanList = new ArrayList<>();
                    for (int i = 0; i < pagedNewsLength; i++) {
                        NewsBean newsBean = new NewsBean();
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            newsBean.setArticleid(jsonObject.getString("articleid"));
                            newsBean.setCommentCount(jsonObject.getInt("commentCount"));
                            newsBean.setLikeCount(jsonObject.getInt("likeCount"));
                            newsBean.setMainpicture(jsonObject.getString("mainpicture"));
                            newsBean.setTitle(jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pagedNewsBeanList.add(newsBean);
                    }
                    pagedNewsAdapter.addAll(pagedNewsBeanList);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.i(LOG_TAG, "JSONObject" + errorResponse);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.i(LOG_TAG, "JSONArray" + errorResponse);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i(LOG_TAG, "responseString" + responseString);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsBean newsBean = (NewsBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
        intent.putExtra(INTENT_EXTRA.ARTICLEID, newsBean.getArticleid());
        startActivityForResult(intent,REQUEST_CODE.NEWS_DETAIL); // 启动Activity
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode:"+requestCode);
//        super.onActivityResult(requestCode, resultCode, data);
    }
}
