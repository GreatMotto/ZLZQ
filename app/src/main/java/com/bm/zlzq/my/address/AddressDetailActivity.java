package com.bm.zlzq.my.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.AddressBean;

/**
 * Created by wangwm on 2015/12/22.
 */
public class AddressDetailActivity extends BaseActivity {
    private TextView tv_edit, tv_set_default_address;
    private TextView tv_name, tv_mobile, tv_area, tv_street, tv_detail_address;
    private AddressBean addressBean = new AddressBean();
    private AddressBean resultBean = new AddressBean();
    private static final int RESULT_MODIFY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_address_detail);
        addressBean = (AddressBean) getIntent().getSerializableExtra("addr");
        initView();
        initTitle("收货地址");
    }

    private void initView() {
        tv_set_default_address = (TextView) findViewById(R.id.tv_set_default_address);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_street = (TextView) findViewById(R.id.tv_street);
        tv_detail_address = (TextView) findViewById(R.id.tv_detail_address);
        tv_edit.setText("修改");
        tv_edit.setVisibility(View.VISIBLE);
        refreshUI();
        tv_edit.setOnClickListener(this);
        tv_set_default_address.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_MODIFY:
                    resultBean = (AddressBean) data.getSerializableExtra("address");
                    addressBean = resultBean;
                    refreshUI();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                Intent intent = new Intent();
                intent.putExtra("address", addressBean);
                setResult(RESULT_OK, intent);
                onBackPressed();
                break;
            case R.id.tv_edit:
                Intent intent1 = new Intent(this, ModifyAddressActivity.class);
                intent1.putExtra("addr", addressBean);
                startActivityForResult(intent1, RESULT_MODIFY);
                break;
            case R.id.tv_set_default_address:
                Intent intent2 = new Intent();
                intent2.putExtra("address", addressBean);
                intent2.putExtra("isDefault", true);
                setResult(RESULT_OK, intent2);
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent();
            intent.putExtra("address", addressBean);
            setResult(RESULT_OK, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    public void refreshUI() {
        tv_name.setText(addressBean.name);
        tv_mobile.setText(addressBean.mobile);
        tv_area.setText(addressBean.area);
        tv_street.setText(addressBean.street);
        tv_detail_address.setText(addressBean.detailaddress);
    }
}
