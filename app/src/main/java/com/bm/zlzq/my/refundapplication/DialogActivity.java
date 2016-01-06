package com.bm.zlzq.my.refundapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.bm.zlzq.R;


public class DialogActivity extends Activity implements OnClickListener {
    private LinearLayout layout01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initView();
    }


    private void initView() {
        layout01 = (LinearLayout) findViewById(R.id.llayout01);


        layout01.setOnClickListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
