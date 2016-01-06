package com.bm.zlzq.my.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.AddressBean;
import com.bm.zlzq.constant.Constant;
import com.bm.zlzq.utils.NewToast;
import com.bm.zlzq.utils.ProgressUtils;
import com.bm.zlzq.view.WheelDialog;

/**
 * Created by wangwm on 2015/12/22.
 */
public class ModifyAddressActivity extends BaseActivity implements APICallback.OnResposeListener {
    private TextView tv_edit, tv_area;
    private RelativeLayout rl_area;
    private EditText et_name, et_mobile, et_street, et_detail_address;
    private AddressBean addressBean = new AddressBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_modify_address);
        addressBean = (AddressBean) getIntent().getSerializableExtra("addr");
        initView();
        initTitle("收货地址");
    }

    private void initView() {
        rl_area = (RelativeLayout) findViewById(R.id.rl_area);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        tv_area = (TextView) findViewById(R.id.tv_area);
        et_street = (EditText) findViewById(R.id.et_street);
        et_detail_address = (EditText) findViewById(R.id.et_detail_address);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setText("保存");
        tv_edit.setVisibility(View.VISIBLE);
        et_name.setText(addressBean.consignee);
        et_mobile.setText(addressBean.mobile);
        tv_area.setText(addressBean.area);
        et_street.setText(addressBean.street);
        et_detail_address.setText(addressBean.address);
        tv_edit.setOnClickListener(this);
        rl_area.setOnClickListener(this);
    }

    private boolean canSave() {
        if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
            NewToast.show(this, "请输入收件人姓名", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_mobile.getText().toString().trim())) {
            NewToast.show(this, "请输入手机号", NewToast.LENGTH_LONG);
            return false;
        }
        if (!et_mobile.getText().toString().trim().matches(Constant.CheckMobile)) {
            NewToast.show(this, "手机号不正确", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(tv_area.getText().toString().trim())) {
            NewToast.show(this, "请选择所在地区", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_street.getText().toString().trim())) {
            NewToast.show(this, "请输入街道信息", NewToast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(et_detail_address.getText().toString().trim())) {
            NewToast.show(this, "请输入详细地址", NewToast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            //保存按钮
            case R.id.tv_edit:
                if (canSave()) {
                    AddressBean db = new AddressBean();
                    db.consignee = et_name.getText().toString().trim();
                    db.mobile = et_mobile.getText().toString().trim();
                    db.area = tv_area.getText().toString().trim();
                    db.street = et_street.getText().toString().trim();
                    db.address = et_detail_address.getText().toString().trim();
                    db.status = "0";
                    Intent intent = new Intent();
                    intent.putExtra("address", db);
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                    WebServiceAPI.getInstance().updateaddress(addressBean.id, et_name.getText().toString().trim(), et_mobile.getText().toString().trim(), "", "", "", et_street.getText().toString().trim(), et_detail_address.getText().toString().trim(), "0", ModifyAddressActivity.this, ModifyAddressActivity.this);
                }
                break;
            case R.id.rl_area:
                WheelDialog.getInstance().chooseCity(this, tv_area, new WheelDialog.GetCityIdListener() {
                    @Override
                    public void GetCityId(String provinceId, String cityId, String areaId) {

                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 2:
                    NewToast.show(this, "地址修改成功!", NewToast.LENGTH_LONG);
                default:
                    break;
            }
        }

    }

    @Override
    public void OnErrorData(String code, Integer tag) {

    }
}
