package com.bm.zlzq.home.message;

import android.os.Bundle;
import android.widget.ListView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/3.
 */
public class MessageActivity extends BaseActivity{
    private ListView lv_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_message);
        initTitle("通知消息");
        initView();
    }

    private void initView() {
        lv_root = (ListView) findViewById(R.id.lv_root);
        lv_root.setAdapter(new MsgRootAdapter(this));
    }
}
