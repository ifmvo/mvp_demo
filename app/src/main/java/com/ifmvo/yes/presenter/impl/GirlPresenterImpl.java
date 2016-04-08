package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.R;
import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.GirlModelImpl;
import com.ifmvo.yes.model.interfaces.IGirlModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.IGirlPresenter;
import com.ifmvo.yes.ui.view.interfaces.IGirlView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.request.GirlRequest;
import com.ifmvo.yes.vo.response.GirlResponse;

/**
 * ifmvo on 2016/4/3.
 */
public class GirlPresenterImpl extends BasePresenter implements IGirlPresenter {


    @Override
    public void requestGirlDataFromInternet(final IGirlView girlView, GirlRequest girlRequest) {
        IGirlModel girlModel = new GirlModelImpl(girlView.getContext());
        girlModel.requestBeautifulGirlData(girlRequest, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                GirlResponse girlResponse = CommonUtils.getGson().fromJson(response, GirlResponse.class);
                if (girlResponse.code == 200){
                    girlView.queryGirlData(girlResponse);
                }else{
                    girlView.handleNoConnect(girlResponse.message);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                girlView.handleNoConnect(girlView.getContext().getString(R.string.cannot_load));
            }
        });

    }

    @Override
    public void requestMoreGirlDataFromInternet(final IGirlView girlView, GirlRequest girlRequest) {
        IGirlModel girlModel = new GirlModelImpl(girlView.getContext());
        girlModel.requestBeautifulGirlData(girlRequest, new StringTransactionListener() {
            @Override
            public void onSuccess(String response) {
                GirlResponse girlResponse = CommonUtils.getGson().fromJson(response, GirlResponse.class);
                if (girlResponse.code == 200){
                    girlView.againQueryGirlData(girlResponse);
                }else{
                    girlView.showToast(girlResponse.message);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                girlView.handleNoConnect(girlView.getContext().getString(R.string.cannot_load));
            }
        });
    }
}
