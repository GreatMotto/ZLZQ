package com.bm.zlzq.my.address;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.bm.zlzq.view.SwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/19.
 */
public class MyAddressActivity extends BaseActivity implements APICallback.OnResposeListener {
    private TextView tv_edit;
    private SwipeListView swp_listview;
    private AdressSwipeAdapter adapter;
    private List<AddressBean> list = new ArrayList<>();
    private AddressBean addressBean = new AddressBean();
    private int editPosition = 0;
    private boolean isDef;
    private static final int RESULT_ADD = 0;
    private static final int RESULT_MODIFY = 1;
    private static final int RESULT_DETAIL = 2;
    private int flag = 0;// 0-非确认订单跳转   1-确认订单跳转

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_address);
        flag = getIntent().getIntExtra(Constant.FLAG, 0);
        initView();
        initData();
        initTitle("管理收货地址");
    }

    private void initData() {
        WebServiceAPI.getInstance().address(MyAddressActivity.this, MyAddressActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_ADD:
                    addressBean = (AddressBean) data.getSerializableExtra("address");
                    list.add(addressBean);
                    adapter.notifyDataSetChanged();
                    break;
                case RESULT_MODIFY:
                    addressBean = (AddressBean) data.getSerializableExtra("address");
                    list.get(editPosition).consignee = addressBean.consignee;
                    list.get(editPosition).mobile = addressBean.mobile;
                    list.get(editPosition).area = addressBean.area;
                    list.get(editPosition).street = addressBean.street;
                    list.get(editPosition).address = addressBean.address;
                    adapter.notifyDataSetChanged();
                    break;
                case RESULT_DETAIL:
                    addressBean = (AddressBean) data.getSerializableExtra("address");
                    isDef = data.getBooleanExtra("isDefault", false);
                    list.get(editPosition).consignee = addressBean.consignee;
                    list.get(editPosition).mobile = addressBean.mobile;
                    list.get(editPosition).area = addressBean.area;
                    list.get(editPosition).street = addressBean.street;
                    list.get(editPosition).address = addressBean.address;
                    if (isDef) {
                        for (int i = 0; i < list.size(); i++) {
                            if (i == editPosition) {
                                list.get(i).status = "1";
                            } else {
                                list.get(i).status = "0";
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    }

    private void initView() {
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setText("新增");
        tv_edit.setVisibility(View.VISIBLE);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                startActivityForResult(intent, RESULT_ADD);
            }
        });


        swp_listview = (SwipeListView) findViewById(R.id.swp_listview);
        adapter = new AdressSwipeAdapter(this, swp_listview.getRightViewWidth(), list, new AdressSwipeAdapter.IOnItemClickListener() {

            @Override
            public void onRightClick(View v, int position) {
                WebServiceAPI.getInstance().deladdress(list.get(position).id, MyAddressActivity.this, MyAddressActivity.this);
                list.remove(position);
                swp_listview.ishiddenRight(true);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDefaultAdrs(View v, int position) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).status = "1";
                    } else {
                        list.get(i).status = "0";
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDelClick(View v, int position) {
                WebServiceAPI.getInstance().deladdress(list.get(position).id, MyAddressActivity.this, MyAddressActivity.this);
                list.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onEditClick(View v, int position) {
//                Intent intent = new Intent(MyAddressActivity.this, ModifyAddressActivity.class);
//                intent.putExtra("addr", list.get(position));
//                startActivity(intent);
                editPosition = position;
                Intent intent = new Intent(MyAddressActivity.this, ModifyAddressActivity.class);
                intent.putExtra("addr", list.get(position));
                startActivityForResult(intent, RESULT_MODIFY);
            }


        });

        swp_listview.setAdapter(adapter);
        swp_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flag == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("address", list.get(position));
                    setResult(RESULT_OK, intent);
                    onBackPressed();
                } else {
                    editPosition = position;
                    Intent intent = new Intent(MyAddressActivity.this, AddressDetailActivity.class);
                    intent.putExtra("addr", list.get(position));
                    startActivityForResult(intent, RESULT_DETAIL);
                }
            }
        });
    }

    @Override
    public void OnFailureData(String error, Integer tag) {

    }

    @Override
    public void OnSuccessData(APIResponse apiResponse, Integer tag) {
        if (apiResponse.status.equals("0") && apiResponse.data != null) {
            switch (tag) {
                case 0:
                    ProgressUtils.cancleProgressDialog();
                    list.addAll(apiResponse.data.list);
//                    Log.e("list  OnSuccessData", list + "0.0");
                    adapter.notifyDataSetChanged();
//                    NewToast.show(this, "我的地址", NewToast.LENGTH_LONG);
                    break;
                case 1:
                    NewToast.show(this, "地址删除成功!", NewToast.LENGTH_LONG);
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
