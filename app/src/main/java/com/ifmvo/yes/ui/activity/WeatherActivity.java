package com.ifmvo.yes.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BaseActivity;
import com.ifmvo.yes.presenter.impl.WeatherPresenterImpl;
import com.ifmvo.yes.presenter.interfaces.IWeatherPresenter;
import com.ifmvo.yes.ui.view.interfaces.IWeatherView;
import com.ifmvo.yes.vo.info.Result;
import com.ifmvo.yes.vo.request.WeatherRequest;

import butterknife.Bind;

/**
 * ifmvo on 2016/4/10.
 */
public class WeatherActivity extends BaseActivity implements IWeatherView {

    @Bind(R.id.txt)
    TextView textView;

    public static final String CITY = "city";
    IWeatherPresenter weatherPresenter;

    public static void action(Context context, String city){
        Intent intent = new Intent (context, WeatherActivity.class);
        intent.putExtra(CITY, city);
        context.startActivity(intent);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_weather);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String city = intent.getStringExtra(CITY);

        WeatherRequest params = new WeatherRequest();
        params.cityname = city;
        weatherPresenter.requestWeatherDataFromInternet(WeatherActivity.this, params);
    }

    @Override
    public void initPresenter() {
        weatherPresenter = new WeatherPresenterImpl();
    }

    @Override
    public void queryWeatherData(Result result) {
        if (result != null){
            textView.setText(result.toString());
        }
    }

    @Override
    public void handleNoConnect(String msg) {

    }

    public void showWeatherLoading() {

    }
}
