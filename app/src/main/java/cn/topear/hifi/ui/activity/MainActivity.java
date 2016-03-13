package cn.topear.hifi.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import cn.topear.hifi.R;
import cn.topear.hifi.ui.fragment.Fragment_GoldEar;
import cn.topear.hifi.ui.fragment.Fragment_Mine;
import cn.topear.hifi.ui.fragment.Fragment_News;
import cn.topear.hifi.ui.fragment.Fragment_Products;

/**
 * HiFi main activity
 * Created by clx-zj on 2016/3/12.
 */
public class MainActivity extends FragmentActivity {

    private static final String LOG_TAG = "MainActivity";

    private View currentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }


    private void initComponents() {
        final Fragment_News fragment_news = (Fragment_News) getSupportFragmentManager().findFragmentById(R.id.fragment_news);
        final Fragment_Products fragment_products = (Fragment_Products) getSupportFragmentManager().findFragmentById(R.id.fragment_products);
        final Fragment_GoldEar fragment_goldEar = (Fragment_GoldEar) getSupportFragmentManager().findFragmentById(R.id.fragment_goldear);
        final Fragment_Mine fragment_mine = (Fragment_Mine) getSupportFragmentManager().findFragmentById(R.id.fragment_mine);

        ImageButton btn_news = (ImageButton) findViewById(R.id.buttom_one);
        ImageButton btn_products = (ImageButton) findViewById(R.id.buttom_two);
        ImageButton btn_goldEar = (ImageButton) findViewById(R.id.buttom_three);
        ImageButton btn_mine = (ImageButton) findViewById(R.id.buttom_four);

        btn_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(fragment_products).hide(fragment_goldEar).hide(fragment_mine).show(fragment_news);
                ft.commit();
                setButton(v);
            }
        });

        btn_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(fragment_news).hide(fragment_goldEar).hide(fragment_mine).show(fragment_products);
                ft.commit();
                setButton(v);
            }
        });


        btn_goldEar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(fragment_news).hide(fragment_products).hide(fragment_mine).show(fragment_goldEar);
                ft.commit();
                setButton(v);
            }
        });

        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.hide(fragment_news).hide(fragment_goldEar).hide(fragment_products).show(fragment_mine);
                ft.commit();
                setButton(v);
            }
        });

        /**
         * 默认第一个按钮点击
         */
        btn_news.performClick();

    }

    /**
     * 设置按钮的背景图片
     *
     * @param v
     */
    private void setButton(View v) {
        if (currentButton != null && currentButton.getId() != v.getId()) {
            currentButton.setEnabled(true);
        }
        v.setEnabled(false);
        currentButton = v;
    }

}
