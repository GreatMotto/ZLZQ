package com.bm.zlzq.shopcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.ZLZQApplication;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/3.
 */
public class ShopCarActivity extends BaseActivity {
    private View footerview;
    private TextView tv_edit, tv_price, tv_buy;
    private ListView lv_shopcar;
    private RelativeLayout rl_check_all;
    private ImageView iv_check_all;
    private MyShopCarAdapter adapter;
    private List<ShopCarBean> list = new ArrayList<>();
    private List<ShopCarBean> listgoods = new ArrayList<>();
    private int i, shopnum;
    private boolean isCheckAll, isEditAll, isEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_shopcar);
        initTitle("购物车");
        initView();
    }

    private void initView() {
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        rl_check_all = (RelativeLayout) findViewById(R.id.rl_check_all);
        iv_check_all = (ImageView) findViewById(R.id.iv_check_all);
        lv_shopcar = (ListView) findViewById(R.id.lv_shopcar);
        footerview = LayoutInflater.from(this).inflate(R.layout.gwc_footview, null, false);
        lv_shopcar.addFooterView(footerview);

        tv_edit.setOnClickListener(this);
        rl_check_all.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_edit.setVisibility(View.VISIBLE);

        //模拟购物车数据
        shopnum = ZLZQApplication.getInstance().getSp().getIntValue(Constant.ADDNUM);
        for (int i = 0; i < shopnum - 1; i++) {
            ShopCarBean db = new ShopCarBean();
            db.pname = "进口蓝莓125g/份鲜果浆新鲜水果";
            db.price = "3288.00";
            db.count = "1";
            db.isCheck = false;
            list.add(db);
        }
        AdapterClic clic = new AdapterClic();
        adapter = new MyShopCarAdapter(this, list, clic);
        lv_shopcar.setAdapter(adapter);
    }

    public class MyShopCarAdapter extends BaseAdapter {
        private Context context;
        private List<ShopCarBean> list;
        private View.OnClickListener clic;

        public MyShopCarAdapter(Context context, List<ShopCarBean> list, View.OnClickListener clic) {
            this.context = context;
            this.list = list;
            this.clic = clic;
        }

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.shpcat_item, null);
            }
            TextView tv_add = ViewHolder.get(convertView, R.id.tv_add);
            TextView tv_cut = ViewHolder.get(convertView, R.id.tv_cut);
            TextView tv_cutnum = ViewHolder.get(convertView, R.id.tv_cutnum);
            TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
            ImageView iv_check = ViewHolder.get(convertView, R.id.iv_check);
            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
            TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
            LinearLayout ll_add_cut_num = ViewHolder.get(convertView, R.id.ll_add_cut_num);

            tv_cut.setOnClickListener(clic);
            tv_add.setOnClickListener(clic);
            iv_check.setOnClickListener(clic);
            tv_add.setTag(position);
            tv_cut.setTag(position);
            iv_check.setTag(position);
            if (list.get(position).isCheck) {
                iv_check.setImageResource(R.mipmap.gwc_xz);
            } else {
                iv_check.setImageResource(R.mipmap.gwc_wxz);
            }
            if (isEdit) {
                tv_num.setVisibility(View.GONE);
                ll_add_cut_num.setVisibility(View.VISIBLE);
            } else {
                tv_num.setVisibility(View.VISIBLE);
                ll_add_cut_num.setVisibility(View.GONE);
            }
            tv_cutnum.setText(String.valueOf(list.get(position).count));
//            tv_price.setText("¥" + list.get(position).price + ".00");
            if (list.get(position).equals("")) {
                tv_price.setText("¥" + list.get(position).price + "0.00");
            } else if (list.get(position).price.contains(".")) {
                tv_price.setText("¥" + list.get(position).price);
            } else {
                tv_price.setText("¥" + list.get(position).price + ".00");
            }
            tv_name.setText(list.get(position).pname);
            tv_num.setText("x" + list.get(position).count);
