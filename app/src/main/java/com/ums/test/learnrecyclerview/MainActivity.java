package com.ums.test.learnrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
        //现行管理器，支持横向、纵向
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //网格布局管理器
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this,4);
        //瀑布就式布局管理器
        StaggeredGridLayoutManager mGridLayoutManager = new StaggeredGridLayoutManager (3, LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        initData();
        final MyAdapter mAdapter = new MyAdapter(mDatas);

        mRecyclerView.setAdapter(mAdapter);

        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

        mAdapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener(){
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " long click",Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });

    }




    protected void initData() {
        mDatas = new ArrayList<String>();
        for(int i = 'A';i<'z';i++) {
            mDatas.add("" + (char) i);
        }
    }
}
