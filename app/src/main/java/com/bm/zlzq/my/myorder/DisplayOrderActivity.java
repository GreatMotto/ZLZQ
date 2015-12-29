package com.bm.zlzq.my.myorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.ShopCarBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.upload.AlbumFileActivity;
import com.bm.zlzq.upload.Bimp;
import com.bm.zlzq.upload.FileUtils;
import com.bm.zlzq.utils.ImageUtils;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ViewHolder;
import com.bm.zlzq.view.NoScrollListView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 晒单评价
 * Created by wangwm on 2015/12/28.
 */
public class DisplayOrderActivity extends BaseActivity {
    private TextView tv_order_number, tv_publish;
    private NoScrollListView nslv_order;
    private View layout_footer;
    private List<ShopCarBean> list = new ArrayList<>();
    private DisplayOrderAdapter orderAdapter;
    private GridAdapter gridAdapter;
    private static final int RESULT_CAMERA = 1;// 相机
    private List<String> listpath;
    private List<File> files = new ArrayList<File>();
    private Uri imageUri;
    private String pic_path;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_display_order);
        list = (List<ShopCarBean>) getIntent().getSerializableExtra(Constant.RELETLIST);
        initView();
        initTitle("晒单评价");
    }

    private void initView() {
        tv_order_number = (TextView) findViewById(R.id.tv_order_number);
        nslv_order = (NoScrollListView) findViewById(R.id.nslv_order);
        layout_footer = LayoutInflater.from(this).inflate(R.layout.footview_publish_order, null);
        nslv_order.addFooterView(layout_footer);
        tv_publish = (TextView) layout_footer.findViewById(R.id.tv_publish);
        tv_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewToast.show(DisplayOrderActivity.this, "发布成功", NewToast.LENGTH_LONG);
                onBackPressed();
            }
        });

        orderAdapter = new DisplayOrderAdapter(this, list);
        nslv_order.setAdapter(orderAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CAMERA:
                if (Bimp.drr.size() < 6 && imageUri != null) {
                    Bimp.drr.add(pic_path);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileUtils.deleteDir();
    }

    @Override
    protected void onRestart() {
        gridAdapter.update();
        super.onRestart();
    }

    public class DisplayOrderAdapter extends BaseAdapter {
        private Context context;
        private LayoutInflater mInflater;
        private List<ShopCarBean> list;

        public DisplayOrderAdapter(Context context, List<ShopCarBean> list) {
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_display, null);
            }
            TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
            TextView tv_num = ViewHolder.get(convertView, R.id.tv_num);
            EditText et_comment = ViewHolder.get(convertView, R.id.et_comment);
            GridView gridview = ViewHolder.get(convertView, R.id.gridview);

            tv_name.setText(list.get(position).name);
            tv_price.setText("￥" + list.get(position).price);
            tv_num.setText("x" + list.get(position).count);

            gridAdapter = new GridAdapter(context);
            gridview.setAdapter(gridAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int itemPosition, long id) {
                    if (itemPosition == Bimp.bmp.size()) {
                        currentPosition = position;
                        showPhoto();
                    } else {
                        String newStr = Bimp.drr.get(itemPosition).substring(
                                Bimp.drr.get(itemPosition).lastIndexOf("/") + 1,
                                Bimp.drr.get(itemPosition).lastIndexOf("."));
                        Bimp.bmp.remove(itemPosition);
                        Bimp.drr.remove(itemPosition);
                        Bimp.max = Bimp.drr.size();
                        FileUtils.delFile(newStr + ".JPEG");
                        View v = nslv_order.getChildAt(currentPosition);
                        GridView gridView = (GridView) v.findViewById(R.id.gridview);
                        gridView.setAdapter(gridAdapter);
//                        gridAdapter.notifyDataSetChanged();
//                        orderAdapter.notifyDataSetChanged();
                    }
                }
            });

            return convertView;
        }
    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            return (Bimp.bmp.size() + 1);
        }

        public Object getItem(int arg0) {

            return null;
        }

        public long getItemId(int arg0) {

            return 0;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int itemposition, View convertView, ViewGroup parent) {
            final int coord = itemposition;
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_upload_grid, parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView
                        .findViewById(R.id.item_grida_image);
                holder.iv_delete = (ImageView) convertView
                        .findViewById(R.id.iv_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (itemposition == Bimp.bmp.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.tianjia));
                holder.iv_delete.setVisibility(View.GONE);
                if (itemposition == 6) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.bmp.get(itemposition));
                holder.iv_delete.setVisibility(View.VISIBLE);
            }

            return convertView;
        }

        public class ViewHolder {
            public ImageView image, iv_delete;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        View v = nslv_order.getChildAt(currentPosition);
                        GridView gridView = (GridView) v.findViewById(R.id.gridview);
                        gridView.setAdapter(gridAdapter);
//                        gridAdapter.notifyDataSetChanged();
//                        orderAdapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimp.max == Bimp.drr.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            try {
                                String path = Bimp.drr.get(Bimp.max);
                                System.out.println(path);
                                Bitmap bm = Bimp.revitionImageSize(path);
                                Bimp.bmp.add(bm);
                                String newStr = path.substring(
                                        path.lastIndexOf("/") + 1,
                                        path.lastIndexOf("."));
                                FileUtils.saveBitmap(bm, "" + newStr);
                                Bimp.max += 1;
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    // 弹出对话框,提示选择拍照上传或者从相册上传
    private void showPhoto() {
        showdialog(Gravity.BOTTOM);
        alertDialog.getWindow().setContentView(R.layout.dialog_photo);
        TextView tv_camera = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_camera);
        TextView tv_album = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_album);
        TextView tv_cancel = (TextView) alertDialog.getWindow().findViewById(
                R.id.tv_cancel);
        tv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();// 拍照上传
                alertDialog.cancel();
            }
        });
        tv_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayOrderActivity.this, AlbumFileActivity.class);
                startActivity(intent);// 相册上传
                alertDialog.cancel();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    /**
     * 调用相机拍照
     */
    public void takePhoto() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File dir = Environment.getExternalStorageDirectory();

            File file = new File(dir, new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
                    + ".jpg");
            pic_path = file.getPath();
            imageUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, RESULT_CAMERA);
        } else {
            NewToast.show(this, "请确认已经插入SD卡", Toast.LENGTH_LONG);
        }
    }

    // 获取图片路径
    public void getPicturesPath() {
        // 高清的压缩图片全部就在  list 路径里面了
        // 高清的压缩过的 bmp 对象  都在 Bimp.bmp里面
        listpath = new ArrayList<String>();
        for (int i = 0; i < Bimp.drr.size(); i++) {
            String Str = Bimp.drr.get(i).substring(
                    Bimp.drr.get(i).lastIndexOf("/") + 1,
                    Bimp.drr.get(i).lastIndexOf("."));
            listpath.add(FileUtils.SDPATH + Str + ".JPEG");
        }
    }

    // 获取图片文件
    public void getPictures() {
        getPicturesPath();
        files.clear();
        for (int i = 0; i < listpath.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeFile(listpath.get(i));
            File file = ImageUtils.saveBitmap(bitmap, "image" + i + ".jpg");
            if (file != null) {
                files.add(file);
            } else {
                NewToast.show(this, "图像保存失败，请检查SD卡是否连接正常", Toast.LENGTH_LONG);
            }
        }
    }
}
