package com.bm.zlzq.home.message;

import android.os.Bundle;
import android.widget.ListView;

import com.bm.zlzq.BaseActivity;
import com.bm.zlzq.Http.APICallback;
import com.bm.zlzq.Http.APIResponse;
import com.bm.zlzq.Http.WebServiceAPI;
import com.bm.zlzq.R;
import com.bm.zlzq.bean.MessageBean;
import com.bm.zlzq.utils.ProgressUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangwm on 2015/12/3.
 */
public class MessageActivity extends BaseActivity implements APICallback.OnResposeListener {
    private ListView lv_root;
    private List<MessageBean> msglist = new ArrayList<>();
    private MsgRootAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_message);
        initTitle("通知消息");
        initView();
        initData();
    }

    private void initData() {
        ProgressUtils.showProgressDialog("", this);
        WebServiceAPI.getInstance().message(MessageActivity.this, MessageActivity.this);
    }

    private void initView() {
        lv_root = (ListView) findViewById(R.id.lv_root);

        adapter = new MsgRootAdapter(this, msglist);
        lv_root.setAdapter(adapter);
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
                    msglist.clear();
                    msglist.addAll(apiResponse.data.list);
                    adapter.notifyDataSetChanged();
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
