package com.bm.zlzq.my.settings;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/20.
 */
public class AboutUsActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_about_us);
        initView();
        initTitle("关于我们");
    }

    private void initView() {

    }
}
