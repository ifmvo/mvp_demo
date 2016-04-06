package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.response.GirlResponse;

/**
 * ifmvo on 2016/4/3.
 */
public interface IGirlView extends IBaseView {

    /**
     * 获取girl数据
     * @param girlResponse
     */
    void queryGirlData(GirlResponse girlResponse);

    /**
     * 原来list中有数据，再一次获取数据（加载更多）
     * @param girlResponse
     */
    void againQueryGirlData(GirlResponse girlResponse);

    /**
     *
     * @param error
     */
    void handleNoConnect(String error);

}
