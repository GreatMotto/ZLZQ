package com.bm.zlzq.my.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.shopcar.ConfirmOrderActivity;
import com.bm.zlzq.view.NoScrollListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 续租
 * Created by wangwm on 2015/12/28.
 */
public class ReletActivity extends BaseActivity{
    private View layout_footer;
    private TextView tv_order_number,tv_relet;
    private NoScrollListView nslv_relet;
    private ReletAdapter adapter;
    private List<ShopCarBean> list = new ArrayList<>();
    private List<ShopCarBean> goodslist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_relet);
        list = (List<ShopCarBean>) getIntent().getSerializableExtra(Constant.RELETLIST);
        initView();
        initTitle("续租申请");
    }

    private void initView() {
        tv_order_number = (TextView) findViewById(R.id.tv_order_number);
        nslv_relet = (NoScrollListView) findViewById(R.id.nslv_relet);
        layout_footer = LayoutInflater.from(this).inflate(R.layout.footview_relet, null);
        nslv_relet.addFooterView(layout_footer);
        tv_relet = (TextView) layout_footer.findViewById(R.id.tv_relet);
        tv_relet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReletActivity.this, ConfirmOrderActivity.class);
                intent.putExtra(Constant.FLAG, 1);
                intent.putExtra(Constant.CARLIST, (Serializable) goodslist);
                startActivity(intent);
            }
        });

        adapter = new ReletAdapter(this, list, new ReletAdapter.CheckInterface() {
            @Override
            public void onClick(int position, boolean checked) {
                if (checked){
                    goodslist.add(list.get(position));
                }else {
                    goodslist.remove(list.get(position));
                }
                list.get(position).isCheck = checked;
                adapter.notifyDataSetChanged();
            }
        });
        nslv_relet.setAdapter(adapter);
    }
}
