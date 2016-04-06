package com.ifmvo.yes.model.impl;

import android.content.Context;

import com.ifmvo.yes.base.BaseModel;
import com.ifmvo.yes.model.interfaces.IUserModel;
import com.ifmvo.yes.net.JsonTransactionListener;
import com.ifmvo.yes.vo.request.LoginRequest;

/**
 * 用户模型实现类
 */
public class UserModelImpl extends BaseModel implements IUserModel {

    public UserModelImpl(Context context) {
        super(context);
    }

    @Override
    public void login(LoginRequest loginRequest, JsonTransactionListener transactionListener) {
        post(getContext(), "http://www.baidu.com", loginRequest, transactionListener);
    }
}
