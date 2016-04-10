package com.ifmvo.yes.model.impl;

import android.content.Context;

import com.ifmvo.yes.base.BaseModel;
import com.ifmvo.yes.model.interfaces.IWeatherModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.net.URLs;
import com.ifmvo.yes.vo.request.WeatherRequest;

/**
 * ifmvo on 2016/4/10.
 */
public class WeatherModelImpl extends BaseModel implements IWeatherModel {

    public WeatherModelImpl(Context context) {
        super(context);
    }

    @Override
    public void requestWeatherData(WeatherRequest weatherRequest, StringTransactionListener stringTransactionListener) {
        get(getContext(), URLs.WEATHER_HOST, weatherRequest, stringTransactionListener);
    }
}
