package com.bm.zlzq.my.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;

/**
 * Created by wangwm on 2015/12/18.
 */
public class ModifyNicknameActivity extends BaseActivity {
    private EditText et_nickname;
    private TextView tv_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_modify_nickname);
        initView();
        initTitle("昵称修改");
    }

    private void initView() {
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        tv_save = (TextView) findViewById(R.id.tv_save);

        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_nickname.getText().toString())) {
                    Intent intent = new Intent();
                    intent.putExtra("nickname", et_nickname.getText().toString());
                    ModifyNicknameActivity.this.setResult(RESULT_OK, intent);
                }
                onBackPressed();
            }
        });
    }
}
