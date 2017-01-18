package com.example.itemtouchhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/1/15.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private RecyclerListAdapter mAdapter;

    public SimpleItemTouchHelperCallback(RecyclerListAdapter adapter) {
        this.mAdapter = adapter;
    }

    //允许长按拖动
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    //允许滑动
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        //LinearLayoutManager
//        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//上下是拖动操作
//        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;//左右是滑动操作

        //GridLayoutManager
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
        int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemRemove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof RecyclerListAdapter.MyViewHolder) {
                ((RecyclerListAdapter.MyViewHolder) viewHolder).onItemSelected();
            }
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (viewHolder instanceof RecyclerListAdapter.MyViewHolder) {
            ((RecyclerListAdapter.MyViewHolder) viewHolder).onItemClear();
        }
    }
}
