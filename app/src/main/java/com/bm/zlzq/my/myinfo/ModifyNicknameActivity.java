package com.bm.zlzq.my.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.NewToast;

/**
 * Created by wangwm on 2015/12/18.
 */
public class ModifyNicknameActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_nickname;
    private TextView tv_save;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_modify_nickname);
        initView();
        initData();
        initTitle("昵称修改");
    }

    private void initData() {


    }

    private void initView() {
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        tv_save = (TextView) findViewById(R.id.tv_save);

//        Intent intent = getIntent();
//        String nickname = intent.getStringExtra("nickname");


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_nickname.getText().toString())) {
                    WebServiceAPI.getInstance().updateInfo(et_nickname.getText().toString().trim(), "", ModifyNicknameActivity.this, ModifyNicknameActivity.this);
                    intent = new Intent();
                    intent.putExtra("nickname", et_nickname.getText().toString().trim());
                    ModifyNicknameActivity.this.setResult(RESULT_OK, intent);
                }
                onBackPressed();
            }
        });
    }

    @Override
    public void OnFailureData(String error, Integer tag) {
        NewToast.show(this, "网络不好,请稍后再试!", NewToast.LENGTH_LONG);
    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {

        NewToast.show(this, "修改成功!", NewToast.LENGTH_LONG);


    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
