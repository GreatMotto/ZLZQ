package com.bm.zlzq.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;

/**
 * Created by wangwm on 2015/12/1.
 */
public class LoginActivity extends BaseActivity {
    private ImageView iv_register, iv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ZLZQApplication.getInstance().finishActivity();
        ZLZQApplication.getInstance().addActivity(this);
        initView();
    }

    private void initView() {
        iv_register = (ImageView) findViewById(R.id.iv_register);
        iv_login = (ImageView) findViewById(R.id.iv_login);

        iv_register.setOnClickListener(this);
        iv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_register:
                gotoOtherActivity(RegisterActivity.class);
                break;
            case R.id.iv_login:
                gotoOtherActivity(LoginInActivity.class);
                break;
            default:
                break;
        }
    }
}
