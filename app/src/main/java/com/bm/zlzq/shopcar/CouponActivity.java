package com.bm.zlzq.shopcar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.my.coupon.MyCouponActivity;

/**
 * Created by wangwm on 2015/12/15.
 */
public class CouponActivity extends BaseActivity {
    private TextView tv_get_coupon;
    private View layout_footer;
    private ListView lv_coupon;
    private CouponAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_coupon);
        initView();
        initTitle("优惠券");
    }

    private void initView() {
        lv_coupon = (ListView) findViewById(R.id.lv_coupon);
        layout_footer = LayoutInflater.from(this).inflate(R.layout.yhq_footer, null);
        lv_coupon.addFooterView(layout_footer);
        tv_get_coupon = (TextView) layout_footer.findViewById(R.id.tv_get_coupon);

        tv_get_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOtherActivity(MyCouponActivity.class);
            }
        });

        adapter = new CouponAdapter(this);
        lv_coupon.setAdapter(adapter);
    }

    public class CouponAdapter extends BaseAdapter {
        private Context context;

        public CouponAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_coupon, null);
            }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            return convertView;
        }
    }
}
