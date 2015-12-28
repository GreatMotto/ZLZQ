package com.bm.zlzq.shopcar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.CouponBean;
import com.bm.zlzq.my.coupon.CouponAdapter;
import com.bm.zlzq.my.coupon.MyCouponActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/15.
 */
public class OrderCouponActivity extends BaseActivity {
    private TextView tv_get_coupon;
    private View layout_footer;
    private ListView lv_coupon;
    private CouponAdapter adapter;
    private List<CouponBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_coupon);
        initView();
        initTitle("优惠券");
    }

    private void initView() {
        lv_coupon = (ListView) findViewById(R.id.lv_coupon);
        layout_footer = LayoutInflater.from(this).inflate(R.layout.footview_coupon, null);
        lv_coupon.addFooterView(layout_footer);
        tv_get_coupon = (TextView) layout_footer.findViewById(R.id.tv_get_coupon);

        tv_get_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOtherActivity(MyCouponActivity.class);
            }
        });

        // 模拟优惠券数据
        for (int i = 0; i < 2; i++) {
            CouponBean db = new CouponBean();
            db.enddate = "到期日期：2015-12-31";
            db.fullprice = "500";
            db.minusprice = "30";
            db.isget = "0";
            list.add(db);
        }

        for (int i = 0; i < 2; i++) {
            CouponBean db = new CouponBean();
            db.enddate = "到期日期：2015-12-31";
            db.minusprice = "50";
            db.isget = "0";
            list.add(db);
        }

        adapter = new CouponAdapter(this, list, true);
        lv_coupon.setAdapter(adapter);
    }

}
