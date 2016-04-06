package com.ifmvo.yes.presenter.impl;

import com.ifmvo.yes.base.BasePresenter;
import com.ifmvo.yes.base.BaseResponse;
import com.ifmvo.yes.model.impl.UserModelImpl;
import com.ifmvo.yes.model.interfaces.IUserModel;
import com.ifmvo.yes.net.JsonTransactionListener;
import com.ifmvo.yes.presenter.interfaces.ILoginPresenter;
import com.ifmvo.yes.ui.view.interfaces.ILoginView;
import com.ifmvo.yes.utils.CommonUtils;
import com.ifmvo.yes.vo.request.LoginRequest;
import com.ifmvo.yes.vo.info.User;

public class LoginPresenterImpl extends BasePresenter implements ILoginPresenter {

    @Override
    public void login(final ILoginView loginView, LoginRequest loginRequest) {

        // 校验用户名和密码是否为空
        if (isEmpty(loginRequest.userName, loginView, "用户名不能为空")) return;
        if (isEmpty(loginRequest.password, loginView, "密码不能为空")) return;

        // 实例化用户模型,调用登录方法,传入接口所需参数
        IUserModel userModel = new UserModelImpl(loginView.getContext());
        userModel.login(loginRequest, new JsonTransactionListener() {
            @Override
            public void onSuccess(BaseResponse response) {
                if (response.isSuccess()) {
                    // 登录成功,调用view接口显示用户信息
                    User userInfo = CommonUtils.getGson().fromJson(response.getData(), User.class);
                    loginView.loginCallback(userInfo);
                } else {
                    // 登录失败,根据业务需求进行处理...
                }
            }

            @Override
            public void onFailure(int errorCode) {
                // 网络访问异常
                super.onFailure(errorCode);
            }
        });
    }
}
