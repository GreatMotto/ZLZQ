package com.bm.zlzq.my.address;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.AddressBean;
import com.bm.zlzq.view.SwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyAddressActivity extends BaseActivity {
    private TextView tv_edit;
    private SwipeListView swp_listview;
    private AdressSwipeAdapter adapter;
    private List<AddressBean> list = new ArrayList<>();
    private AddressBean[] db = new AddressBean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_address);
        initView();
        initTitle("管理收货地址");
    }

    private void initView() {
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setText("新增");
        tv_edit.setVisibility(View.VISIBLE);

        swp_listview = (SwipeListView) findViewById(R.id.swp_listview);

        // 模拟地址数据并初始化第一个为默认地址
        for (int i = 0; i < 5; i++) {
            db[i] = new AddressBean();
            db[i].name = "黄晓明" + i;
            if (i == 0){
                db[i].isDefault = "1";
            }else {
                db[i].isDefault = "0";
            }
            list.add(db[i]);
        }

        adapter = new AdressSwipeAdapter(this, swp_listview.getRightViewWidth(), list, new AdressSwipeAdapter.IOnItemClickListener() {
            @Override
            public void onRightClick(View v, int position) {
                list.remove(position);
                swp_listview.ishiddenRight(true);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDefaultAdrs(View v, int position) {
                list.clear();
                for (int i = 0; i < 5; i++) {
                    db[i] = new AddressBean();
                    db[i].name = "黄晓明" + i;
                    db[i].isDefault = "0";
                    list.add(db[i]);
                }
                list.get(position).isDefault = "1";
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDelClick(View v, int position) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onEditClick(View v, int position) {

            }
        });
        swp_listview.setAdapter(adapter);
    }
}
