package com.jordan.commonlibrary.task;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.jordan.commonlibrary.utils.CommonSystemUtils;
import com.jordan.httplibrary.BaseTask;
import com.jordan.httplibrary.HttpUtils;
import com.jordan.httplibrary.config.InnerMessageConfig;
import com.jordan.httplibrary.utils.CommonUtils;
import com.safari.core.protocol.RequestMessage;

/**
 * Created by icean on 2017/2/1.
 */

public final class CheckCodeTask extends BaseTask {
    private String mAccount, mType, mCode;

    public CheckCodeTask(Context ctx, String remote_address, Handler main_handler,
                       String user_account, String file_type, String code, String user_token,
                       boolean is_granted) {
        super(ctx, remote_address, user_token, main_handler, is_granted);
        mType = file_type;
        mAccount = user_account;
        mType = file_type;
        mCode = code;
    }
    @Override
    public String doTask() {
        String main_json_str = CommonSystemUtils.createCheckCodeMainRequest(mAccount, mType, mCode);
        RequestMessage.Request request_proto = CommonUtils.createRequest(mContext, main_json_str, mUserToken, mIsGranted);
        String result_json = HttpUtils.sendHttpRequest(mRemoteServerAddress, request_proto);
        return result_json;
    }

    @Override
    public void onSuccess(String result_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_CHECK_CODE_MESSAGE_SUCCESS);
            success_message.obj = result_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onFalse(String false_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_CHECK_CODE_MESSAGE_FALSE);
            success_message.obj = false_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onException() {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_CHECK_CODE_MESSAGE_EXCEPTION);
            success_message.sendToTarget();
        }
    }
}
