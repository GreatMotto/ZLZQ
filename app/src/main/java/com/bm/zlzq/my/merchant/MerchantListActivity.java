package com.bm.zlzq.my.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantListBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.discount.ActivitiesActivity;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MerchantListActivity extends BaseActivity implements APICallback.OnResposeListener {
    private LinearLayout ll_back,ll_delete;
    private RefreshLayout rfl_lv;
    private EditText et_search;
    private ListView lv_merchant;
    private TextView tv_sousuo;
    private List<MerchantListBean> merchantList = new ArrayList<>();
    private MerchantListAdapter adapter;
    private int pageNum = 1, pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_merchant_list);
        initView();
        initData();
    }

    private void initData() {
        WebServiceAPI.getInstance().merchantList("", "", "",pageNum,pageSize,MerchantListActivity.this,MerchantListActivity.this);
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        rfl_lv = (RefreshLayout) findViewById(R.id.rfl_lv);
        et_search = (EditText) findViewById(R.id.et_search);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        tv_sousuo = (TextView) findViewById(R.id.tv_sousuo);
        lv_merchant = (ListView) findViewById(R.id.lv_merchant);
        lv_merchant.addFooterView(rfl_lv.getFootView(), null, false);
        lv_merchant.setOnScrollListener(rfl_lv);
        et_search.setHint("请输入商户名");
        tv_sousuo.setText("筛选");

        ll_back.setOnClickListener(this);
        tv_sousuo.setOnClickListener(this);
        ll_delete.setOnClickListener(this);

        rfl_lv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                WebServiceAPI.getInstance().merchantList("", "", "", pageNum, pageSize, MerchantListActivity.this, MerchantListActivity.this);            }
        });
        rfl_lv.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                pageNum++;
                WebServiceAPI.getInstance().merchantList("", "", "", pageNum, pageSize, MerchantListActivity.this, MerchantListActivity.this);            }
        });
//        //模拟商户数据
//        for (int i = 0; i < 10; i++) {
//            MerchantBean db = new MerchantBean();
//            db.name = "宝隆便利店 " + i;
//            db.distance = "23km";
//            db.phonenumber = "电话:020-555555";
//            db.address = "地址:徐州市中山南路169号";
//            list.add(db);
//        }
        adapter = new MerchantListAdapter(this, merchantList);
        lv_merchant.setAdapter(adapter);
        lv_merchant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MerchantListActivity.this, ActivitiesActivity.class);
                intent.putExtra(Constant.FLAG, 1);
                intent.putExtra(Constant.TITLE, merchantList.get(position).name);
                intent.putExtra(Constant.ID, merchantList.get(position).id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                break;
            case R.id.ll_delete:
                et_search.setText("");
                break;
            case R.id.tv_sousuo:
                WebServiceAPI.getInstance().merchantList("", "",
                        et_search.getText().toString().trim(),
                        pageNum,pageSize,MerchantListActivity.this,MerchantListActivity.this);
                break;
            default:
                break;
        }
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
                    if (pageNum == 1) {
                        merchantList.clear();
                        rfl_lv.setRefreshing(false);
                        rfl_lv.setLoad_More(true);
                        merchantList.addAll(apiResponse.data.list);
                        adapter.notifyDataSetInvalidated();
                    } else {
                        rfl_lv.setLoading(false);
                        merchantList.addAll(apiResponse.data.list);
                        adapter.notifyDataSetChanged();
                    }
                    if (apiResponse.data.page.totalPage <= pageNum) {
                        rfl_lv.setLoad_More(false);
                    }
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
