package com.bm.zlzq.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.HotBean;
import com.bm.zlzq.commodity.QuPinDetailActivity;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by wangwm on 2015/12/2.
 */
public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private int flag;
    private LayoutInflater inflater;
    private List<HotBean> list;

    public HomeAdapter(Context mContext, List<HotBean> list) {
        super();
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.list = list;
    }

    public void setFlag(int flag){
        this.flag = flag;
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
            convertView = inflater.inflate(R.layout.lv_item_home, null);
        }
        RoundImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
        TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
        TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);

//        sdv_pic.setImageURI(Uri.parse(Urls.PHOTO + list.get(position).path));
        ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, ((BaseActivity) mContext).getImageOptions());

        tv_name.setText(list.get(position).name);
        tv_price.setText(list.get(position).price);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuPinDetailActivity.class);
                intent.putExtra(Constant.FLAG, flag);
                intent.putExtra(Constant.ID, list.get(position).id);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
