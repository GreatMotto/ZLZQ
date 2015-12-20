package com.bm.zlzq.my.settings;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by Administrator on 2015/12/20.
 */
public class SuggestionActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_suggestion);
        initView();
        initTitle("反馈意见");
    }

    private void initView() {

    }
}
