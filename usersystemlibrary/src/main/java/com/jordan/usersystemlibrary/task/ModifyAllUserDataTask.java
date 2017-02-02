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

public final class ModifyAllUserDataTask extends BaseTask {
    private String mName, mNick, mGender, mAge, mBirthday, mPosition, mWeight, mHeight, mQQ, mImg;
    private String mUrl;

    public ModifyAllUserDataTask(Context ctx, String remote_address, Handler main_handler,
                                    String name, String nick, String gender, String age,
                                    String birthday, String position, String weight, String height, String QQ, String img,
                                    String user_token, boolean is_granted) {
        super(ctx, remote_address, user_token, main_handler, is_granted);
        mName = name;
        mNick = nick;
        mGender = gender;
        mAge = age;
        mBirthday = birthday;
        mPosition = position;
        mWeight = weight;
        mHeight = height;
        mQQ = QQ;
        mImg = img;
        mUrl = mRemoteServerAddress + UserSystemConfig.URI_MODIFY_USER_INFO_ALL;
    }

    @Override
    public String doTask() {
        String main_json_str = UserSystemUtils.createModifyAllUserInfoMainRequest(mName, mNick, mGender, mAge, mBirthday, mPosition, mWeight, mHeight, mQQ, mImg);
        RequestMessage.Request request_proto = CommonUtils.createRequest(mContext, main_json_str, mUserToken, mIsGranted);
        String result_json = HttpUtils.sendHttpRequest(mUrl, request_proto);
        return result_json;
    }

    @Override
    public void onSuccess(String result_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_MODIFY_USER_DATA_ALL_MESSAGE_SUCCESS);
            success_message.obj = result_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onFalse(String false_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_MODIFY_USER_DATA_ALL_MESSAGE_FALSE);
            success_message.obj = false_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onException() {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.USER_MODIFY_USER_DATA_ALL_MESSAGE_EXCEPTION);
            success_message.sendToTarget();
        }
    }
}
