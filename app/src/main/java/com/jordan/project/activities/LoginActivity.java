package com.jordan.project.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jordan.project.R;
import com.jordan.project.config.ActivityActionConfig;
import com.jordan.project.utils.ActivityUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mETPhone, mETPassword;

    private String mCurrentPhone, mCurrentPassword;

    private Handler mLoginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCurrentPhone = ActivityUtils.getInputStrInfo(getIntent(), ActivityActionConfig.KEY_LOGIN_USER_NAME);
        mCurrentPassword = ActivityUtils.getInputStrInfo(getIntent(), ActivityActionConfig.KEY_LOGIN_USER_PASSWORD);

        mETPhone = (EditText) findViewById(R.id.login_account_et);
        if (!TextUtils.isEmpty(mCurrentPhone))
            mETPhone.setText(mCurrentPhone);
        mETPassword = (EditText) findViewById(R.id.login_password_et);
        if (!TextUtils.isEmpty(mCurrentPassword))
            mETPassword.setText(mCurrentPassword);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        Button register_btn = (Button) findViewById(R.id.login_register_btn);
        register_btn.setOnClickListener(this);
        Button forget_btn = (Button) findViewById(R.id.login_forget_btn);
        forget_btn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ActivityActionConfig.REQUEST_CODE_FORGET_PASSWORD:

                break;
            case ActivityActionConfig.REQUEST_CODE_REGISTER:
                handleRegisterResult(data, resultCode);
                break;
            default:
                throw new RuntimeException("nonsupport request code");

        }
    }

    @Override
    public void onClick(View v) {
        int view_id = v.getId();
        switch (view_id) {
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.login_forget_btn:
                doForgetPassword();
                break;
            case R.id.login_register_btn:
                doRegister();
                break;
            default:
                throw new RuntimeException("nonsupport operation");
        }
    }

    private void doLogin() {
        String current_phone = mETPhone.getText().toString();
        String current_password = mETPassword.getText().toString();
        if (TextUtils.isEmpty(current_phone) || TextUtils.isEmpty(current_password)) {
            Toast.makeText(this, R.string.info_wrong_input, Toast.LENGTH_LONG).show();
            return;
        } else {
            mCurrentPhone = current_phone;
            mCurrentPassword = current_password;
            //begin to login
        }
    }

    private void doForgetPassword(){
        if (!ActivityUtils.startForgetPasswordActivitySafe(this, mCurrentPhone)) {
            Toast.makeText(this, R.string.info_start_activity_false, Toast.LENGTH_SHORT).show();
        }
    }

    private void doRegister(){
        if (!ActivityUtils.startRegisterActivitySafe(this)) {
            Toast.makeText(this, R.string.info_start_activity_false, Toast.LENGTH_SHORT).show();
        }
    }

    private void handleRegisterResult(Intent result_intent, int result_code) {
        switch (result_code) {
            case ActivityActionConfig.RESULT_CODE_REGISTER_SUCCESS:
                mCurrentPhone = result_intent.getStringExtra(ActivityActionConfig.KEY_LOGIN_USER_NAME);
                mCurrentPassword = result_intent.getStringExtra(ActivityActionConfig.KEY_LOGIN_USER_PASSWORD);
                updateUI();
                if (getResources().getBoolean(R.bool.config_auto_login_after_register)) {
                    doLogin();
                }
                break;
            case ActivityActionConfig.RESULT_CODE_REGISTER_FALSE:
                break;
            default:
                throw new RuntimeException("nonsupport result code");
        }
    }

    private void updateUI(){
        if (!TextUtils.isEmpty(mCurrentPhone)) {
            mETPhone.setText(mCurrentPhone);
        } else {
            mETPhone.setText("");
        }

        if (!TextUtils.isEmpty(mCurrentPassword)) {
            mETPassword.setText(mCurrentPassword);
        } else {
            mETPassword.setText("");
        }
    }
}
