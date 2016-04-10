package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.QiWenModelImpl;
import com.ifmvo.yes.model.interfaces.IQiWenModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.IQiWenPresenter;
import com.ifmvo.yes.ui.view.interfaces.IQiWenView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.info.QiWen;
import com.ifmvo.yes.vo.request.QiWenRequest;
import com.ifmvo.yes.vo.response.QiWenResponse;

import java.util.List;

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
                    List<QiWen> qiWens = qiWenResponse.result;
                    qiWenView.queryQiWenDataFromInternet(qiWens);
                    //把QiWen表里的数据清除
//                    DataSupport.deleteAll(QiWen.class);
                    //把从网上获取过来的数据存到数据库里
//                    DataSupport.saveAll(qiWens);
                }else{
                    //如果没网或者查询失败，就从数据库里面调
//                    List<QiWen> qiWens = DataSupport.limit(G.QIWEN_PAGE_SIZE).find(QiWen.class);
//                    if (qiWens != null)
//                        qiWenView.queryQiWenDataFromInternet(qiWens);
                    qiWenView.handleNoConnect(qiWenResponse.reason);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                //如果没网或者查询失败，就从数据库里面调
//                List<QiWen> qiWens = DataSupport.limit(G.QIWEN_PAGE_SIZE).find(QiWen.class);
//                if (qiWens != null)
//                    qiWenView.queryQiWenDataFromInternet(qiWens);
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
                    qiWenView.againQueryQiWenFromInternet(qiWenResponse.result);
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
