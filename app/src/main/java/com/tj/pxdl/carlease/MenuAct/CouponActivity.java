package com.tj.pxdl.carlease.MenuAct;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.MenuAct.adapter.CouponAdapter;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.widget.OneKeyClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends BaseActivity {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.couponEdit) OneKeyClearEditText couponEdit;
    @BindView(R.id.couponView) RecyclerView couponView;

    CouponAdapter adapter;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);

        couponView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        couponView.setLayoutManager(layoutManager);
        adapter=new CouponAdapter(this);
        couponView.setAdapter(adapter);

    }

    @OnClick({R.id.leftBtn, R.id.couponBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.couponBtn:
                break;
        }
    }
}
