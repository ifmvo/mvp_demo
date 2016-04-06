package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.info.User;

/**
 * 用户登录View接口
 */
public interface ILoginView extends IBaseView {
    /**
     * 登录成功视图回调
     */
    void loginCallback(User user);
}
