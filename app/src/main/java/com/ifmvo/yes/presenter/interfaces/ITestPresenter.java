package com.ifmvo.yes.presenter.interfaces;

import com.ifmvo.yes.ui.view.interfaces.ITestView;
import com.ifmvo.yes.vo.request.QueryRequest;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ITestPresenter {
    /**
     * 归属地查询
     *
     * @param testView
     * @param queryParameter
     */
    void attributionToInquiries(ITestView testView, QueryRequest queryParameter);
}
