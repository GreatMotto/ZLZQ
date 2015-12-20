package com.bm.zlzq.home.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.commodity.QuPinActivity;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/10.
 */
public class SearchActivity extends BaseActivity {
    private SharedPreferencesHelper sp = ZLZQApplication.getInstance().getSp();
    private TextView tv_sousuo;
    private LinearLayout ll_back, ll_delete;
    private ListView lv_search;
    private EditText et_search;
    private SearchAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_search);
        initView();
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_sousuo = (TextView) findViewById(R.id.tv_sousuo);
        lv_search = (ListView) findViewById(R.id.lv_search);

        ll_delete.setOnClickListener(this);
        tv_sousuo.setOnClickListener(this);
        ll_back.setOnClickListener(this);

        getSearchHistory();
        list.clear();
        if (sp.getListValue(Constant.HISTORY) != null) {
            list.addAll(sp.getListValue(Constant.HISTORY));
        }
        if (list.size() != 0) {
            if (list.size() > 10) {
                list.remove(list.get(0));
            }
            adapter = new SearchAdapter(this, list);
            lv_search.setAdapter(adapter);
        }

        lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoOtherActivity(QuPinActivity.class);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_delete:
                et_search.setText("");
                break;
            case R.id.tv_sousuo:
                getSearchHistory();
                onBackPressed();
//                gotoOtherActivity(QuPinActivity.class);
//                finish();
                break;
            default:
                break;
        }
    }

    private void getSearchHistory() {
        if (!TextUtils.isEmpty(et_search.getText().toString().trim())) {
            String history = et_search.getText().toString().trim();
            boolean isExist = false;
            for (String temp : list) {
                if (temp.equals(history)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                list.add(history);
                sp.putListValue(Constant.HISTORY, list);
            }
        }
//        sp.putListValue(Constant.HISTORY, list);
    }
}
