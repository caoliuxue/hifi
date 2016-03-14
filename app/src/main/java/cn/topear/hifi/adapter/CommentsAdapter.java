package cn.topear.hifi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.topear.hifi.R;
import cn.topear.hifi.bean.CommentItemBean;

/**
 * Created by clx-zj on 2016/3/14.
 */
public class CommentsAdapter extends BaseAdapter {

    private List<CommentItemBean> commentItemBeanList;
    private Context mContext;

    public CommentsAdapter(Context context) {
        commentItemBeanList = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemBean commentItemBean = getItem(position);
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_news_comments_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
//        Picasso.with(mContext).load(HiFiRestClient.BASE_URL + commentItemBean.getMainpicture()).into(holder.avatarImage);
        holder.replier.setText(commentItemBean.getReplyorcommentusername());
        holder.replyTime.setText(commentItemBean.getPosttime());
        holder.replyCcontent.setText(commentItemBean.getCommentcontent());
        return convertView;
    }

    public void addData(List<CommentItemBean> commentItemBeanList) {
        this.commentItemBeanList.addAll(commentItemBeanList);
        notifyDataSetChanged();
    }

    public void setData(List<CommentItemBean> commentItemBeanList) {
        this.commentItemBeanList = commentItemBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null == commentItemBeanList ? 0 : commentItemBeanList.size();
    }

    @Override
    public CommentItemBean getItem(int position) {
        return commentItemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        @Bind(R.id.replier_avatar)
        ImageView avatarImage;
        @Bind(R.id.replier)
        TextView replier;
        @Bind(R.id.reply_time)
        TextView replyTime;
        @Bind(R.id.reply_content)
        TextView replyCcontent;
        @Bind(R.id.reply)
        Button reply;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @OnClick(R.id.reply)
    void toasts() {
        Toast.makeText(mContext, "OnClick toasts", Toast.LENGTH_SHORT).show();
    }
}
