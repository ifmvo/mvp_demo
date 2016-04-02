package com.ifmvo.yes.BeautifulGirl.Model;

import java.util.List;

/**
 * ifmvo on 2016/3/29.
 */
public class GirlResponse {

    private int code;

    private String message;

    private List<Girl> newslist;

    public GirlResponse(int code, String message, List<Girl> newslist) {
        this.code = code;
        this.message = message;
        this.newslist = newslist;
    }

    public GirlResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Girl> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Girl> newslist) {
        this.newslist = newslist;
    }
}
