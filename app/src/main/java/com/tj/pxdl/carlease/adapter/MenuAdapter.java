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

/**
 * Created by Chaersi on 17/2/27.
 * 菜单适配器
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private String[] titleArr;
    private int[] tagImgArr;
    private String[] itemValueArr;
    public MenuAdapter(Context context,String[] itemValueArr,String[] titleArr,int[] tagImgArr){
        this.context=context;
        this.titleArr=titleArr;
        this.tagImgArr = tagImgArr;

        this.itemValueArr=itemValueArr;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_menu, null,false);
            holder=new ViewHolder();
            holder.itemIcon=(ImageView) convertView.findViewById(R.id.itemIcon);
            holder.itemName=(TextView) convertView.findViewById(R.id.itemName);
            holder.itemValue=(TextView) convertView.findViewById(R.id.itemValue);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.itemIcon.setImageResource(tagImgArr[position]);
        holder.itemName.setText(titleArr[position]);

        if (TextUtils.isEmpty(itemValueArr[position])) {
            holder.itemValue.setText("");
        } else {
            holder.itemValue.setText(itemValueArr[position]);
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView itemIcon;
        TextView itemName;
        TextView itemValue;
    }

}
