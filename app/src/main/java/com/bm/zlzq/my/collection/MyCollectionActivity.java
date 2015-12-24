package com.bm.zlzq.my.collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.CollectionBean;
import com.bm.zlzq.commodity.QuPinDetailActivity;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.view.SwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyCollectionActivity extends BaseActivity{
    private SwipeListView swp_listview;
    private CollectionSwipeAdapter adapter;
    private List<CollectionBean> list = new ArrayList<>();
    private CollectionBean collectionBean = new CollectionBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_collection);
        initView();
        initTitle("我的收藏");
    }

    private void initView() {
        swp_listview = (SwipeListView) findViewById(R.id.swp_listview);

        // 模拟数据
        for (int i = 0; i < 6; i++) {
            collectionBean = new CollectionBean();
            collectionBean.goodsname = "山东蜜糖冬枣3斤 超甜冬枣 抢先水果新鲜";
            collectionBean.price = "¥38.00";
            list.add(collectionBean);
        }

        swp_listview.setRightViewWidth(130);
        adapter = new CollectionSwipeAdapter(this, swp_listview.getRightViewWidth(), list, new CollectionSwipeAdapter.IOnItemClickListener() {

            @Override
            public void onRightClick(View v, int position) {
                list.remove(position);
                swp_listview.ishiddenRight(true);
                adapter.notifyDataSetChanged();
            }
        });
        swp_listview.setAdapter(adapter);
        swp_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyCollectionActivity.this, QuPinDetailActivity.class);
                intent.putExtra(Constant.FLAG, 0);
                startActivity(intent);
            }
        });
    }
}
