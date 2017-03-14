package com.tj.pxdl.carlease.MenuAct;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tj.pxdl.carlease.MenuAct.adapter.AccountMsgAdapter;
import com.tj.pxdl.carlease.MenuAct.adapter.CouponAdapter;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.base.BaseActivity;
import com.tj.pxdl.carlease.widget.DividerDecoration;
import com.tj.pxdl.carlease.widget.LoadMoreOnScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMsgActivity extends BaseActivity {

    @BindView(R.id.msgRecyclerView) RecyclerView msgRecyclerView;
    @BindView(R.id.no_msg) LinearLayout no_msg;
    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;

    private AccountMsgAdapter adapter;
    private ArrayList<String> strArr;
    @Override
    public void onCreate() {
        setContentView(R.layout.activity_account_msg);
        ButterKnife.bind(this);

        strArr=new ArrayList<>();
        for(int i=0;i<10;i++){
            strArr.add("index position"+i);
        }


        msgRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        msgRecyclerView.addItemDecoration(new DividerDecoration(this));
        msgRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new AccountMsgAdapter(this,strArr);
        msgRecyclerView.setAdapter(adapter);

        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refreshLayout.setColorSchemeResources(R.color.bg_theme_color);

        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                msgRecyclerView.setVisibility(View.GONE);
                no_msg.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
            }
        });

        msgRecyclerView.addOnScrollListener(new LoadMoreOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                showProgressDialog("加载中...");
                Log.i(TAG,"position:"+currentPage);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        strArr.add("new object");
                        strArr.add("new object");
                        strArr.add("new object");
                        adapter.notifyItemRangeInserted(strArr.size(),3);
                        hideProgressDialog();
                    }
                }).start();

            }
        });


    }

    @OnClick({R.id.leftBtn,R.id.no_msg})
    public void onClickListener(View v) {
        switch (v.getId()){
            case R.id.leftBtn:
                finish();
                break;
            case R.id.no_msg:
                msgRecyclerView.setVisibility(View.VISIBLE);
                no_msg.setVisibility(View.GONE);
                break;
        }
    }
}
