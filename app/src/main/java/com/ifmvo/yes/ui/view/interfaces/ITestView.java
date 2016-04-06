package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.response.ResultResponse;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ITestView extends IBaseView {
    /**
     * 获取归属地查询结果
     *
     * @param result
     */
    void queryResult(ResultResponse result);
}
