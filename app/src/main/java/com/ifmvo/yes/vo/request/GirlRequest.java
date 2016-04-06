package com.ifmvo.yes.vo.request;

import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BaseRequest;
import com.ifmvo.yes.net.URLs;

/**
 * ifmvo on 2016/4/3.
 */
public class GirlRequest extends BaseRequest {
    public String key = URLs.GIRL_API_KEY;
    //每次请求的个数
    public String num = G.PAGE_SIZE;
    //第几页
    public String page;
}
