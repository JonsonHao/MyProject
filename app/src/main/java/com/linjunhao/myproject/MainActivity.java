package com.linjunhao.myproject;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.linjunhao.myproject.recyclerview.BaseRecyclerAdapter;
import com.linjunhao.myproject.recyclerview.SimpleItemTouchCallBack;
import com.linjunhao.myproject.recyclerview.ViewHolder;
import com.linjunhao.myproject.recyclerview.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    EmptyRecyclerView recyclerView;

    private List<String> mDatas = new ArrayList<>();

    private BaseRecyclerAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mAdapter = new BaseRecyclerAdapter<String>(mDatas) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recycler_item;
            }

            @Override
            public void convert(ViewHolder holder, String data, int position) {
                holder.setText(R.id.tv_item, data);
            }
        };
        View view = LayoutInflater.from(this).inflate(R.layout.recycler_empty, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setEmptyView(view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallBack<String>(mAdapter, mDatas));
        helper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDatas.size() == 0) {
                    initData();
                } else {
                    mDatas.clear();
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mDatas.add("第 " + i + " 个Item");
        }
    }
}
