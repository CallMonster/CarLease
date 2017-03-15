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
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ItemViewHolder> {

    private Context context;
    public MessageAdapter(Context context){
        this.context=context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;
        holder.msgTitleView.setText("用户注册");
        holder.msgTimeView.setText("2017-01-22 12:16");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView msgTitleView,msgTimeView;
        public RelativeLayout parentLayout;
        public int position;

        public ItemViewHolder(View itemView) {
            super(itemView);
            msgTitleView= (TextView) itemView.findViewById(R.id.msgTitleView);
            msgTimeView= (TextView) itemView.findViewById(R.id.msgTimeView);
            parentLayout= (RelativeLayout) itemView.findViewById(R.id.parentLayout);
            parentLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.parentLayout:
                    listener.onItemClickListener(position);
                    break;
            }
        }
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }

}
