package com.jordan.commonlibrary.task;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.jordan.commonlibrary.config.CommonSystemConfig;
import com.jordan.commonlibrary.data.UploadMediaInfo;
import com.jordan.commonlibrary.utils.CommonSystemUtils;
import com.jordan.httplibrary.BaseTask;
import com.jordan.httplibrary.HttpUtils;
import com.jordan.httplibrary.config.InnerMessageConfig;
import com.jordan.httplibrary.utils.CommonUtils;
import com.safari.core.protocol.RequestMessage;

import java.util.ArrayList;

/**
 * Created by icean on 2017/2/1.
 */

public final class UploadMediasTask extends BaseTask {

    private ArrayList<UploadMediaInfo> mAllUploadFiles;
    private String mUploadMediasUri;

    public UploadMediasTask(Context ctx, String remote_address, Handler main_handler,
                            ArrayList<UploadMediaInfo> upload_medias, String user_token,
                            boolean is_granted) {
        super(ctx, remote_address, user_token, main_handler, is_granted);
        mAllUploadFiles = upload_medias;
        mUploadMediasUri = mRemoteServerAddress + CommonSystemConfig.URI_UPLOAD_FILE_SOME;
    }

    @Override
    public String doTask() {
        String main_json_str = CommonSystemUtils.createUploadMediasMainRequest(mAllUploadFiles);
        RequestMessage.Request request_proto = CommonUtils.createRequest(mContext, main_json_str, mUserToken, mIsGranted);
        String result_json = HttpUtils.sendHttpRequest(mUploadMediasUri, request_proto);
        return result_json;
    }

    @Override
    public void onSuccess(String result_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_UPLOAD_MEDIA_MESSAGE_SUCCESS);
            success_message.obj = result_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onFalse(String false_json) {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_UPLOAD_MEDIA_MESSAGE_FALSE);
            success_message.obj = false_json;
            success_message.sendToTarget();
        }
    }

    @Override
    public void onException() {
        if (null != mMainHandler) {
            Message success_message = mMainHandler.obtainMessage(InnerMessageConfig.COMMON_UPLOAD_MEDIA_MESSAGE_EXCEPTION);
            success_message.sendToTarget();
        }
    }
}
