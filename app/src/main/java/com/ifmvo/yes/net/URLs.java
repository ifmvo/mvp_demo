package com.ifmvo.yes.net;

/**
 * URL路径处理类
 *
 * @author Ht
 */
public class URLs {

    /**
     *  美女模块
     */
    public static final String GIRL_HOST = "http://api.huceo.com/meinv";
    public static final String GIRL_API_KEY = "30ebcf1ebb28aac02d72384130fd3745";

    /**
     *  笑话
     */
//    public static final String JOKE_HOST = "http://apis.baidu.com/hihelpsme/chinajoke/getjokelist";
//    public static final String JOKE_API_KEY = "62b1402cf8b3d19b221c2d9f8e834ff5";

    /**
     *  奇闻异事
     */
    public static final String QIWEN_HOST = "http://api.avatardata.cn/QiWenNews/Query";
    public static final String QIWEN_API_KEY = "b35e7bf05412442794fb4f937842bddb";






    public static final String HOST = "http://apis.juhe.cn/";
    public static final String PROJECT_NAME = "mobile/";
    public static final String API = "";

    // 归属地查询
    public static final String TEST_API = "get";
    /**
     * 用户模块
     */
    //注册
    public static final String USER_SIGNUP = "xxx";
    //登陆
    public static final String USER_SIGNIN = "xxx";
    //第三方登陆
    public static final String OAUTH_SIGNIN = "xxx";

    /**
     * 拼接请求路径
     *
     * @PARAM URI
     * @RETURN
     */
    public static String getURL(String uri) {
        return HOST + PROJECT_NAME + API + uri;
    }

}
