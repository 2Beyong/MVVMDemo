package com.hongon.solarmvvmdemo.converter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hongon.solarmvvmdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CoCO on 2017/12/14.
 */

public class ConverterListItemAdapter extends RecyclerView.Adapter<ConverterListItemAdapter.myViewHolder> {

    private List<ConverterListItem> ItemArray;

    public ConverterListItemAdapter(List<ConverterListItem> itemArray)
    {
        if(itemArray!=null)
        ItemArray =itemArray;
    }


    /*ViewHolder是专门用来缓存一个itemView中的各个部分，
    * */
    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView ConvererterName ;
        TextView LastReportTime;
        public myViewHolder(View view)
        {
            super(view);
            ConvererterName = view.findViewById(R.id.id_convertername);
            LastReportTime=view.findViewById(R.id.id_lastReportTime);
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder = new myViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.convert_feed_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String name = "逆变器储能系统名称 : "+ItemArray.get(position).getName();
        String time = "上次运行时间 : " + ItemArray.get(position).getLastReportTime().toString();
        holder.ConvererterName.setText(name);
        holder.LastReportTime.setText(time);
    }

    @Override
    public int getItemCount() {
        if (ItemArray==null)
            return 0;
        return ItemArray.size();
    }

}
