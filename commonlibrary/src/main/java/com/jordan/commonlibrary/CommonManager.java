package com.jordan.commonlibrary;

import android.content.Context;
import android.os.Handler;

/**
 * Created by icean on 2017/1/27.
 */

public final class CommonManager {
    private Context mContext;
    private Handler mMainThreadHandler;
    private String mCommonRemoteAddress;

    public CommonManager(Context context, Handler main_thread_handler, String common_address) {
        mContext = context;
        mMainThreadHandler = main_thread_handler;
        mCommonRemoteAddress = common_address;
    }
}
