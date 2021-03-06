package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.tj.pxdl.carlease.MenuAct.AccountActivity;
import com.tj.pxdl.carlease.MenuAct.AuthActivity;
import com.tj.pxdl.carlease.MenuAct.CouponActivity;
import com.tj.pxdl.carlease.MenuAct.DesignActivity;
import com.tj.pxdl.carlease.MenuAct.MessageActivity;
import com.tj.pxdl.carlease.MenuAct.OrderActivity;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.adapter.MenuAdapter;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.widget.ListViewShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 菜单
 * @author Chaersi
 */
public class MenuActivity extends BaseActivity {

    @BindView(R.id.rightBtn_img) ImageView rightBtnImg;
    @BindView(R.id.topline) View topline;
    @BindView(R.id.rightBtn) View rightBtn;
    @BindView(R.id.menuListView) ListViewShowView menuListView;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        rightBtnImg.setVisibility(View.VISIBLE);
        rightBtn.setVisibility(View.VISIBLE);
        topline.setVisibility(View.GONE);
        rightBtnImg.setImageResource(R.mipmap.icon_set);

        String[] valueArr=new String[]{
          "¥0.00","0","0","0","","未认证","","400-090-1619"
        };

        String[] titleArr=new String[]{
                "账户","优惠券","积分","消息","订单","征信认证","发票管理","客服热线"
        };
        int[] tagImgArr = new int[]{
                R.mipmap.icon_gray_account, R.mipmap.icon_gray_coupon, R.mipmap.ic_jifen,
                R.mipmap.icon_gray_website_letter, R.mipmap.icon_gray_order,
                R.mipmap.icon_gray_id_verify, R.mipmap.icon_gray_receipt,
                R.mipmap.icon_user_phone
        };


        MenuAdapter adapter=new MenuAdapter(this,valueArr,titleArr,tagImgArr);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent accountIntent=new Intent(MenuActivity.this, AccountActivity.class);
                        startActivity(accountIntent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 1:
                        Intent couponIntent=new Intent(MenuActivity.this, CouponActivity.class);
                        startActivity(couponIntent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 3:
                        Intent msgIntent=new Intent(MenuActivity.this, MessageActivity.class);
                        startActivity(msgIntent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 4:
                        Intent orderIntent=new Intent(MenuActivity.this, OrderActivity.class);
                        startActivity(orderIntent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                    case 5:
                        Intent authIntent=new Intent(MenuActivity.this, AuthActivity.class);
                        startActivity(authIntent);
                        overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.leftBtn, R.id.rightBtn})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
            case R.id.rightBtn:
                Intent intent=new Intent(this,DesignActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
                break;
        }
    }
}
