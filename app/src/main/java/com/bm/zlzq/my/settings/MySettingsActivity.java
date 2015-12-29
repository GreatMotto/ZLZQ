package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.login.LoginActivity;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MySettingsActivity extends BaseActivity{
    private RelativeLayout rl_account_and_security,rl_message,rl_clean_cache,rl_suggestion,rl_about_us;
    private LinearLayout ll_exit_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_settings);
        ZLZQApplication.getInstance().addActivity(this);
        initView();
        initTitle("系统设置");
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
        switch (v.getId()){
            case R.id.rl_account_and_security:
                gotoOtherActivity(AccountAndSecurityActivity.class);
                break;
            case R.id.rl_message:
                gotoOtherActivity(MyMessageActivity.class);
                break;
            case R.id.rl_clean_cache:

                break;
            case R.id.rl_suggestion:
                gotoOtherActivity(SuggestionActivity.class);
                break;
            case R.id.rl_about_us:
                gotoOtherActivity(AboutUsActivity.class);
                break;
            case R.id.ll_exit_account:
                gotoOtherActivity(LoginActivity.class);
                break;
            default:
                break;
        }
    }
}
