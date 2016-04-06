package com.ifmvo.yes.model.impl;

import android.content.Context;

import com.ifmvo.yes.base.BaseModel;
import com.ifmvo.yes.model.interfaces.ITestModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.net.URLs;
import com.ifmvo.yes.vo.request.QueryRequest;

/**
 * Created by Administrator on 2016/2/23.
 */
public class TestModelImpl extends BaseModel implements ITestModel {

    public TestModelImpl(Context context) {
        super(context);
    }

    @Override
    public void attributionToInquiries(QueryRequest queryParameter, StringTransactionListener transactionListener) {
        get(getContext(), URLs.getURL(URLs.TEST_API), queryParameter, transactionListener);
    }
}
