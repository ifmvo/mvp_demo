package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.TestModelImpl;
import com.ifmvo.yes.model.interfaces.ITestModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.ITestPresenter;
import com.ifmvo.yes.ui.view.interfaces.ITestView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.request.QueryRequest;
import com.ifmvo.yes.vo.response.ResultResponse;

/**
 * Created by Administrator on 2016/2/23.
 */
public class TestPresenterImpl extends BasePresenter implements ITestPresenter {
    @Override
    public void attributionToInquiries(final ITestView testView, QueryRequest queryParameter) {
        ITestModel testModel = new TestModelImpl(testView.getContext());
        testModel.attributionToInquiries(queryParameter, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                ResultResponse queryResult = CommonUtils.getGson().fromJson(response, ResultResponse.class);
                if (queryResult.resultcode == 200) {
                    testView.queryResult(queryResult);
                } else {
                    testView.showToast(queryResult.reason);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
            }
        });
    }
}
