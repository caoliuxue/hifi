package cn.topear.hifi.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.topear.hifi.R;

/**
 * Created by clx-zj on 2016/3/12.
 */
public class Fragment_Products extends Fragment {
    private static final String LOG_TAG = "Fragment_Products";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_three, null);
        Log.d(LOG_TAG,"onCreateView");

        return contentView;
    }
}
