package com.bm.zlzq.my.coupon;

import android.os.Bundle;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.CouponBean;
import com.bm.zlzq.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/15.
 */
public class MyCouponActivity extends BaseActivity {
    private NoScrollListView lv_my_coupon, lv_get_coupon;
    private CouponAdapter myAdapter;
    private CouponAdapter getAdapter;
    private List<CouponBean> mylist = new ArrayList<>();
    private List<CouponBean> getlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_mycoupon);
        initView();
        initTitle("优惠券");
    }

    private void initView() {
        lv_my_coupon = (NoScrollListView) findViewById(R.id.lv_my_coupon);
        lv_get_coupon = (NoScrollListView) findViewById(R.id.lv_get_coupon);

        // 模拟优惠券数据
        for (int i = 0; i < 2; i++) {
            CouponBean db = new CouponBean();
            db.enddate = "到期日期：2015-12-31";
            db.fullprice = "500";
            db.minusprice = "30";
            db.isget = "0";
            mylist.add(db);
        }

        for (int i = 0; i < 2; i++) {
            CouponBean db = new CouponBean();
            db.enddate = "到期日期：2015-12-31";
            db.minusprice = "50";
            db.isget = "1";
            getlist.add(db);
        }

        myAdapter = new CouponAdapter(this, mylist, false);
        getAdapter = new CouponAdapter(this, getlist, false);
        lv_my_coupon.setAdapter(myAdapter);
        lv_get_coupon.setAdapter(getAdapter);
    }
}
