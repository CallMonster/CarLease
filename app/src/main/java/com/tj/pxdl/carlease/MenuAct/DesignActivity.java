package com.tj.pxdl.carlease.MenuAct;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.activity.LoginActivity;
import com.tj.pxdl.carlease.activity.UpdatePwdActivity;
import com.tj.pxdl.carlease.adapter.DesignAdapter;
import com.tj.pxdl.carlease.adapter.MenuAdapter;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.base.BaseApplication;
import com.tj.pxdl.carlease.widget.ListViewShowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 * @author Chaersi
 */
public class DesignActivity extends BaseActivity {

    @BindView(R.id.updatePwd) TextView updatePwd;
    @BindView(R.id.designListView) ListViewShowView designListView;

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);

        DesignAdapter adapter=new DesignAdapter(this);
        designListView.setAdapter(adapter);
    }

    @OnClick({R.id.leftBtn,R.id.updatePwd,R.id.exitBtn})
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.updatePwd:
                Intent intentPwd=new Intent(this, UpdatePwdActivity.class);
                startActivity(intentPwd);
                finish();
                break;
            case R.id.exitBtn:
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                BaseApplication.instance.clearAct();
                break;
        }

    }
}
