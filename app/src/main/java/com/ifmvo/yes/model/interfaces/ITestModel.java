package com.ifmvo.yes.model.interfaces;

import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.vo.request.QueryRequest;

/**
 * Created by Administrator on 2016/2/23.
 */
public interface ITestModel {
    /**
     * 归属地查询
     *
     * @param queryParameter
     * @param transactionListener
     */
    void attributionToInquiries(QueryRequest queryParameter, StringTransactionListener transactionListener);
}
