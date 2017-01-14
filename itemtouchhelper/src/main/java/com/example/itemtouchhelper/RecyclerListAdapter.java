package com.example.itemtouchhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/14.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mDatas;
    private LayoutInflater mInflater;

    public RecyclerListAdapter(ArrayList<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=mInflater.inflate(R.layout.item,parent,true);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!(holder instanceof MyViewHolder))
            return;

        String item=mDatas.get(position);
        ((MyViewHolder) holder).mTvItem.setText(item);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView mTvItem;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTvItem= (TextView) itemView.findViewById(R.id.item);
        }
    }
}
