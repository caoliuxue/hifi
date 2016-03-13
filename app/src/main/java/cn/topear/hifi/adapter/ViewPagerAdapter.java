package cn.topear.hifi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.topear.hifi.R;
import cn.topear.hifi.http.HiFiRestClient;
import cn.topear.hifi.bean.NewsBean;

/**
 * view pager adapter
 * Created by clx-zj on 2016/3/12.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> pagerList;

    public ViewPagerAdapter(List<NewsBean> newsBeanList,Context context) {
        LayoutInflater lf = LayoutInflater.from(context);
        this.pagerList = new ArrayList<>();
        for(NewsBean newsBean:newsBeanList){
            View v = lf.inflate(R.layout.header_news_item, null);
            TextView title = (TextView)v.findViewById(R.id.header_news_item_txt);
            ImageView img = (ImageView)v.findViewById(R.id.header_news_item_img);
            title.setText(newsBean.getTitle());
            Picasso.with(context).load(HiFiRestClient.BASE_URL+newsBean.getMainpicture()).into(img);
            pagerList.add(v);
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("destroyItem",String.valueOf(position));
       container.removeView(pagerList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("instantiateItem", String.valueOf(position));
        container.addView(pagerList.get(position), 0);
        return pagerList.get(position);
    }

    public int getCount() {
        return null == pagerList ? 0 : pagerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
