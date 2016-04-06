package com.ifmvo.yes.presenter.interfaces;

import com.ifmvo.yes.ui.view.interfaces.IGirlView;
import com.ifmvo.yes.vo.request.GirlRequest;

/**
 * ifmvo on 2016/4/3.
 */
public interface IGirlPresenter {
    /**
     * 从网络上请求美女数据
     */
    void requestGirlDataFromInternet(IGirlView girlView, GirlRequest girlRequest);

    /**
     * 加载更多
     * @param girlView
     * @param girlRequest
     */
    void requestMoreGirlDataFromInternet(IGirlView girlView, GirlRequest girlRequest);
}
