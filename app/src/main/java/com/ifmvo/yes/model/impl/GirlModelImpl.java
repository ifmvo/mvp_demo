package com.ifmvo.yes.model.impl;

import android.content.Context;

import com.ifmvo.yes.base.BaseModel;
import com.ifmvo.yes.model.interfaces.IGirlModel;
import com.ifmvo.yes.net.StringTransactionListener;
import com.ifmvo.yes.net.URLs;
import com.ifmvo.yes.vo.request.GirlRequest;

/**
 *
 * ifmvo on 2016/4/3.
 */
public class GirlModelImpl extends BaseModel implements IGirlModel {

    public GirlModelImpl(Context context) {
        super(context);
    }

    @Override
    public void requestBeautifulGirlData(GirlRequest girlRequest, StringTransactionListener stringTransactionListener) {
        get(getContext(), URLs.GIRL_HOST, girlRequest, stringTransactionListener);
    }
}
