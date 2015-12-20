package com.bm.zlzq.shopcar;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantBean;
import com.bm.zlzq.utils.DialogUtil;
import com.bm.zlzq.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/15.
 */
public class MerchantChooseAdapter extends BaseAdapter{
    private Activity context;
    private List<MerchantBean> list = new ArrayList<>();
    AdapterClickInterface clickInterface;

    public MerchantChooseAdapter(Activity context, List<MerchantBean> list, AdapterClickInterface clickInterface){
        this.context = context;
        this.list = list;
        this.clickInterface = clickInterface;
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
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_merchant, null);
        }
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        tv_name.setText(list.get(position).name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onClick(list.get(position).name);
                context.finish();
            }
        });
        return convertView;
    }
}
