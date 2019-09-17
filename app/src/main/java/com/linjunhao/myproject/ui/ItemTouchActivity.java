package com.linjunhao.myproject.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.linjunhao.myproject.util.Constant;
import com.linjunhao.myproject.R;
import com.linjunhao.myproject.recyclerview.BaseRecyclerAdapter;
import com.linjunhao.myproject.recyclerview.RecyclerViewItemTouch;
import com.linjunhao.myproject.recyclerview.ViewHolder;
import com.linjunhao.myproject.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * @author linjunhao
 * @date 2019/9/17
 */
public class ItemTouchActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<String> mData = new ArrayList<>();

    private BaseRecyclerAdapter<String> mAdapter;

    private RecyclerViewItemTouch mItemTouch;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);
        StatusBarUtil.setColor(this, Color.parseColor("#FFFFFF"));
        ButterKnife.bind(this);
        initData();
        setRecyclerView();
    }

    private void initData() {
        for (int i = 0; i < Constant.ITEM_TEXT.length; i++) {
            mData.add(Constant.ITEM_TEXT[i]);
        }
    }

    private void setRecyclerView() {
        mAdapter = new BaseRecyclerAdapter<String>(mData) {
            @Override
            public void onItemMove(int from, int to) {
                Collections.swap(mData, from, to);
                notifyItemMoved(from, to);
            }

            @Override
            public void onItemRemove(int position) {
                mData.remove(position);
                notifyItemRemoved(position);
            }

            @Override
            public int getLayoutId(int viewType) {
                return R.layout.recycler_item;
            }

            @Override
            public void convert(ViewHolder holder, String data, int position) {
                holder.setText(R.id.tv_item, data);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mItemTouch = new RecyclerViewItemTouch(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemTouch);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
