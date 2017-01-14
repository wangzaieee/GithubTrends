package com.example.itemtouchhelper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        initData();
        RecyclerListAdapter adapter=new RecyclerListAdapter(list,mContext);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    //初始化数据
    private void initData() {
        for (int i = 0; i <20 ; i++) {
            list.add(i,"item "+i);
        }
    }
}
