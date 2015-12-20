package com.bm.zlzq.commodity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.search.SearchActivity;

/**
 * Created by wangwm on 2015/12/3.
 */
public class QuPinActivity extends BaseActivity{
    private Dialog alertDialog;
    private RadioGroup radioGroup;
    private RadioButton rb_mai, rb_zu;
    private LinearLayout ll_back, ll_search;
    private ListView lv_qupin;
    private QuPinAdapter adapter;
    private TextView tv_shaixuan;
    private int flag = 0;// 0买  1租
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        setContentView(R.layout.ac_qupin);
        initView();
        if (flag == 0){
            radioGroup.check(R.id.rb_mai);
        }else {
            radioGroup.check(R.id.rb_zu);
        }
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        rb_mai = (RadioButton) findViewById(R.id.rb_mai);
        rb_zu = (RadioButton) findViewById(R.id.rb_zu);
        lv_qupin = (ListView) findViewById(R.id.lv_qupin);
        tv_shaixuan = (TextView) findViewById(R.id.tv_shaixuan);
        ll_search = (LinearLayout) findViewById(R.id.ll_search);

        ll_back.setOnClickListener(this);
        tv_shaixuan.setOnClickListener(this);
        ll_search.setOnClickListener(this);

        adapter = new QuPinAdapter(this, flag);
        lv_qupin.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mai:
                        flag = 0;
                        adapter = new QuPinAdapter(QuPinActivity.this, flag);
                        lv_qupin.setAdapter(adapter);
                        break;
                    case R.id.rb_zu:
                        flag = 1;
                        adapter = new QuPinAdapter(QuPinActivity.this, flag);
                        lv_qupin.setAdapter(adapter);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_shaixuan:
                showDialog(this);
                break;
            case R.id.ll_search:
                gotoOtherActivity(SearchActivity.class);
                break;
            default:
                break;
        }
    }

    private void showDialog(Activity context) {
        alertDialog = new Dialog(context, R.style.MyDialogStyle);
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.RIGHT);
        window.setWindowAnimations(R.style.dlganimright);
        window.setContentView(R.layout.dlg_choose_specification);
        WindowManager manager = context.getWindowManager();
        Display display = manager.getDefaultDisplay();
        int height = display.getHeight();
        int width = display.getWidth();
        window.setLayout(width - 160, height);
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(true);
        TextView tv_cancel = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_cancel);
        TextView tv_sure = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }
}
