package com.ifmvo.yes.presenter.interfaces;

import com.ifmvo.yes.ui.view.interfaces.IQiWenView;
import com.ifmvo.yes.vo.request.QiWenRequest;

/**
 * ifmvo on 2016/4/6.
 */
public interface IQiWenPresenter {

    /**
     * 请求奇闻异事数据
     */
    void requestQiWenData(IQiWenView qiWenView, QiWenRequest qiWenRequest);

    /**
     * 加载更多
     */
    void requestMoreQiWenData(IQiWenView qiWenView, QiWenRequest qiWenRequest);
}
