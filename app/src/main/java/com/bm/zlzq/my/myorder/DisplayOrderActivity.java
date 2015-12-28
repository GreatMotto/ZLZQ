package com.bm.zlzq.my.myorder;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * 晒单评价
 * Created by wangwm on 2015/12/28.
 */
public class DisplayOrderActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_display_order);
        initView();
        initTitle("晒单评价");
    }

    private void initView() {

    }
}
