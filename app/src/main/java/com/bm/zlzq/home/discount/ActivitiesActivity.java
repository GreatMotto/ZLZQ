package com.bm.zlzq.home.discount;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.constant.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangwm on 2015/12/3.
 */
public class ActivitiesActivity extends BaseActivity implements APICallback.OnResposeListener {
    private LinearLayout ll_goodsname;
    private ImageView iv_image;
    private TextView tv_description, tv_title,tv_mobile,tv_address;
    private int flag = 0;// 0-首页banner图跳转  1-商户详情跳转
    private String title = "", id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_activities);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        title = getIntent().getStringExtra(Constant.TITLE);
        id = getIntent().getStringExtra(Constant.ID);
        initView();
        initData();
    }

    private void initData() {
        if (flag == 0) {
            initTitle("满减特惠");
            tv_mobile.setVisibility(View.GONE);
            tv_address.setVisibility(View.GONE);
            WebServiceAPI.getInstance().activityInfo(id, ActivitiesActivity.this, ActivitiesActivity.this);
        } else {
            initTitle(title);
            ll_goodsname.setVisibility(View.GONE);
            WebServiceAPI.getInstance().merchantInfo(id, ActivitiesActivity.this, ActivitiesActivity.this);
        }
    }

    private void initView() {
        iv_image = (ImageView) findViewById(R.id.iv_image);
        ll_goodsname = (LinearLayout) findViewById(R.id.ll_goodsname);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_description = (TextView) findViewById(R.id.tv_description);

    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    ImageLoader.getInstance().displayImage(Urls.PHOTO + apiResponse.data.activity.path, iv_image, getImageOptions());
                    tv_title.setText(apiResponse.data.activity.title);
                    tv_description.setText(apiResponse.data.activity.description);
                    break;
                case 1:
                    ImageLoader.getInstance().displayImage(Urls.PHOTO + apiResponse.data.merchant.path, iv_image, getImageOptions());
                    tv_mobile.setText(apiResponse.data.merchant.mobile);
                    tv_address.setText(apiResponse.data.merchant.address);
                    tv_description.setText(apiResponse.data.merchant.description);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
