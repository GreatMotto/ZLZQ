package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.NewToast;

/**
 * Created by Administrator on 2015/12/20.
 */
public class SuggestionActivity extends BaseActivity{
    private EditText et_suggest;
    private TextView tv_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_suggestion);
        initView();
        initTitle("反馈意见");
    }

    private void initView() {
        et_suggest = (EditText) findViewById(R.id.et_suggest);
        tv_send = (TextView) findViewById(R.id.tv_send);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewToast.show(SuggestionActivity.this, "已发送", NewToast.LENGTH_LONG);
            }
        });
    }
}
