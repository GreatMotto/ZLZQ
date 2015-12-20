package com.bm.zlzq.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.commodity.QuPinDetailActivity;

/**
 * Created by wangwm on 2015/12/2.
 */
public class HomeAdapter extends BaseAdapter{
    private Context mContext;
    private int flag;

    public HomeAdapter(Context mContext, int flag){
        super();
        this.mContext = mContext;
        this.flag = flag;
    }


    @Override
    public int getCount() {
        return 5;
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
            convertView = View.inflate(mContext, R.layout.lv_item_home, null);
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
