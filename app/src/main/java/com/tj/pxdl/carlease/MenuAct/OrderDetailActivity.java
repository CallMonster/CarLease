package com.tj.pxdl.carlease.MenuAct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {


    @BindView(R.id.carPic) ImageView carPic;
    @BindView(R.id.version) TextView version;
    @BindView(R.id.addr) TextView addr;
    @BindView(R.id.elecVal) TextView elecVal;
    @BindView(R.id.dictincVal) TextView dictincVal;
    @BindView(R.id.colorVal) TextView colorVal;
    @BindView(R.id.cardetail) TextView cardetail;
    @BindView(R.id.priceView) TextView priceView;
    @BindView(R.id.loginBtn) Button loginBtn;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.leftBtn, R.id.priceView, R.id.state1, R.id.state2, R.id.state3, R.id.state4, R.id.loginBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.priceView:
                break;
            case R.id.state1:
                break;
            case R.id.state2:
                break;
            case R.id.state3:
                break;
            case R.id.state4:
                break;
            case R.id.loginBtn:
                break;
        }
    }

}
