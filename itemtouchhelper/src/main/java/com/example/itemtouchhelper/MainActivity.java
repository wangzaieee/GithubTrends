package com.example.itemtouchhelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<String> list = new ArrayList<>();
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        RecyclerListAdapter adapter = new RecyclerListAdapter(this, list, mContext);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext, 3);
        mRecyclerView.setLayoutManager(manager);
        //添加默认分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

        SimpleItemTouchHelperCallback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i < 20; i++) {
            list.add(i, "item " + i);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        mItemTouchHelper.startDrag(holder);
    }
}
