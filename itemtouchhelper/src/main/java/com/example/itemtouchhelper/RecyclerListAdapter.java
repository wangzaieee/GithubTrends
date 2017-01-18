package com.example.itemtouchhelper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2017/1/14.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<String> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;
    private OnStartDragListener mStartDragListener;

    public RecyclerListAdapter(OnStartDragListener listener, ArrayList<String> mDatas, Context mContext) {
        this.mStartDragListener = listener;
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //最后一个为true为什么会影响ItemTouchHelper的作用范围 (因为添加在item上的根布局是wrap_content)
        View v = mInflater.inflate(R.layout.item, new FrameLayout(mContext), false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (!(holder instanceof MyViewHolder))
            return;

        String item = mDatas.get(position);
        ((MyViewHolder) holder).mTvItem.setText(item);

        ((MyViewHolder) holder).mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onItemRemove(int fromPos, int toPos) {
        Collections.swap(mDatas, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
    }

    @Override
    public void onItemDismiss(int pos) {
        mDatas.remove(pos);
        notifyItemRemoved(pos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        public TextView mTvItem;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.item);
            mImageView = (ImageView) itemView.findViewById(R.id.drag_bar);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
