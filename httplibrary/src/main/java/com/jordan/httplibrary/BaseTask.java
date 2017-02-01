package com.jordan.httplibrary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

/**
 * Created by icean on 2017/2/1.
 */

public abstract class BaseTask extends AsyncTask<String, String, String> {

    protected Context mContext;
    protected String mRemoteServerAddress, mUserToken;
    protected boolean mIsGranted;
    protected Handler mMainHandler;

    public BaseTask(Context ctx, String target_address, String user_token, Handler main_handler, boolean is_granted) {
        mContext = ctx;
        mRemoteServerAddress = target_address;
        mUserToken = user_token;
        mMainHandler = main_handler;
        mIsGranted = is_granted;
    }

    @Override
    protected String doInBackground(String... params) {
        return doTask();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    public abstract String doTask();
    public abstract void onSuccess(String result_json);
    public abstract void onFalse(String false_json);
    public abstract void onException();
}