//            iv_shop.setImageURI(Uri.parse(Urls.PHOTO + list.get(position).path));
//            iv_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    WebServiceAPI.getInstance().deleteShopCar(list.get(position).id, MyShopCatActivity.this, context);
////                    showInfo(position);
//                    list.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
//
//
//            });
//            convertView.setOnClickListener(new View.OnClickListener() {//购物车跳转到商品详情
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, ShopDeataisActivity.class);
////                    intent.putExtra(Constant.SCORE,2);//购物车跳转到商品详情
//                    intent.putExtra(Constant.SCORE, 0);
//                    intent.putExtra(Constant.ID, list.get(position).productid);
//                    context.startActivity(intent);
//                }
//            });
            return convertView;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_check_all:
                if (isCheckAll) {
                    iv_check_all.setVisibility(View.GONE);
                    listgoods.clear();
                    tv_buy.setText("结算");
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isCheck = false;
                    }
                    isCheckAll = false;
                } else {
                    iv_check_all.setVisibility(View.VISIBLE);
                    listgoods.clear();
                    listgoods.addAll(list);
                    tv_buy.setText("结算(" + listgoods.size() + ")");
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isCheck = true;
                    }
                    isCheckAll = true;
                }
                adapter.notifyDataSetChanged();
                setNum();
                break;
            case R.id.tv_edit:
                if (isEditAll) {
                    tv_edit.setText("编辑");
                    isEdit = false;
                } else {
                    tv_edit.setText("完成");
                    isEdit = true;
                }
                isEditAll = !isEditAll;
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_buy:
                Intent intent = new Intent(this, ConfirmOrderActivity.class);
                intent.putExtra(Constant.CARLIST, (Serializable) listgoods);
                intent.putExtra(Constant.PRICE, tv_price.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private class AdapterClic implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int postion = (int) v.getTag();
            ShopCarBean good = list.get(postion);
            switch (v.getId()) {
                case R.id.tv_cut:
                    if (good.count.equals("1")) {
//                        Toast.makeText(MyShopCatActivity.this, "至少为1件", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    i = Integer.parseInt(good.count);
                    i--;
                    good.count = i + "";
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.tv_add:
                    i = Integer.parseInt(good.count);
                    i++;
                    good.count = i + "";
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.iv_check:
                    if (good.isCheck) {
                        good.isCheck = false;
                        listgoods.remove(good);
                        if (listgoods.size() < list.size()){
                            iv_check_all.setVisibility(View.GONE);
                            isCheckAll = false;
                        }
                        if (listgoods.size() == 0) {
                            tv_buy.setText("结算");
                        } else {
                            tv_buy.setText("结算(" + listgoods.size() + ")");
                        }
                    } else {
                        good.isCheck = true;
                        listgoods.add(good);
                        if (listgoods.size() == list.size()){
                            iv_check_all.setVisibility(View.VISIBLE);
                            isCheckAll = true;
                        }
                        tv_buy.setText("结算(" + listgoods.size() + ")");
                    }
                    adapter.notifyDataSetChanged();
                    break;
            }
            setNum();
        }
    }

    /*设置购物车总价格*/
    private void setNum() {
        float count = 0;
        float couns;
        int coun;
        int price;
        for (ShopCarBean bean : list) {
            if (list.size() != 0) {
                if (bean.isCheck) {
                    if (bean.price.contains(".")) {
                        coun = Integer.parseInt(bean.count);
                        couns = Float.parseFloat(bean.price);
                        count = count + coun * couns;
//                        }
                    } else {
                        coun = Integer.parseInt(bean.count);
                        price = Integer.parseInt(bean.price);
                        count = count + coun * price;
                    }
                }
            }
        }
        if (count == 0) {
            tv_price.setText("¥0.00");
        } else {
            String dataprice = count + "";
            if (dataprice.contains(".")) {
                tv_price.setText("¥" + dataprice + "0");
            } else {
                tv_price.setText("¥" + dataprice + ".00");
            }
        }
    }
}
