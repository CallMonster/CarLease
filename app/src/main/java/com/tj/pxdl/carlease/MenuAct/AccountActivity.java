package com.tj.pxdl.carlease.MenuAct;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.adapter.MenuAdapter;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.widget.ListViewShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.priceView) TextView priceView;
    @BindView(R.id.payListView) ListViewShowView payListView;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);

        String[] valueArr=new String[]{"","",""};
        String[] titleArr=new String[]{"微信","支付宝","银联卡"};
        int[] tagImgArr = new int[]{R.mipmap.icon_pay_weixin,R.mipmap.icon_pay_alipay, R.mipmap.icon_pay_card};
        MenuAdapter adapter=new MenuAdapter(this,valueArr,titleArr,tagImgArr);
        payListView.setAdapter(adapter);

        priceView.setText("¥1938.00");
    }

    @OnClick({R.id.leftBtn, R.id.accBtn,R.id.priceView})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.accBtn:
                break;
            case R.id.priceView:
                Intent intent=new Intent(this,AccountMsgActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                break;
        }
    }
}
