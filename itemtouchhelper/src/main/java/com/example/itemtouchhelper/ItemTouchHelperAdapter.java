package com.example.itemtouchhelper;

/**
 * Created by Administrator on 2017/1/15.
 */

public interface ItemTouchHelperAdapter {
    void onItemRemove(int fromPos, int toPos);

    void onItemDismiss(int pos);
}
