package com.ifmvo.yes.vo.request;

import com.ifmvo.yes.base.BaseRequest;
import com.ifmvo.yes.net.URLs;

/**
 * ifmvo on 2016/4/10.
 */
public class WeatherRequest extends BaseRequest{

    public String key = URLs.WEATHER_API_KEY;
    public String cityname;
}
