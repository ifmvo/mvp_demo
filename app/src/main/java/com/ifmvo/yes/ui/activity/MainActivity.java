package com.ifmvo.yes.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.presenter.impl.WeatherPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.IWeatherPresenter;
import com.ifmvo.yes.ui.adapter.MainViewPagerAdapter;
import com.ifmvo.yes.ui.custom.TitleBar;
import com.ifmvo.yes.ui.fragment.GirlFragment;
import com.ifmvo.yes.ui.fragment.QiwenFragment;
import com.ifmvo.yes.ui.view.interfaces.IWeatherView;
import com.ifmvo.yes.vo.info.Result;
import com.ifmvo.yes.vo.request.WeatherRequest;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements IWeatherView{
    @Bind(R.id.title_bar)
    TitleBar titleBar;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    View rightViewTitleBar;
    TextView cityTv;

    IWeatherPresenter weatherPresenter;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        titleBar.setTitle(getString(R.string.index_title));

        initTitleBarLeft();
        initTitleBarRight();

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new QiwenFragment(), "奇闻");
        adapter.addFragment(new GirlFragment(), "美女");
        viewPager.setAdapter(adapter);

        //需要在viewPager设置adapter后面写
        tabLayout.setupWithViewPager(viewPager);

        //进入页面是请求天气信息
        notifyRequestWeatherData();
    }

    private void notifyRequestWeatherData() {
        String defaultCity = "绥化";
        WeatherRequest params = new WeatherRequest();
        params.cityname = defaultCity;
        weatherPresenter.requestWeatherDataFromInternet(MainActivity.this, params);
    }

    private void initTitleBarLeft() {
        ImageView iv = new ImageView(getContext());
        iv.setImageResource(R.mipmap.about);
        titleBar.setLeftView(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.action(MainActivity.this);
            }
        });
    }

    public void initTitleBarRight(){

        rightViewTitleBar = LayoutInflater.from(getContext()).inflate(R.layout.main_titlebar_right_view, null);
        cityTv = (TextView) rightViewTitleBar.findViewById(R.id.title_bar_city);

        titleBar.setRightView(rightViewTitleBar);

        rightViewTitleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WeatherActivity.action(MainActivity.this, "绥化");
                notifyRequestWeatherData();
                //默认weatherView显示正在加载
                showWeatherLoading();
            }
        });

        //默认weatherView显示正在加载
        showWeatherLoading();
    }

    @Override
    public void initPresenter() {
        weatherPresenter = new WeatherPresenterImpl();
    }

    @Override
    public void queryWeatherData(Result result) {
        if (result != null){
            String city = result.getRealtime().getCity_name();
            String weather = result.getRealtime().getWeather().getInfo();
            String temperature = result.getRealtime().getWeather().getTemperature();
            cityTv.setText(getString(R.string.weather_info, city, weather, temperature));
        }
    }

    @Override
    public void handleNoConnect(String msg) {
        showToast(msg);
    }

    public void showWeatherLoading() {
        cityTv.setText(getString(R.string.loading));
    }
}
