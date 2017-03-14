package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.tj.pxdl.carlease.MenuAct.AuthActivity;
import com.tj.pxdl.carlease.MenuAct.CouponActivity;
import com.tj.pxdl.carlease.MenuAct.DesignActivity;
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
        MenuAdapter adapter=new MenuAdapter(this,valueArr);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:

                        break;
                    case 1:
                        Intent couponIntent=new Intent(MenuActivity.this, CouponActivity.class);
                        startActivity(couponIntent);
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
