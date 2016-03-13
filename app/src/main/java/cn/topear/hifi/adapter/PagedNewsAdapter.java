package cn.topear.hifi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.topear.hifi.R;
import cn.topear.hifi.http.HiFiRestClient;
import cn.topear.hifi.bean.NewsBean;

/**
 * Created by clx-zj on 2016/3/13.
 */
public class PagedNewsAdapter extends ArrayAdapter<NewsBean> {


    public PagedNewsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsBean newsBean = getItem(position);
        System.out.println(newsBean);
        ViewHolder viewHolder;
        if (null == convertView) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAconvertView");
            LayoutInflater lf = LayoutInflater.from(getContext());
            convertView = lf.inflate(R.layout.paged_news_item, null);
            viewHolder = new ViewHolder();
            viewHolder.pagedNewsMainPic = (ImageView) convertView.findViewById(R.id.paged_news_item_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.paged_news_item_txt);
            viewHolder.count = (TextView) convertView.findViewById(R.id.count);
//            viewHolder.messageCnt = (TextView) convertView.findViewById(R.id.message_cnt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(newsBean.getTitle());
        viewHolder.count.setText(newsBean.getLikeCount()+"收藏 "+newsBean.getCommentCount()+"评论");
//        viewHolder.messageCnt.setText("" + newsBean.getCommentCount());
        Picasso.with(getContext()).load(HiFiRestClient.BASE_URL + newsBean.getMainpicture()).into(viewHolder.pagedNewsMainPic);
        return convertView;
    }

    public class ViewHolder {
        ImageView pagedNewsMainPic;
        TextView title, count;
    }
}
