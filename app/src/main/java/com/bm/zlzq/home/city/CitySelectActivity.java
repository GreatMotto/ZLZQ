package com.bm.zlzq.home.city;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/3.
 */
public class CitySelectActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_city_select);
        initTitle("城市选择");
    }
}
