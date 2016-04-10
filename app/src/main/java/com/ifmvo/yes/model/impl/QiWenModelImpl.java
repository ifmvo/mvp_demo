package com.ifmvo.yes.model.impl;

import android.content.Context;

import com.ifmvo.yes.base.BaseModel;
import com.ifmvo.yes.model.interfaces.IQiWenModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.net.URLs;
import com.ifmvo.yes.vo.request.QiWenRequest;

/**
 * ifmvo on 2016/4/6.
 */
public class QiWenModelImpl extends BaseModel implements IQiWenModel {

    public QiWenModelImpl(Context context) {
        super(context);
    }

    @Override
    public void requestQiWenData(QiWenRequest qiWenRequest, StringTransactionListener stringTransactionListener) {
        get(getContext(), URLs.QIWEN_HOST, qiWenRequest, stringTransactionListener);
    }

}
