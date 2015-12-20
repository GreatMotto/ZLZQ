package com.bm.zlzq.my.collection;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyCollectionActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_collection);
        initView();
        initTitle("我的收藏");
    }

    private void initView() {

    }
}
