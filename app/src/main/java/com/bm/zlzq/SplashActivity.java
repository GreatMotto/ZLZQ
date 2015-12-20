package com.bm.zlzq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bm.zlzq.home.HomeActivity;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.SharedPreferencesHelper;
/**
 * Created by wangwm on 2015/12/1.
 * 引导页
 */
public class SplashActivity extends BaseActivity {

    private SharedPreferencesHelper sp;
    private boolean isRight;//判断登录是否成功

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
//        sp = ZLZQApplication.getInstance().getSp();
//
//        //如果已经有登录状态  请求登录接口
//        if (sp.getBooleanValue(Constant.ISLOGIN)) {
//        }
        new Handler().postDelayed(new Runnable() {

            public void run() {
                /*
                 * Create an Intent that will start the Main WordPress Activity.
                 */
//                sp.putBooleanValue(Constant.ISLOGIN, isRight);
//                if (isRight) {
                    // type = sp.getValue(Constant.SP_GOWHERE);
                    gotoOtherActivity(HomeActivity.class);
                    SplashActivity.this.finish();
//                } else {
//                    gotoFirstAC();
//                }

            }
        }, 3000);
    }

    private void gotoFirstAC() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

}
