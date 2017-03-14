package com.tj.pxdl.carlease.MenuAct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;

import java.util.ArrayList;

/**
 * Created by Chaersi on 17/3/14.
 */
public class AccountMsgAdapter extends RecyclerView.Adapter<AccountMsgAdapter.ItemViewHolder>{

    private Context context;
    private ArrayList<String> strArr;
    public AccountMsgAdapter(Context context,ArrayList<String> strArr){
        this.context=context;
        this.strArr=strArr;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_accountmsg, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;
        holder.timeView.setText("2016-03-14 15:00");
        holder.stateView.setText("消费");
        holder.priceView.setText("¥3.09");
    }

    @Override
    public int getItemCount() {
        return strArr.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView timeView,stateView,priceView;
        public LinearLayout parentLayout;
        public int position;

        public ItemViewHolder(View itemView) {
            super(itemView);
            timeView= (TextView) itemView.findViewById(R.id.timeView);
            stateView= (TextView) itemView.findViewById(R.id.stateView);
            priceView= (TextView) itemView.findViewById(R.id.priceView);
            parentLayout= (LinearLayout) itemView.findViewById(R.id.parentLayout);

        }

        @Override
        public void onClick(View v) {

        }
    }

}
