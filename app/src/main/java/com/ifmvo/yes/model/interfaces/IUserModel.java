package com.ifmvo.yes.model.interfaces;

import com.ifmvo.yes.net.JsonTransactionListener;
import com.ifmvo.yes.vo.request.LoginRequest;

/**
 * 用户模型接口
 */
public interface IUserModel {
    void login(LoginRequest loginRequest, JsonTransactionListener transactionListener);
}
