package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.info.QiWen;

import java.util.List;

/**
 * ifmvo on 2016/4/6.
 */
public interface IQiWenView extends IBaseView{


    void queryQiWenDataFromInternet(List<QiWen> list);

    void againQueryQiWenFromInternet(List<QiWen> list);

    /**
     * 处理无法连接网络的情况
     */
    void handleNoConnect(String msg);

}
