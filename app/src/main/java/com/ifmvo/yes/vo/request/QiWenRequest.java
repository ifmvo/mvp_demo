package com.ifmvo.yes.vo.request;

import com.ifmvo.yes.app.G;
import com.ifmvo.yes.base.BaseRequest;
import com.ifmvo.yes.net.URLs;

/**
 * Created by Administrator on 2016/2/23.
 */
public class QiWenRequest extends BaseRequest {
    public String key = URLs.QIWEN_API_KEY;
    public String rows = G.QIWEN_PAGE_SIZE+"";
    public String page;
}
