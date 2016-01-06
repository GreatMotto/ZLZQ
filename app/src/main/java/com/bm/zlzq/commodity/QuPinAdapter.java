package com.bm.zlzq.commodity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ProductListBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangwm on 2015/12/2.
 */
public class QuPinAdapter extends BaseAdapter {
    private Context mContext;
    private int flag;// 0买  1租
    private List<ProductListBean> list;

    public QuPinAdapter(Context mContext, int flag, List<ProductListBean> list) {
        super();
        this.mContext = mContext;
        this.flag = flag;
        this.list = list;
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
            convertView = View.inflate(mContext, R.layout.lv_item_qupin, null);
        }
        RoundImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
        TextView tv_price_old = ViewHolder.get(convertView, R.id.tv_price_old);
        TextView tv_month = ViewHolder.get(convertView, R.id.tv_month);
        TextView tv_count = ViewHolder.get(convertView, R.id.tv_count);

        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, ((BaseActivity) mContext).getImageOptions());

        tv_name.setText(list.get(position).name);
        tv_price.setText("¥" + list.get(position).Price);

        if (flag == 1) {
            tv_month.setVisibility(View.VISIBLE);
            tv_price_old.setVisibility(View.GONE);
            tv_count.setText(list.get(position).count + "人租用");
        } else {
            tv_month.setVisibility(View.GONE);
            tv_price_old.setVisibility(View.VISIBLE);
            tv_price_old.setText("¥" + list.get(position).oldPrice);
            tv_count.setText(list.get(position).count + "人购买");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuPinDetailActivity.class);
                intent.putExtra(Constant.FLAG, flag);
                intent.putExtra(Constant.ID, list.get(position).id);
                Log.e("productId", list.get(position).id);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }
}
