package com.tj.pxdl.carlease.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
