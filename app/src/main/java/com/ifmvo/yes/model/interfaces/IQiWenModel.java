package com.ifmvo.yes.model.interfaces;

import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.vo.request.QiWenRequest;

/**
 * ifmvo on 2016/4/6.
 */
public interface IQiWenModel {

    void requestQiWenData(QiWenRequest qiWenRequest, StringTransactionListener stringTransactionListener);
}
