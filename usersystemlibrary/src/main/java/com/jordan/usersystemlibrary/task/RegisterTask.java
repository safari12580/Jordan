package com.jordan.usersystemlibrary.task;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.jordan.httplibrary.BaseTask;
import com.jordan.httplibrary.HttpUtils;
import com.jordan.httplibrary.config.InnerMessageConfig;
import com.jordan.httplibrary.utils.CommonUtils;
import com.jordan.usersystemlibrary.config.UserSystemConfig;
import com.jordan.usersystemlibrary.utils.UserSystemUtils;
import com.safari.core.protocol.RequestMessage;

/**
 * Created by icean on 2017/2/2.
 */

public final class RegisterTask extends BaseTask {
    private String mAccount, mPassword, mAccountType, mCode;
    private String mUrl;

    public RegisterTask(Context ctx, String remote_address, Handler main_handler,
                        String user_account, String password, String account_type, String code, String user_token,
                     boolean is_granted) {
        super(ctx, remote_address, user_token, main_handler, is_granted);
        mAccount = user_account;
        mPassword = password;
        mAccountType = account_type;
        mCode = code;
        mUrl = mRemoteServerAddress + UserSystemConfig.URI_REGISTER;
    }

    @Override
    public String doTask() {
        String main_json_str = UserSystemUtils.createRegisterMainRequest(mAccount, mPassword, mAccountType, mCode);
        RequestMessage.Request request_proto = CommonUtils.createRequest(mContext, main_json_str, mUserToken, mIsGranted);
        String result_json = HttpUtils.sendHttpRequest(mUrl, request_proto);
        return result_json;
    }

    @Override
    public void onSuccess(String result_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_REGISTER_MESSAGE_SUCCESS);
            success_message.obj = result_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onFalse(String false_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_REGISTER_MESSAGE_FALSE);
            success_message.obj = false_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onException() {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_REGISTER_MESSAGE_EXCEPTION);
            success_message.sendToTarget();
        }
    }
}
