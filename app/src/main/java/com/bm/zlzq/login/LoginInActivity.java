package com.bm.zlzq.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.HomeActivity;
import com.bm.zlzq.utils.NewToast;

/**
 * Created by wangwm on 2015/12/1.
 */
public class LoginInActivity extends BaseActivity {
    private EditText et_mobile, et_password;
    private TextView tv_login, tv_forget;
    private ImageView iv_wx, iv_wb, iv_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login_in);
        ZLZQApplication.getInstance().addActivity(this);
        initView();
        initTitle("登录");
    }

    private void initView() {
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_wb = (ImageView) findViewById(R.id.iv_wb);
        iv_qq = (ImageView) findViewById(R.id.iv_qq);

        tv_login.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
    }

    private boolean canLogin() {
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号码不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString().trim())) {
            NewToast.show(this, "请输入密码", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_login:
                if (canLogin()) {
                    gotoOtherActivity(HomeActivity.class);
                }
                break;
            case R.id.tv_forget:
                gotoOtherActivity(ForgetPasswordActivity.class);
                break;
            case R.id.iv_wx:
                gotoOtherActivity(HomeActivity.class);
                break;
            case R.id.iv_wb:
                gotoOtherActivity(HomeActivity.class);
                break;
            case R.id.iv_qq:
                gotoOtherActivity(HomeActivity.class);
                break;
            default:
                break;
        }
    }
}
