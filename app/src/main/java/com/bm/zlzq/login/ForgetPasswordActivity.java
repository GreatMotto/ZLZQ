package com.bm.zlzq.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;

/**
 * Created by wangwm on 2015/12/2.
 */
public class ForgetPasswordActivity extends BaseActivity {
    private EditText et_mobile, et_verify_code, et_password, et_password_again;
    private TextView tv_send, tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_forget_password);
        initTitle("忘记密码");
        initView();
    }

    private void initView() {
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_verify_code = (EditText) findViewById(R.id.et_verify_code);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

        tv_send.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    private boolean canSubmit() {
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_verify_code.getText().toString().trim())) {
            NewToast.show(this, "请输入验证码", NewToast.LENGTH_LONG);
            return false;
        }
        if (et_verify_code.getText().toString().trim().length() < 6) {
            NewToast.show(this, "验证码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            NewToast.show(this, "请输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password_again.getText().toString().trim())) {
            NewToast.show(this, "请再次输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_password.getText().toString().trim().equals(et_password_again.getText().toString().trim())) {
            NewToast.show(this, "两次输入密码不匹配", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_send:
                NewToast.show(this, "验证码已发送", NewToast.LENGTH_LONG);
                break;
            case R.id.tv_submit:
                if (canSubmit()){
                    NewToast.show(this, "密码重置成功", NewToast.LENGTH_LONG);
                }
                break;
            default:
                break;
        }
    }
}
