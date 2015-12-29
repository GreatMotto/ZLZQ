package com.bm.zlzq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.SharedPreferencesHelper;
/**
 * 启动页
 * Created by wangwm on 2015/12/1.
 */
public class SplashActivity extends BaseActivity {
    private SharedPreferencesHelper sp;
    private boolean isOpenMain;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        sp = ZLZQApplication.getInstance().getSp();
//        //如果已经有登录状态  请求登录接口
//        if (sp.getBooleanValue(Constant.ISLOGIN)) {
//        }
        new Handler().postDelayed(new Runnable() {

            public void run() {
                /*
                 * Create an Intent that will start the Main WordPress Activity.
                 */
                isOpenMain = sp.getBooleanValue(Constant.ISOPENMAIN);
                if (isOpenMain) {
                    gotoFirstAC();
                } else {
                    // type = sp.getValue(Constant.SP_GOWHERE);
                    gotoOtherActivity(GuideActivity.class);
                    SplashActivity.this.finish();
                }

            }
        }, 3000);
    }

    private void gotoFirstAC() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        SplashActivity.this.startActivity(intent);
        SplashActivity.this.finish();
    }

}
