package com.ifmvo.yes.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.ui.adapter.MainViewPagerAdapter;
import com.ifmvo.yes.ui.custom.TitleBar;
import com.ifmvo.yes.ui.fragment.GirlFragment;
import com.ifmvo.yes.ui.fragment.QiwenFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

//    IGirlPresenter girlPresenter;
//    IQiWenPresenter qiWenPresenter;

    @Override
    public void initContentView() {
        // 设置布局文件
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        titleBar.setTitle(getString(R.string.index_title));

        ImageView iv = new ImageView(getContext());
        iv.setImageResource(R.mipmap.about);
        titleBar.setLeftView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.action(MainActivity.this);
            }
        });

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new QiwenFragment(), "奇闻");
        adapter.addFragment(new GirlFragment(), "美女");
        viewPager.setAdapter(adapter);

        //需要在viewPager设置adapter后面写
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initPresenter() {
//        girlPresenter = new GirlPresenterImpl();
//        qiWenPresenter = new QiWenPresenterImpl();
    }
}
