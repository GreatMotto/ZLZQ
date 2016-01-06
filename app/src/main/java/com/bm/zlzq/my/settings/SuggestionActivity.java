package com.bm.zlzq.my.settings;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.UsersBean;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;

/**
 * Created by Administrator on 2015/12/20.
 */
public class SuggestionActivity extends BaseActivity implements APICallback.OnResposeListener {
    private EditText et_suggest;
    private TextView tv_send, tv_numbers;
    //    private UsersBean usersBean = new UsersBean();
    private int maxLength = 100;

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
        tv_numbers = (TextView) findViewById(R.id.tv_numbers);
        tv_numbers.setText("0/" + maxLength);
        et_suggest.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        et_suggest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                tv_numbers.setText(text.length() + "/" + maxLength);
            }
        });

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebServiceAPI.getInstance().feedback(et_suggest.getText().toString().trim(), SuggestionActivity.this, SuggestionActivity.this);
//                NewToast.show(SuggestionActivity.this, "已发送", NewToast.LENGTH_LONG);
                onBackPressed();
            }
        });
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    ProgressUtils.cancleProgressDialog();

//                    Log.e("list  OnSuccessData", list + "0.0");

                    NewToast.show(this, "谢谢您的宝贵意见!", NewToast.LENGTH_LONG);
                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
