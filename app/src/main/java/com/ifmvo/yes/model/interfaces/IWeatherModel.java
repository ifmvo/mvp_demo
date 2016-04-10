package com.ifmvo.yes.model.interfaces;

import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.vo.request.WeatherRequest;

/**
 * ifmvo on 2016/4/10.
 */
public interface IWeatherModel {

    void requestWeatherData(WeatherRequest weatherRequest, StringTransactionListener stringTransactionListener);
}
