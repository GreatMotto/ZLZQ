package com.bm.zlzq;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.HomeActivity;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.SharedPreferencesHelper;


/**
 * 启动页
 * Created by wangwm on 2015/12/1.
 */
public class SplashActivity extends BaseActivity implements APICallback.OnResposeListener {
    private SharedPreferencesHelper sp;
    private boolean isOpenMain;
    private boolean login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        sp = ZLZQApplication.getInstance().getSp();
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
        login = sp.getBooleanValue(Constant.ISLOGIN);
        if (login && !TextUtils.isEmpty(sp.getValue(Constant.PHONE)) && !TextUtils.isEmpty(sp.getValue(Constant.PASSWORD))) {
            WebServiceAPI.getInstance().login(
                    sp.getValue(Constant.PHONE),
                    sp.getValue(Constant.PASSWORD), "", SplashActivity.this, SplashActivity.this);

        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }


    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    gotoOtherActivity(HomeActivity.class);
                    sp.putValue(Constant.USERID, apiResponse.data.id);
                    Log.e("userid", apiResponse.data.id);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
