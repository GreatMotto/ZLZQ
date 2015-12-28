package com.bm.zlzq.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.commodity.QuPinActivity;
import com.bm.zlzq.my.MyFragment;
import com.bm.zlzq.shopcar.ShopCarActivity;

/**
 * Created by wangwm on 2015/12/1.
 */
public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg_group;
    private RadioButton rb_home, rb_qupin, rb_shopcar, rb_my;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private int from = 0;// 1-主页   2-我的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (from == 0){
            rb_home.setChecked(true);
        }else {
            rb_my.setChecked(true);
        }
    }

    private void initView() {
        rg_group = (RadioGroup) findViewById(R.id.rg_group);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_qupin = (RadioButton) findViewById(R.id.rb_qupin);
        rb_shopcar = (RadioButton) findViewById(R.id.rb_shopcar);
        rb_my = (RadioButton) findViewById(R.id.rb_my);

        rb_home.setChecked(true);
        setDefaultFragment();
        rg_group.setOnCheckedChangeListener(this);

    }

    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.replace(R.id.fl_home, homeFragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.rb_home:
                from = 0;
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.fl_home, homeFragment);
                break;
            case R.id.rb_qupin:
                gotoOtherActivity(QuPinActivity.class);
                break;
            case R.id.rb_shopcar:
                gotoOtherActivity(ShopCarActivity.class);
                break;
            case R.id.rb_my:
                from = 1;
                if (myFragment == null) {
                    myFragment = new MyFragment();
                }
                transaction.replace(R.id.fl_home, myFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }
}
