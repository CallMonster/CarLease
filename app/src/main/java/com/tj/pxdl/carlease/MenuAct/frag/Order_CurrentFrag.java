package com.tj.pxdl.carlease.MenuAct.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.tj.pxdl.carlease.MenuAct.OrderDetailActivity;
import com.tj.pxdl.carlease.MenuAct.adapter.CurrentAdapter;
import com.tj.pxdl.carlease.MenuAct.adapter.MessageAdapter;
import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.impl.OnRecyclerViewListener;
import com.tj.pxdl.carlease.widget.DividerDecoration;
import com.tj.pxdl.carlease.widget.LoadMoreOnScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chaersi on 17/3/15.
 */
public class Order_CurrentFrag extends Fragment implements View.OnClickListener{

    @BindView(R.id.currentView) RecyclerView currentView;
    @BindView(R.id.no_msg) LinearLayout noMsg;
    @BindView(R.id.parent_refresh) FrameLayout parentRefresh;
    @BindView(R.id.msgLayout) SwipeRefreshLayout msgLayout;

    private CurrentAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_current, null);
        ButterKnife.bind(this, view);

        currentView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        currentView.setLayoutManager(layoutManager);
        currentView.addItemDecoration(new DividerDecoration(getActivity()));
        currentView.setItemAnimator(new DefaultItemAnimator());
        adapter=new CurrentAdapter(getActivity());
        currentView.setAdapter(adapter);

        msgLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        msgLayout.setColorSchemeResources(R.color.bg_theme_color);

        /**
         * 下拉刷新
         */
        msgLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentView.setVisibility(View.GONE);
                noMsg.setVisibility(View.VISIBLE);
                msgLayout.setRefreshing(false);
            }
        });

        currentView.addOnScrollListener(new LoadMoreOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                //上拉加载
            }
        });

        noMsg.setOnClickListener(this);
        adapter.addItemClickListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent=new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_from_left);
            }

            @Override
            public void onItemViewClickListener(int position) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.no_msg:
                currentView.setVisibility(View.VISIBLE);
                noMsg.setVisibility(View.GONE);
                break;
        }
    }
}
