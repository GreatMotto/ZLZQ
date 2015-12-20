package com.bm.zlzq.commodity;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.utils.ViewHolder;

/**
 * Created by wangwm on 2015/12/2.
 */
public class QuPinAdapter extends BaseAdapter {
    private Context mContext;
    private int flag;// 0买  1租

    public QuPinAdapter(Context mContext, int flag) {
        super();
        this.mContext = mContext;
        this.flag = flag;
    }


    @Override
    public int getCount() {
        return 8;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.lv_item_qupin, null);
        }
        TextView tv_price_old = ViewHolder.get(convertView, R.id.tv_price_old);
        TextView tv_month = ViewHolder.get(convertView, R.id.tv_month);
        TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);

        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        if (flag == 1){
            tv_month.setVisibility(View.VISIBLE);
            tv_price_old.setVisibility(View.GONE);
            tv_num.setText("23人租用");
        } else {
            tv_month.setVisibility(View.GONE);
            tv_price_old.setVisibility(View.VISIBLE);
            tv_num.setText("23人购买");
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) mContext).goto1OtherActivity(QuPinDetailActivity.class, flag);
            }
        });

        return convertView;
    }
}
