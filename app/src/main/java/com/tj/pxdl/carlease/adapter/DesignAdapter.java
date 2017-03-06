package com.tj.pxdl.carlease.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.pxdl.carlease.R;
import com.tj.pxdl.carlease.utils.AppUtils;

/**
 * Created by Chaersi on 17/2/27.
 * 设置项适配器
 */
public class DesignAdapter extends BaseAdapter {

    private Context context;
    private String[] titleArr;
    public DesignAdapter(Context context) {
        this.context = context;
        titleArr = new String[]{"版本号", "用户协议", "关于我们"};
    }

    @Override
    public int getCount() {
        return titleArr.length;
    }

    @Override
    public Object getItem(int position) {
        return titleArr[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_design, null,false);
            holder=new ViewHolder();
            holder.itemName=(TextView) convertView.findViewById(R.id.itemName);
            holder.itemValue=(TextView) convertView.findViewById(R.id.itemValue);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.itemName.setText(titleArr[position]);

        if(position==0){
            holder.itemValue.setText(AppUtils.getAppVersionName(context));
        }else{
            holder.itemValue.setText("");
        }
        return convertView;
    }

    private class ViewHolder{
        TextView itemName;
        TextView itemValue;
    }
}
