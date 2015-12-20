package com.bm.zlzq;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.zlzq.bean.ImageBean;
import com.bm.zlzq.constant.Constant;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/1.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private int x, y;
    private LinearLayout ll_back, ll_to_home;
    private ImageView iv_to_home;
    private TextView tv_content;
    private ProgressDialog pd;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void initTitle(String content) {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_content = (TextView) findViewById(R.id.tv_content);
        ll_back.setOnClickListener(this);
        tv_content.setText(content);
    }

    // 返回时关闭软键盘
    public void CloseKeyboard() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        // TODO Auto-generated method stub
        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        CloseKeyboard();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) ev.getX();
                y = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs((int) ev.getY() - y) <= Math.abs((int) ev.getX() - x)) {
                    View v = getCurrentFocus();
                    if (isShouldHideInput(v, ev)) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                    x = 0;
                    y = 0;
                    return super.dispatchTouchEvent(ev);
                }
                x = 0;
                y = 0;
                break;

            default:
                break;
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    //根据触摸位置计算是否隐藏输入法
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1] - 20;
            int bottom = top + v.getHeight() + 40;
            int right = left + v.getWidth() + 10000;
            if (event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 跳转到其他Activity
    public void gotoOtherActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    // 带一个参数跳转到其他Activity
    public void goto1OtherActivity(Class<?> cls, int flag) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        intent.putExtra(Constant.FLAG, flag);
        startActivity(intent);
    }

    public List<ImageBean> getImageList(String[] strs, int size) {
        List<ImageBean> list = new ArrayList<>();
        if (size > strs.length) {
            size = strs.length;
        }
        ImageBean bean;
        for (int i = 0; i < size; i++) {
            bean = new ImageBean();
            bean.path = strs[i];
            list.add(bean);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
