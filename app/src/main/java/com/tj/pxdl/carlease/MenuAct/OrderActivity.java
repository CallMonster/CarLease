package com.tj.pxdl.carlease.MenuAct;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tj.pxdl.carlease.MenuAct.frag.Order_CurrentFrag;
import com.tj.pxdl.carlease.MenuAct.frag.Order_HistoryFrag;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单
 *
 * @author Chaersi
 */
public class OrderActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.rgpBot) RadioGroup rgpBot;
    @BindView(R.id.contain) FrameLayout contain;
    @BindView(R.id.rbtn01) RadioButton rbtn01;
    @BindView(R.id.rbtn02) RadioButton rbtn02;

    private Order_CurrentFrag currentFrag;
    private Order_HistoryFrag historyFrag;

    private Fragment mContext;
    private FragmentTransaction transaction;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        currentFrag=new Order_CurrentFrag();
        historyFrag=new Order_HistoryFrag();

        mContext=currentFrag;
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contain, currentFrag);
        transaction.commit();

        rgpBot.setOnCheckedChangeListener(this);
    }

    /**
     * 切换Fragment
     * @param to
     */
    private void switchContent(Fragment to){
        if(mContext!=to){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            if(to.isAdded()){
                transaction.hide(mContext).show(to).commit();
            }else{
                transaction.hide(mContext).add(R.id.contain,to).commit();
            }
            mContext=to;
        }
    }

    @OnClick({R.id.leftBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId){
            case R.id.rbtn01:
                if(null==currentFrag){
                    currentFrag=new Order_CurrentFrag();
                }
                switchContent(currentFrag);

                rbtn01.setTextColor(getResources().getColor(R.color.white));
                rbtn01.setBackgroundResource(R.drawable.border_btn_theme_left);
                rbtn02.setTextColor(getResources().getColor(R.color.bg_theme_color));
                rbtn02.setBackgroundColor(getResources().getColor(R.color.nocolor));
                break;
            case R.id.rbtn02:
                if(null==historyFrag){
                    historyFrag=new Order_HistoryFrag();
                }
                switchContent(historyFrag);

                rbtn02.setTextColor(getResources().getColor(R.color.white));
                rbtn02.setBackgroundResource(R.drawable.border_btn_theme_right);
                rbtn01.setTextColor(getResources().getColor(R.color.bg_theme_color));
                rbtn01.setBackgroundColor(getResources().getColor(R.color.nocolor));
                break;
        }
    }
}
