package com.ifmvo.yes.Custom;

import android.util.Log;

import com.ifmvo.yes.Constants;

/**
 * ifmvo on 2016/3/30.
 */
public class MyLog {
    
    public static void e(String tag, String info){
        if (Constants.LOG_SWITCH){
            Log.e(tag, info);
        }
    }

    public static void i(String tag, String info){
        if (Constants.LOG_SWITCH){
            Log.i(tag, info);
        }
    }
}
