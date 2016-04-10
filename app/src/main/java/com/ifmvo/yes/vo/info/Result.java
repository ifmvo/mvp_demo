package com.ifmvo.yes.vo.info;

/**
 * ifmvo on 2016/4/10.
 */
public class Result{

    private RealTime realtime;

    public RealTime getRealtime() {
        return realtime;
    }

    public void setRealtime(RealTime realtime) {
        this.realtime = realtime;
    }

    public Result(RealTime realtime) {
        this.realtime = realtime;
    }

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "realtime=" + realtime +
                '}';
    }
}
