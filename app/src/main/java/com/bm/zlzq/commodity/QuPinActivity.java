package com.bm.zlzq.commodity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ProductListBean;
import com.bm.zlzq.bean.ProductType;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.home.search.SearchActivity;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.view.MultiLineRadioGroup;
import com.bm.zlzq.view.NoScrollGridView;
import com.bm.zlzq.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/3.
 */
public class QuPinActivity extends BaseActivity implements APICallback.OnResposeListener {
    private RefreshLayout rfl_lv;
    private Dialog alertDialog;
    private RadioGroup radioGroup;
    private RadioButton rb_mai, rb_zu;
    private LinearLayout ll_back, ll_search;
    private ListView lv_qupin;
    private QuPinAdapter adapter;
    private TextView tv_shaixuan;
    private List<String> typeIdList = new ArrayList<>();
    private List<ProductListBean> productList = new ArrayList<>();
    private List<ProductType> typeList = new ArrayList<>();
    private String range = "";
    private int flag = 0;// 0买  1租
    private int pageNum = 1, pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        setContentView(R.layout.ac_qupin);
        initView();
        initData();
    }

    private void initData() {
        if (flag == 0) {
            radioGroup.check(R.id.rb_mai);
            ProgressUtils.showProgressDialog("", this);
            WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
            WebServiceAPI.getInstance().productType(QuPinActivity.this, QuPinActivity.this);
        } else {
            radioGroup.check(R.id.rb_zu);
            ProgressUtils.showProgressDialog("", this);
            WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
            WebServiceAPI.getInstance().productType(QuPinActivity.this, QuPinActivity.this);
        }
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        rb_mai = (RadioButton) findViewById(R.id.rb_mai);
        rb_zu = (RadioButton) findViewById(R.id.rb_zu);
        tv_shaixuan = (TextView) findViewById(R.id.tv_shaixuan);
        ll_search = (LinearLayout) findViewById(R.id.ll_search);
        rfl_lv = (RefreshLayout) findViewById(R.id.rfl_lv);
        lv_qupin = (ListView) findViewById(R.id.lv_qupin);
        lv_qupin.addFooterView(rfl_lv.getFootView(), null, false);
        lv_qupin.setOnScrollListener(rfl_lv);

        ll_back.setOnClickListener(this);
        tv_shaixuan.setOnClickListener(this);
        ll_search.setOnClickListener(this);

        rfl_lv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
            }
        });
        rfl_lv.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                pageNum++;
                WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_mai:
                        flag = 0;
                        WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
                        break;
                    case R.id.rb_zu:
                        flag = 1;
                        WebServiceAPI.getInstance().productList(flag, "", "", "", "", "", pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
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
        switch (v.getId()) {
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

        final EditText et_minprice = (EditText) alertDialog.getWindow().findViewById(R.id.et_minprice);
        final EditText et_maxprice = (EditText) alertDialog.getWindow().findViewById(R.id.et_maxprice);

        range = "";
        MultiLineRadioGroup mlrg_radiogroup = (MultiLineRadioGroup) alertDialog.getWindow().findViewById(R.id.mlrg_radiogroup);
        mlrg_radiogroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MultiLineRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_all:
                        range = "";
                        break;
                    case R.id.rb_3_month_less:
                        range = "1";
                        break;
                    case R.id.rb_3_to_6_month:
                        range = "2";
                        break;
                    case R.id.rb_6_to_12_month:
                        range = "3";
                        break;
                    case R.id.rb_1_to_2_year:
                        range = "4";
                        break;
                    case R.id.rb_2_year_more:
                        range = "5";
                        break;
                    default:
                        break;
                }
            }
        });

        typeIdList.clear();
        NoScrollGridView gridview = (NoScrollGridView) alertDialog.getWindow().findViewById(R.id.gridview);
        gridview.setAdapter(new TypeGridAdapter(this, typeList, new TypeGridAdapter.BoxCheck() {
            @Override
            public void check(int position) {
                typeIdList.add(typeList.get(position).id);
                Log.e("typeIdList", typeIdList.toString());
            }
            @Override
            public void noCheck(int position) {
                typeIdList.remove(typeList.get(position).id);
                Log.e("typeIdList", typeIdList.toString());
            }
        }));

        TextView tv_cancel = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_cancel);
        TextView tv_sure = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebServiceAPI.getInstance().productList(
                        flag,
                        "",
                        typeIdList.toString().replace("[", "").replace("]", ""),
                        et_minprice.getText().toString().trim(),
                        et_maxprice.getText().toString().trim(),
                        range,pageNum, pageSize, QuPinActivity.this, QuPinActivity.this);
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
                        productList.clear();
                        rfl_lv.setRefreshing(false);
                        rfl_lv.setLoad_More(true);
                        productList.addAll(apiResponse.data.list);
                        adapter = new QuPinAdapter(QuPinActivity.this, flag, productList);
                        lv_qupin.setAdapter(adapter);
                    } else {
                        rfl_lv.setLoading(false);
                        productList.addAll(apiResponse.data.list);
                        adapter.notifyDataSetChanged();
                    }
                    if (apiResponse.data.page.totalPage <= pageNum) {
                        rfl_lv.setLoad_More(false);
                    }
                    break;
                case 1:
                    typeList.clear();
                    typeList.addAll(apiResponse.data.list);
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
