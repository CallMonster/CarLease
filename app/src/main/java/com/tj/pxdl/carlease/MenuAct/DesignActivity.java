package com.tj.pxdl.carlease.MenuAct;

import android.view.View;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.adapter.DesignAdapter;
import com.tj.pxdl.carlease.adapter.MenuAdapter;
import com.tj.pxdl.carlease.base.BaseActivity;
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

    @OnClick({R.id.leftBtn,R.id.updatePwd})
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
                break;
            case R.id.updatePwd:

                break;
        }

    }
}
