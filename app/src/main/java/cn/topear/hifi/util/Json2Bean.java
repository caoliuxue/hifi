package cn.topear.hifi.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.topear.hifi.bean.CommentItemBean;

/**
 * Created by clx-zj on 2016/3/13.
 */
public class Json2Bean {

    public static List<CommentItemBean> getCommentFromJson(String json){
        List<CommentItemBean> commentItemBeans = new ArrayList<>();
        try {
            JSONArray array = JSONArray.fromObject(json);
            for(Iterator iterator = array.iterator(); iterator.hasNext();){
                JSONObject jsonObject = (JSONObject)iterator.next();
                commentItemBeans.add((CommentItemBean)JSONObject.toBean(jsonObject, CommentItemBean.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentItemBeans;
    }
}
