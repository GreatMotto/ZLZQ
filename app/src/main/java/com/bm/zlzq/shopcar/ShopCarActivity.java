package com.bm.zlzq.shopcar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.RoundImageView;
import com.bm.zlzq.view.SwipeListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/3.
 */
public class ShopCarActivity extends BaseActivity implements APICallback.OnResposeListener {
    private View footerview;
    private TextView tv_edit, tv_price, tv_buy;
    private SwipeListView swp_listview;
    private RelativeLayout rl_check_all;
    private ImageView iv_check_all;
    private ShopCarSwipeAdapter adapter;
    private List<ShopCarBean> shopCarList = new ArrayList<>();
    private List<ShopCarBean> listgoods = new ArrayList<>();
    private int i, shopnum;
    private boolean isCheckAll, isEditAll, isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_shopcar);
        initTitle("购物车");
        initView();
        initData();
    }

    private void initData() {
        ProgressUtils.showProgressDialog("", this);
        WebServiceAPI.getInstance().shopcarList(ShopCarActivity.this, ShopCarActivity.this);
    }

    private void initView() {
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        rl_check_all = (RelativeLayout) findViewById(R.id.rl_check_all);
        iv_check_all = (ImageView) findViewById(R.id.iv_check_all);
        swp_listview = (SwipeListView) findViewById(R.id.swp_listview);
        footerview = LayoutInflater.from(this).inflate(R.layout.footview_shopcar, null, false);
        swp_listview.addFooterView(footerview);

        tv_edit.setOnClickListener(this);
        rl_check_all.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_edit.setVisibility(View.VISIBLE);

    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            ProgressUtils.cancleProgressDialog();
            switch (tag) {
                case 0:
                    shopCarList.clear();
                    shopCarList.addAll(apiResponse.data.list);
                    AdapterClic clic = new AdapterClic();
                    adapter = new ShopCarSwipeAdapter(this, swp_listview.getRightViewWidth(), shopCarList, clic);
                    swp_listview.setAdapter(adapter);
                    break;
                case 1:
                    NewToast.show(this, "删除成功", NewToast.LENGTH_LONG);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }

    public class ShopCarSwipeAdapter extends BaseAdapter {
        private Context context;
        private List<ShopCarBean> list;
        private View.OnClickListener clic;
        private int mRightWidth = 0;

        public ShopCarSwipeAdapter(Context context, int rightWidth, List<ShopCarBean> list, View.OnClickListener clic) {
            this.context = context;
            this.mRightWidth = rightWidth;
            this.list = list;
            this.clic = clic;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.shpcat_item, null);
            }
            View item_left = ViewHolder.get(convertView, R.id.item_left);
            View item_right = ViewHolder.get(convertView, R.id.item_right);
            TextView item_right_text = ViewHolder.get(convertView, R.id.item_right_txt);

            RoundImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
            TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
            ImageView iv_check = ViewHolder.get(convertView, R.id.iv_check);
            TextView tv_invalid = ViewHolder.get(convertView, R.id.tv_invalid);
            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
            TextView tv_guige = ViewHolder.get(convertView, R.id.tv_guige);
            TextView tv_rend_date = ViewHolder.get(convertView, R.id.tv_rend_date);
            TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
            TextView tv_add = ViewHolder.get(convertView, R.id.tv_add);
            TextView tv_cut = ViewHolder.get(convertView, R.id.tv_cut);
            TextView tv_cutnum = ViewHolder.get(convertView, R.id.tv_cutnum);
            LinearLayout ll_add_cut_num = ViewHolder.get(convertView, R.id.ll_add_cut_num);

            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            item_left.setLayoutParams(lp1);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, LinearLayout.LayoutParams.MATCH_PARENT);
            item_right.setLayoutParams(lp2);
            item_right_text.setText("删除");

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

            if (TextUtils.isEmpty(list.get(position).priceTwo)) {
                tv_price.setText("¥0.00");
            } else {
                tv_price.setText("¥" + list.get(position).priceTwo);
            }

            if (!TextUtils.isEmpty(list.get(position).lease)) {
                tv_rend_date.setText("租赁期:" + list.get(position).lease + "个月");
            }

            if (list.get(position).status.equals("1")) {
                iv_check.setVisibility(View.GONE);
                tv_invalid.setVisibility(View.VISIBLE);
            } else {
                iv_check.setVisibility(View.VISIBLE);
                tv_invalid.setVisibility(View.GONE);
            }

            tv_name.setText(list.get(position).name);
            tv_guige.setText("规格:" + list.get(position).specs);
            tv_num.setText("x" + list.get(position).count);
//            sdv_pic.setImageURI(Uri.parse(Urls.PHOTO + list.get(position).path));
            ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, getImageOptions());
            item_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebServiceAPI.getInstance().delShopcar(list.get(position).id, ShopCarActivity.this,ShopCarActivity.this);
                    list.remove(position);
                    swp_listview.ishiddenRight(true);
                    adapter.notifyDataSetChanged();
                }
            });
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
                    for (int i = 0; i < shopCarList.size(); i++) {
                        shopCarList.get(i).isCheck = false;
                    }
                    isCheckAll = false;
                } else {
                    iv_check_all.setVisibility(View.VISIBLE);
                    listgoods.clear();
                    listgoods.addAll(shopCarList);
                    tv_buy.setText("结算(" + listgoods.size() + ")");
                    for (int i = 0; i < shopCarList.size(); i++) {
                        shopCarList.get(i).isCheck = true;
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
                intent.putExtra(Constant.FLAG, 0);
                intent.putExtra(Constant.LIST, (Serializable) listgoods);
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
            ShopCarBean good = shopCarList.get(postion);
            switch (v.getId()) {
                case R.id.tv_cut:
                    if (good.count.equals("1")) {
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
                        if (listgoods.size() < shopCarList.size()) {
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
                        if (listgoods.size() == shopCarList.size()) {
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
        for (ShopCarBean bean : shopCarList) {
            if (shopCarList.size() != 0) {
                if (bean.isCheck) {
                    if (bean.priceTwo.contains(".")) {
                        coun = Integer.parseInt(bean.count);
                        couns = Float.parseFloat(bean.priceTwo);
                        count = count + coun * couns;
//                        }
                    } else {
                        coun = Integer.parseInt(bean.count);
                        price = Integer.parseInt(bean.priceTwo);
                        count = count + coun * price;
                    }
                }
            }
        }
        if (count == 0) {
            tv_price.setText("¥0.00");
        } else {
            tv_price.setText("¥" + new DecimalFormat("0.00").format(count));
        }
    }
}
