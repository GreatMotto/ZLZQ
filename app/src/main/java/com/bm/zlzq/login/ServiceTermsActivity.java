package com.bm.zlzq.login;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/2.
 */
public class ServiceTermsActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_service_terms);
        initTitle("服务条款");
    }
}
