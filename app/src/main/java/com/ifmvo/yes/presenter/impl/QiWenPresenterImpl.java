package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.QiWenModelImpl;
import com.ifmvo.yes.model.interfaces.IQiWenModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.IQiWenPresenter;
import com.ifmvo.yes.ui.view.interfaces.IQiWenView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.request.QiWenRequest;
import com.ifmvo.yes.vo.response.QiWenResponse;

/**
 * ifmvo on 2016/4/6.
 */
public class QiWenPresenterImpl extends BasePresenter implements IQiWenPresenter {


    @Override
    public void requestQiWenData(final IQiWenView qiWenView, QiWenRequest qiWenRequest) {

        IQiWenModel qiWenModel = new QiWenModelImpl(qiWenView.getContext());
        qiWenModel.requestQiWenData(qiWenRequest, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                QiWenResponse qiWenResponse = CommonUtils.getGson().fromJson(response, QiWenResponse.class);
                if (qiWenResponse.error_code == 0){
                    qiWenView.queryQiWenDataFromInternet(qiWenResponse);
                }else{
                    qiWenView.handleNoConnect(qiWenResponse.reason);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                qiWenView.handleNoConnect(qiWenView.getContext().getString(R.string.cannot_load));
            }
        });

    }

    @Override
    public void requestMoreQiWenData(final IQiWenView qiWenView, QiWenRequest qiWenRequest) {
        IQiWenModel qiWenModel = new QiWenModelImpl(qiWenView.getContext());
        qiWenModel.requestQiWenData(qiWenRequest, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                QiWenResponse qiWenResponse = CommonUtils.getGson().fromJson(response, QiWenResponse.class);
                if (qiWenResponse.error_code == 0){
                    qiWenView.againQueryQiWenFromInternet(qiWenResponse);
                }else{
                    qiWenView.handleNoConnect(qiWenResponse.reason);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                qiWenView.handleNoConnect(qiWenView.getContext().getString(R.string.cannot_load));
            }
        });
    }
}
