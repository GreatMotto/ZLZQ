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
    private static String APPKEY = "e4b51904f871";
    private static String APPSECRET = "ddd6dd9cd768dcad46a916fd8933c978";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        ZLZQApplication.getInstance().finishActivity();
        ZLZQApplication.getInstance().addActivity(this);
        initView();
//        initSDK();
    }

//    private void initSDK() {
//        SMSSDK.initSDK(LoginActivity.this, APPKEY, APPSECRET);
//        final Handler handler = new Handler((Handler.Callback) this);
//        EventHandler eventHandler = new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                Message msg = new Message();
//                msg.arg1 = event;
//                msg.arg2 = result;
//                msg.obj = data;
//                handler.sendMessage(msg);
//            }
//        };
//        // 注册回调监听接口
//        SMSSDK.registerEventHandler(eventHandler);
//
//    }


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
