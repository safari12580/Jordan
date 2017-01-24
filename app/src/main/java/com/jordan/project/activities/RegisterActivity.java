package com.jordan.project.activities;

import android.app.Activity;
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
import com.jordan.project.utils.CommonUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mETPhoneNumber, mETAuthCode, mETNick, mETRole, mETICInfo, mETPassword, mETRePassword;

    private Handler mRegisterHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mETPhoneNumber = (EditText) findViewById(R.id.register_phone_number_et);
        mETAuthCode = (EditText) findViewById(R.id.register_author_code_et);
        mETNick = (EditText) findViewById(R.id.register_nick_name_et);
        mETRole = (EditText) findViewById(R.id.register_role_et);
        mETICInfo = (EditText) findViewById(R.id.register_ic_info_et);
        mETPassword = (EditText) findViewById(R.id.register_password_et);
        mETRePassword = (EditText) findViewById(R.id.register_re_password_et);

        Button confirm_btn = (Button) findViewById(R.id.register_confirm_btn);
        confirm_btn.setOnClickListener(this);
        Button cancel_btn = (Button) findViewById(R.id.register_cancel_btn);
        cancel_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_confirm_btn:
                if (checkRegisterInfo()){
                    doRegister();
                }
                break;
            case R.id.register_cancel_btn:
                setResult(ActivityActionConfig.RESULT_CODE_REGISTER_CANCEL);
                finish();
                break;
        }
    }

    private boolean checkRegisterInfo(){
        String current_phone = mETPhoneNumber.getText().toString();
        if (TextUtils.isEmpty(current_phone)) {
            Toast.makeText(this, R.string.info_empty_phone, Toast.LENGTH_LONG).show();
            return false;
        }

        if (!CommonUtils.isCellphone(current_phone)) {
            Toast.makeText(this, R.string.info_wrong_phone, Toast.LENGTH_LONG).show();
            return false;
        }

        String current_code = mETAuthCode.getText().toString();
        if (TextUtils.isEmpty(current_code)) {
            Toast.makeText(this, R.string.info_empty_code, Toast.LENGTH_LONG).show();
            return false;
        }

        String current_password = mETPassword.getText().toString();
        String current_re_password = mETRePassword.getText().toString();
        if (TextUtils.isEmpty(current_password) || TextUtils.isEmpty(current_re_password)) {
            Toast.makeText(this, R.string.info_empty_password, Toast.LENGTH_LONG).show();
            return false;
        }
        if (!current_password.equals(current_re_password)) {
            Toast.makeText(this, R.string.info_password_not_equals, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void doRegister(){
        String current_phone = mETPhoneNumber.getText().toString();
        String current_password = mETPassword.getText().toString();
        String current_code = mETAuthCode.getText().toString();
        String current_nick = mETNick.getText().toString();
        String current_role = mETRole.getText().toString();
        String current_ic_info = mETICInfo.getText().toString();

    }

    private void handleResultIfSuccess(){
        String current_phone = mETPhoneNumber.getText().toString();
        String current_password = mETPassword.getText().toString();
        getIntent().putExtra(ActivityActionConfig.KEY_LOGIN_USER_NAME, current_phone);
        getIntent().putExtra(ActivityActionConfig.KEY_LOGIN_USER_PASSWORD, current_password);
        setResult(ActivityActionConfig.RESULT_CODE_REGISTER_SUCCESS);
        finish();
    }

    private void handleResultIfFalse(){
        setResult(ActivityActionConfig.RESULT_CODE_REGISTER_FALSE);
        finish();
    }

}
