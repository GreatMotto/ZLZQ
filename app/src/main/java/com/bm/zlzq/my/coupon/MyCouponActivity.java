package com.bm.zlzq.my.coupon;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/15.
 */
public class MyCouponActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_mycoupon);
        initView();
        initTitle("优惠券");
    }

    private void initView() {

    }
}
