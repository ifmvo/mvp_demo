package com.ifmvo.yes.BeautifulGirl.Presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.ifmvo.yes.BeautifulGirl.Model.Girl;
import com.ifmvo.yes.BeautifulGirl.Model.GirlResponse;
import com.ifmvo.yes.BeautifulGirl.View.IBeautifulGirlView;
import com.ifmvo.yes.Constants;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

/**
 * ifmvo on 2016/3/29.
 */
public class BeautifulGirlPresenter {

    private Context context;
    IBeautifulGirlView beautifulGirlView;
    List<Girl> girls;
    FinalHttp finalHttp;

    public BeautifulGirlPresenter(Context context, IBeautifulGirlView beautifulGirlView) {
        this.beautifulGirlView = beautifulGirlView;
        this.context = context;
        finalHttp = new FinalHttp();
        girls = new ArrayList<>();
    }

    public void requestGirlData(){

        AjaxParams params = new AjaxParams();
        params.put("key", Constants.BEAUTIFUL_GIRL_API_KEY);
        params.put("num", Constants.EVERY_PAGE_NUM);
        params.put("page", "1");

        AjaxCallBack<String> callBack = new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                // TODO 用不用判断一下code呢，待定
                GirlResponse response = new Gson().fromJson(s, GirlResponse.class);
                girls.addAll(response.getNewslist());
                beautifulGirlView.loadGirlDataToListView(girls);
                beautifulGirlView.showProgressBar(false);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                beautifulGirlView.showCannotLoad(true);
                beautifulGirlView.showProgressBar(false);
            }
        };

        beautifulGirlView.showCannotLoad(false);
        finalHttp.get(Constants.BEAUTIFUL_GIRL_URL, params, callBack);
    }

    public void requestMoreGirlData(){
        AjaxParams params = new AjaxParams();
        params.put("key", Constants.BEAUTIFUL_GIRL_API_KEY);
        params.put("num", Constants.EVERY_PAGE_NUM);
        params.put("page", ((girls.size() / Integer.parseInt(Constants.EVERY_PAGE_NUM)) + 1) + "");

        AjaxCallBack<String> callBack = new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                // TODO 用不用判断一下code呢，待定
                GirlResponse response = new Gson().fromJson(s, GirlResponse.class);
                girls.addAll(response.getNewslist());
                beautifulGirlView.notifyAdapter();
                beautifulGirlView.loadGirlDataToListView(girls);
                beautifulGirlView.showProgressBar(false);

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                beautifulGirlView.showCannotLoad(true);
                beautifulGirlView.showProgressBar(false);
            }
        };

        finalHttp.get(Constants.BEAUTIFUL_GIRL_URL, params, callBack);
    }
}
