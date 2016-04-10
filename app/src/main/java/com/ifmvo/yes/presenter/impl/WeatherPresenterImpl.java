package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.WeatherModelImpl;
import com.ifmvo.yes.model.interfaces.IWeatherModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.IWeatherPresenter;
import com.ifmvo.yes.ui.view.interfaces.IWeatherView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.info.Result;
import com.ifmvo.yes.vo.request.WeatherRequest;
import com.ifmvo.yes.vo.response.WeatherResponse;

/**
 * ifmvo on 2016/4/10.
 */
public class WeatherPresenterImpl extends BasePresenter implements IWeatherPresenter {

    @Override
    public void requestWeatherDataFromInternet(final IWeatherView weatherView, WeatherRequest weatherRequest) {
        final IWeatherModel weatherModel = new WeatherModelImpl(weatherView.getContext());

        weatherModel.requestWeatherData(weatherRequest, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                WeatherResponse weatherResponse = CommonUtils.getGson().fromJson(response, WeatherResponse.class);
                if (weatherResponse != null){
                    if (weatherResponse.error_code == 0){
                        Result result = weatherResponse.result;
                        weatherView.queryWeatherData(result);
                    }else{
                        weatherView.handleNoConnect(weatherResponse.reason);
                    }
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                weatherView.handleNoConnect(weatherView.getContext().getString(R.string.cannot_load));
            }
        });
    }
}
