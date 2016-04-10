package com.ifmvo.yes.ui.view.interfaces;

import com.ifmvo.yes.base.IBaseView;
import com.ifmvo.yes.vo.info.Girl;

import java.util.List;

/**
 * ifmvo on 2016/4/3.
 */
public interface IGirlView extends IBaseView {
    /**
     * 获取girl数据
     */
    void queryGirlData(List<Girl> list);
    /**
     * 原来list中有数据，再一次获取数据（加载更多）
     */
    void againQueryGirlData(List<Girl> list);
    /**
     *
     * @param error
     */
    void handleNoConnect(String error);

}
