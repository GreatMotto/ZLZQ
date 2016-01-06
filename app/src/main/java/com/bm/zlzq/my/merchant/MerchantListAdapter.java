package com.bm.zlzq.my.merchant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MerchantListBean;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/23.
 */
public class MerchantListAdapter extends BaseAdapter{
    private Activity context;
    private LayoutInflater mInflater;
    private List<MerchantListBean> list = new ArrayList<>();

    public MerchantListAdapter(Activity context, List<MerchantListBean> list){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_merchant, null);
        }
        RoundImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_have_goods = ViewHolder.get(convertView, R.id.tv_have_goods);
        TextView tv_distance = ViewHolder.get(convertView, R.id.tv_distance);
        TextView tv_phone_number = ViewHolder.get(convertView, R.id.tv_phone_number);
        TextView tv_address = ViewHolder.get(convertView, R.id.tv_address);

        tv_have_goods.setVisibility(View.GONE);
        tv_phone_number.setVisibility(View.VISIBLE);

        ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, ((BaseActivity) context).getImageOptions());

        tv_name.setText(list.get(position).name);
        tv_phone_number.setText(list.get(position).mobile);
        tv_distance.setText(list.get(position).distance);
        tv_address.setText(list.get(position).address);

        return convertView;
    }
}
