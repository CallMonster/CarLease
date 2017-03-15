package com.tj.pxdl.carlease.MenuAct;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.pxdl.carlease.MenuAct.adapter.AccountMsgAdapter;
import com.tj.pxdl.carlease.MenuAct.adapter.MessageAdapter;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.widget.DividerDecoration;
import com.tj.pxdl.carlease.widget.LoadMoreOnScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.rightView) TextView rightView;
    @BindView(R.id.msgView) RecyclerView msgView;
    @BindView(R.id.no_msg) LinearLayout noMsg;
    @BindView(R.id.msgLayout) SwipeRefreshLayout msgLayout;

    private MessageAdapter adapter;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        rightView.setVisibility(View.VISIBLE);
        rightView.setText("全部删除");
        rightView.setTextColor(getResources().getColor(R.color.bg_theme_color));

        msgView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgView.setLayoutManager(layoutManager);
        msgView.addItemDecoration(new DividerDecoration(this));
        msgView.setItemAnimator(new DefaultItemAnimator());
        adapter=new MessageAdapter(this);
        msgView.setAdapter(adapter);

        msgLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        msgLayout.setColorSchemeResources(R.color.bg_theme_color);

        /**
         * 下拉刷新
         */
        msgLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                msgView.setVisibility(View.GONE);
                noMsg.setVisibility(View.VISIBLE);
                msgLayout.setRefreshing(false);
            }
        });

        /**
         * 上拉加载
         */
        msgView.addOnScrollListener(new LoadMoreOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {

            }
        });
    }

    @OnClick({R.id.leftBtn, R.id.rightBtn,R.id.no_msg})
    public void onClickListener(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                overridePendingTransition(R.anim.in_from_left,R.anim.out_from_right);
                break;
            case R.id.no_msg:
                msgView.setVisibility(View.VISIBLE);
                noMsg.setVisibility(View.GONE);
                break;
            case R.id.rightBtn:
                //全部删除
                break;
        }
    }
}
