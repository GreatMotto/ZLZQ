package com.bm.zlzq.my.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.bean.UsersBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.login.LoginActivity;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.SharedPreferencesHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MySettingsActivity extends BaseActivity implements APICallback.OnResposeListener {
    private RelativeLayout rl_account_and_security, rl_message, rl_clean_cache, rl_suggestion, rl_about_us;
    private LinearLayout ll_exit_account;
    private SharedPreferencesHelper sp;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_settings);
        ZLZQApplication.getInstance().addActivity(this);
        sp = ZLZQApplication.getInstance().getSp();
        initView();
        initData();
        initTitle("系统设置");
    }

    private void initData() {
        WebServiceAPI.getInstance().about(MySettingsActivity.this, MySettingsActivity.this);

    }

    private void initView() {
        rl_account_and_security = (RelativeLayout) findViewById(R.id.rl_account_and_security);
        rl_message = (RelativeLayout) findViewById(R.id.rl_message);
        rl_clean_cache = (RelativeLayout) findViewById(R.id.rl_clean_cache);
        rl_suggestion = (RelativeLayout) findViewById(R.id.rl_suggestion);
        rl_about_us = (RelativeLayout) findViewById(R.id.rl_about_us);
        ll_exit_account = (LinearLayout) findViewById(R.id.ll_exit_account);

        rl_account_and_security.setOnClickListener(this);
        rl_message.setOnClickListener(this);
        rl_clean_cache.setOnClickListener(this);
        rl_suggestion.setOnClickListener(this);
        rl_about_us.setOnClickListener(this);
        ll_exit_account.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_account_and_security:
                gotoOtherActivity(AccountAndSecurityActivity.class);
                break;
            case R.id.rl_message:
                gotoOtherActivity(MyMessageActivity.class);
                break;
            case R.id.rl_clean_cache:
                ImageLoader.getInstance().clearMemoryCache();
                ImageLoader.getInstance().clearDiskCache();
                NewToast.show(this, "缓存已清理", NewToast.LENGTH_SHORT);
                break;
            case R.id.rl_suggestion:
                gotoOtherActivity(SuggestionActivity.class);
                break;
            case R.id.rl_about_us:
                Intent intent = new Intent();
                intent.putExtra("url", url);
                intent.setClass(this, AboutUsActivity.class);
                startActivity(intent);

//                gotoOtherActivity(AboutUsActivity.class, apiResponse.data.users.content);
                break;
            case R.id.ll_exit_account:
                gotoOtherActivity(LoginActivity.class);
//                sp.remove(Constant.PASSWORD);
//                sp.remove(Constant.PHONE);
//                sp.remove(Constant.ISLOGIN);
                sp.putValue(Constant.PASSWORD, "");
                sp.putValue(Constant.PHONE, "");

                break;
            default:
                break;
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
                    ProgressUtils.cancleProgressDialog();
                    url = apiResponse.data.content;
//                    Log.e("url", apiResponse.data.content + "....");
                    NewToast.show(this, "关于我们!", NewToast.LENGTH_LONG);
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
