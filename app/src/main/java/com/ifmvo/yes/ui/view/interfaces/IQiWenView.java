package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.response.QiWenResponse;

/**
 * ifmvo on 2016/4/6.
 */
public interface IQiWenView extends IBaseView{


    void queryQiWenDataFromInternet(QiWenResponse qiWenResponse);

    void againQueryQiWenFromInternet(QiWenResponse qiWenResponse);

    /**
     * 处理无法连接网络的情况
     */
    void handleNoConnect(String msg);

}
