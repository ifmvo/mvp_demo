package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.R;
import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.model.impl.GirlModelImpl;
import com.ifmvo.yes.model.interfaces.IGirlModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.presenter.interfaces.IGirlPresenter;
import com.ifmvo.yes.ui.view.interfaces.IGirlView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.info.Girl;
import com.ifmvo.yes.vo.request.GirlRequest;
import com.ifmvo.yes.vo.response.GirlResponse;

import org.litepal.crud.DataSupport;

import java.util.List;

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
                    List<Girl> girls = girlResponse.newslist;
                    girlView.queryGirlData(girls);
                    //从网上获取回来新的数据，就把原来的数据清空，并放入新的数据
                    DataSupport.deleteAll(Girl.class);
                    DataSupport.saveAll(girls);
                }else{
                    List<Girl> girls = DataSupport.limit(G.GIRL_PAGE_SIZE).find(Girl.class);
                    if (girls != null)
                        girlView.queryGirlData(girls);
                    girlView.handleNoConnect(girlResponse.message);
                }
            }

            @Override
            public void onFailure(int errorCode) {
                super.onFailure(errorCode);
                List<Girl> girls = DataSupport.limit(G.GIRL_PAGE_SIZE).find(Girl.class);
                if (girls != null)
                    girlView.queryGirlData(girls);
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
                    girlView.againQueryGirlData(girlResponse.newslist);
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
