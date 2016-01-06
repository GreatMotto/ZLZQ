package com.bm.zlzq.commodity;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ImageBean;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.ViewPagerDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * GridView图片显示适配器
 */
public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<ImageBean> list;
    private int count = 0;

    public ImageGridAdapter(Context context, List<ImageBean> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
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
            convertView = View.inflate(mContext, R.layout.gv_item_image, null);
        }
        ImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);

        ImageLoader.getInstance().displayImage(Urls.PHOTO + list.get(position).path, iv_image, ((BaseActivity) mContext).getImageOptions());

        // 图片的点击事件
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerDialog dlg = new ViewPagerDialog(mContext, list, position);
                dlg.showViewPagerDialog();
            }
        });
        return convertView;
    }
}
