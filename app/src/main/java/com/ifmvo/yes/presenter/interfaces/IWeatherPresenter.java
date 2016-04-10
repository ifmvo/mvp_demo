package com.ifmvo.yes.presenter.interfaces;

import com.ifmvo.yes.ui.view.interfaces.IWeatherView;
import com.ifmvo.yes.vo.request.WeatherRequest;

/**
 * ifmvo on 2016/4/10.
 */
public interface IWeatherPresenter {

    /**
     * 请求天气的网络数据
     */
    void requestWeatherDataFromInternet(IWeatherView weatherView, WeatherRequest weatherRequest);

}
