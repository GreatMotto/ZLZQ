package com.bm.zlzq.my;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.Urls;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.UsersBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.my.address.MyAddressActivity;
import com.bm.zlzq.my.collection.MyCollectionActivity;
import com.bm.zlzq.my.coupon.MyCouponActivity;
import com.bm.zlzq.my.manager.MyManagerActivity;
import com.bm.zlzq.my.merchant.MerchantListActivity;
import com.bm.zlzq.my.myinfo.PersonalInfoActivity;
import com.bm.zlzq.my.myorder.MyOrderActivity;
import com.bm.zlzq.my.recharge.MyRechargeActivity;
import com.bm.zlzq.my.refundapplication.MyRefundActivity;
import com.bm.zlzq.my.settings.MySettingsActivity;
import com.bm.zlzq.my.share.MyShareActivity;
import com.bm.zlzq.utils.ImageUtils;
import com.bm.zlzq.view.RoundImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangwm on 2015/12/3.
 */
public class MyFragment extends Fragment implements View.OnClickListener, APICallback.OnResposeListener {
    private View view;
    private RelativeLayout rl_my_order, rl_phone_and_time, rl_manager;
    private ImageView iv_edit;
    private RoundImageView iv_image;
    private UsersBean usersBean = new UsersBean();
    private static final int RESULT_PHOTO = 0;// 头像
    private TextView tv_recharge, tv_my_dfk, tv_my_dfh, tv_my_dsh, tv_my_ysh,
            tv_my_wddd, tv_my_wdsc, tv_my_wycz, tv_my_tzsq,
            tv_my_wyfx, tv_my_yhq, tv_my_shqd, tv_my_xtsz, tv_rest, tv_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fg_my, container, false);
        initView();
        initData();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        WebServiceAPI.getInstance().usersinfo(this, getActivity());
    }

    private void initData() {

    }

    private void initView() {

        iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
        iv_image = (RoundImageView) view.findViewById(R.id.iv_image);
        rl_my_order = (RelativeLayout) view.findViewById(R.id.rl_my_order);
        tv_recharge = (TextView) view.findViewById(R.id.tv_recharge);
        tv_rest = (TextView) view.findViewById(R.id.tv_rest);
        tv_name = (TextView) view.findViewById(R.id.tv_name);

        tv_my_dfk = (TextView) view.findViewById(R.id.tv_my_dfk);
        tv_my_dfh = (TextView) view.findViewById(R.id.tv_my_dfh);
        tv_my_dsh = (TextView) view.findViewById(R.id.tv_my_dsh);
        tv_my_ysh = (TextView) view.findViewById(R.id.tv_my_ysh);

        tv_my_wddd = (TextView) view.findViewById(R.id.tv_my_wddd);
        tv_my_wdsc = (TextView) view.findViewById(R.id.tv_my_wdsc);
        tv_my_wycz = (TextView) view.findViewById(R.id.tv_my_wycz);
        tv_my_tzsq = (TextView) view.findViewById(R.id.tv_my_tzsq);
        tv_my_wyfx = (TextView) view.findViewById(R.id.tv_my_wyfx);
        tv_my_yhq = (TextView) view.findViewById(R.id.tv_my_yhq);
        tv_my_shqd = (TextView) view.findViewById(R.id.tv_my_shqd);
        tv_my_xtsz = (TextView) view.findViewById(R.id.tv_my_xtsz);

        rl_phone_and_time = (RelativeLayout) view.findViewById(R.id.rl_phone_and_time);
        rl_manager = (RelativeLayout) view.findViewById(R.id.rl_manager);

        rl_manager.setOnClickListener(this);

        iv_edit.setOnClickListener(this);
        rl_my_order.setOnClickListener(this);
        tv_recharge.setOnClickListener(this);

        tv_my_dfk.setOnClickListener(this);
        tv_my_dfh.setOnClickListener(this);
        tv_my_dsh.setOnClickListener(this);
        tv_my_ysh.setOnClickListener(this);

        tv_my_wddd.setOnClickListener(this);
        tv_my_wdsc.setOnClickListener(this);
        tv_my_wycz.setOnClickListener(this);
        tv_my_tzsq.setOnClickListener(this);
        tv_my_wyfx.setOnClickListener(this);
        tv_my_yhq.setOnClickListener(this);
        tv_my_shqd.setOnClickListener(this);
        tv_my_xtsz.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_edit:
//                ((BaseActivity) getActivity()).gotoOtherActivity(PersonalInfoActivity.class);
                Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
//                intent.putExtra("id", usersBean.id);
                intent.putExtra("head", usersBean.head);
                intent.putExtra("nickname", usersBean.nickname);
                intent.putExtra("sex", usersBean.sex);
                startActivityForResult(intent, RESULT_PHOTO);
                break;
            case R.id.tv_recharge:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyRechargeActivity.class);
                break;
            case R.id.rl_my_order:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 0);
                break;
            case R.id.tv_my_dfk:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 1);
                break;
            case R.id.tv_my_dfh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 2);
                break;
            case R.id.tv_my_dsh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 3);
                break;
            case R.id.tv_my_ysh:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyOrderActivity.class, 4);
                break;
            case R.id.tv_my_wddd:
                ((BaseActivity) getActivity()).goto1OtherActivity(MyAddressActivity.class, 0);
                break;
            case R.id.tv_my_wdsc:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyCollectionActivity.class);
                break;
            case R.id.tv_my_wycz:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyRechargeActivity.class);
                break;
            case R.id.tv_my_tzsq:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyRefundActivity.class);
                break;
            case R.id.tv_my_wyfx:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyShareActivity.class);
                break;
            case R.id.tv_my_yhq:
                ((BaseActivity) getActivity()).gotoOtherActivity(MyCouponActivity.class);
                break;
            case R.id.tv_my_shqd:
                ((BaseActivity) getActivity()).gotoOtherActivity(MerchantListActivity.class);
                break;
            case R.id.tv_my_xtsz:
                ((BaseActivity) getActivity()).gotoOtherActivity(MySettingsActivity.class);
                break;
            case R.id.rl_manager:
                Intent intent1 = new Intent(getActivity(), MyManagerActivity.class);
                intent1.putExtra(Constant.TITLE, "一号店");
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_PHOTO:
                    Bitmap bitmap = data.getParcelableExtra("bitmap");
                    iv_image.setImageBitmap(ImageUtils.toRoundBitmap(bitmap));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {

        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    usersBean = apiResponse.data.users;
                    Log.e("usersBean", usersBean + "0.0");
                    tv_rest.setText("余额:" + usersBean.money + "元");
                    tv_name.setText(usersBean.nickname);
                    ImageLoader.getInstance().displayImage(Urls.PHOTO + usersBean.head, iv_image, ((BaseActivity) getActivity()).getImageOptions());
                    // 获取用户图片
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
