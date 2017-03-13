package com.tj.pxdl.carlease.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.impl.OnRecyclerViewListener;

/**
 * Created by Chaersi on 17/3/10.
 */
public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ItemViewHolder>{

    private Context context;
    public CouponAdapter(Context context){
        this.context=context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coupon, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;
        holder.couponName.setText("分时租车优惠券¥5.00");
        holder.timeView.setText("2017-03-02截止");
        holder.contentView.setText("满额10送5");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView couponName,timeView,contentView;
        public RelativeLayout parentLayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            parentLayout= (RelativeLayout) itemView.findViewById(R.id.parentLayout);
            couponName= (TextView) itemView.findViewById(R.id.couponName);
            timeView= (TextView) itemView.findViewById(R.id.timeView);
            contentView= (TextView) itemView.findViewById(R.id.contentView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }

}
