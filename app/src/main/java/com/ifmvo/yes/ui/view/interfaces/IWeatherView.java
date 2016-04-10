package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.info.Result;

/**
 * ifmvo on 2016/4/10.
 */
public interface IWeatherView extends IBaseView{

    /**
     * 获取天气网络数据
     */
    void queryWeatherData(Result result);


    /**
     * 处理无法连接
     * @param msg 错误信息
     */
    void handleNoConnect(String msg);

}
