package com.bm.zlzq.shopcar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.home.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/15.
 */
public class MerchantChooseActivity extends BaseActivity{
    private TextView tv_all, tv_have_goods;
    private ListView lv_merchant;
    private ImageView iv_search;
    private MerchantChooseAdapter adapter;
    private List<MerchantBean> list = new ArrayList<>();
    public static String shopName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_merchant_choose);
        initView();
        initTitle("商户选择");
    }

    AdapterClickInterface clickInterface = new AdapterClickInterface() {
        @Override
        public void onClick(String s) {
//            NewToast.show(MerchantChooseActivity.this, s, NewToast.LENGTH_LONG);
            shopName = s;
        }

        @Override
        public void isFirst(int flag) {

        }
    };

    private void initView() {
        iv_search = (ImageView) findViewById(R.id.iv_search);
        lv_merchant = (ListView) findViewById(R.id.lv_merchant);
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_have_goods = (TextView) findViewById(R.id.tv_have_goods);

        iv_search.setVisibility(View.VISIBLE);

        iv_search.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_have_goods.setOnClickListener(this);

        //模拟商户数据
        for (int i = 0; i < 10; i++) {
            MerchantBean db = new MerchantBean();
            db.name = "宝隆便利店 " + i;
            list.add(db);
        }
        adapter = new MerchantChooseAdapter(this, list, clickInterface);
        lv_merchant.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_search:
                gotoOtherActivity(SearchActivity.class);
                break;
            default:
                break;
        }
    }
}
