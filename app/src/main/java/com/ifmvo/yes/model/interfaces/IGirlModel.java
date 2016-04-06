package com.ifmvo.yes.model.interfaces;

import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.vo.request.GirlRequest;

/**
 * ifmvo on 2016/4/3.
 */
public interface IGirlModel {

    /**
     * 请求网络美女数据
     */
    void requestBeautifulGirlData(GirlRequest girlRequest, StringTransactionListener stringTransactionListener);
}
