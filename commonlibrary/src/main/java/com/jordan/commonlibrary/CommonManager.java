package com.jordan.commonlibrary;

import android.content.Context;
import android.os.Handler;

import com.jordan.commonlibrary.config.CommonSystemConfig;
import com.jordan.commonlibrary.data.UploadMediaInfo;
import com.jordan.commonlibrary.task.CheckCodeTask;
import com.jordan.commonlibrary.task.CheckVersionTask;
import com.jordan.commonlibrary.task.GetCodeTask;
import com.jordan.commonlibrary.task.UploadMediaTask;
import com.jordan.commonlibrary.task.UploadMediasTask;
import com.jordan.commonlibrary.task.ViewMediasTask;

import java.util.ArrayList;

/**
 * Created by icean on 2017/1/27.
 */

public final class CommonManager {
    private Context mContext;
    private Handler mMainThreadHandler;
    private String mCommonRemoteAddress;
    private boolean mIsGranted;
    private String mUserToken;

    private CheckCodeTask mCheckCodeTask;
    private CheckVersionTask mCheckVersionTask;
    private GetCodeTask mGetCodeTask;
    private UploadMediaTask mUploadMediaTask;
    private UploadMediasTask mUploadMediasTask;
    private ViewMediasTask mViewMediasTask;

    public CommonManager(Context context, Handler main_thread_handler, String common_address) {
        mContext = context;
        mMainThreadHandler = main_thread_handler;
        mCommonRemoteAddress = common_address;
        mIsGranted = false;
        mUserToken = "";
    }

    public CommonManager(Context context, Handler main_thread_handler) {
        this(context, main_thread_handler, CommonSystemConfig.COMMON_DEFAULT_ADDRESS);
    }

    public void setIsGranted(boolean is_granted) {
        mIsGranted = is_granted;
    }

    public void setUserToken(String user_token){
        mUserToken = user_token;
    }

    public void uploadMedia(String file_type, String file_full_path) {
        if (null != mUploadMediaTask) {
            mUploadMediaTask.cancel(true);
            mUploadMediaTask = null;
        }

        mUploadMediaTask = new UploadMediaTask(mContext, mCommonRemoteAddress, mMainThreadHandler, file_type, file_full_path, mUserToken, mIsGranted);
        mUploadMediaTask.execute("Begin upload");
    }

    public void uploadMedias(ArrayList<UploadMediaInfo> upload_files) {
        if (null != mUploadMediasTask) {
            mUploadMediasTask.cancel(true);
            mUploadMediasTask = null;
        }

        mUploadMediasTask = new UploadMediasTask(mContext, mCommonRemoteAddress, mMainThreadHandler, upload_files, mUserToken, mIsGranted);
        mUploadMediasTask.execute("Begin upload");
    }

    public void getCode(String user_account, String apply_type, String genre) {
        if (null != mGetCodeTask) {
            mGetCodeTask.cancel(true);
            mGetCodeTask = null;
        }

        mGetCodeTask = new GetCodeTask(mContext, mCommonRemoteAddress, mMainThreadHandler, user_account, apply_type, genre, mUserToken, mIsGranted);
        mGetCodeTask.execute("Begin upload");
    }

    public void checkCode(String user_account, String apply_type, String code) {
        if (null != mCheckCodeTask) {
            mCheckCodeTask.cancel(true);
            mCheckCodeTask = null;
        }

        mCheckCodeTask = new CheckCodeTask(mContext, mCommonRemoteAddress, mMainThreadHandler, user_account, apply_type, code, mUserToken, mIsGranted);
        mGetCodeTask.execute("Begin upload");
    }

    public void checkVersion(String device_type) {
        if (null != mCheckVersionTask) {
            mCheckVersionTask.cancel(true);
            mCheckVersionTask = null;
        }

        mCheckVersionTask = new CheckVersionTask(mContext, mCommonRemoteAddress, mMainThreadHandler, device_type, mUserToken, mIsGranted);
        mCheckVersionTask.execute("Begin upload");
    }

    public void viewMedias(String[] ids) {
        if (null != mViewMediasTask) {
            mViewMediasTask.cancel(true);
            mViewMediasTask = null;
        }

        mViewMediasTask = new ViewMediasTask(mContext, mCommonRemoteAddress, mMainThreadHandler, ids, mUserToken, mIsGranted);
        mViewMediasTask.execute("Begin upload");
    }

}
