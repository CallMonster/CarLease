package com.tj.pxdl.carlease.MenuAct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.impl.OnRecyclerViewListener;

/**
 * Created by Chaersi on 17/3/15.
 */
public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.ItemViewHolder>{

    private Context context;
    public CurrentAdapter(Context context){
        this.context=context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_current, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;
        holder.orderInfo.setText("IEV5/京QV135");
        holder.orderTime.setText("03/13 15:13");
        holder.stateView.setText("完成");
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView orderInfo,orderTime,stateView;
        public RelativeLayout parentLayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            parentLayout= (RelativeLayout) itemView.findViewById(R.id.parentLayout);
            orderInfo = (TextView) itemView.findViewById(R.id.orderInfo);
            orderTime = (TextView) itemView.findViewById(R.id.orderTime);
            stateView = (TextView) itemView.findViewById(R.id.stateView);
            parentLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(position);
        }
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }

}
