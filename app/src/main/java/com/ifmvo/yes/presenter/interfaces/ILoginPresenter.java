package com.ifmvo.yes.presenter.interfaces;

import com.ifmvo.yes.ui.view.interfaces.ILoginView;
import com.ifmvo.yes.vo.request.LoginRequest;

/**
 * Created by Administrator on 2015/10/20.
 */
public interface ILoginPresenter {
    /**
     * 登录
     * @param loginView
     * @param loginRequest
     */
    void login(ILoginView loginView, LoginRequest loginRequest);
}
