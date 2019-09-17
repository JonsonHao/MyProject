package com.linjunhao.myproject.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


/**
 * 含有头部或尾部的 RecyclerView.Adapter，采用装饰者模式
 * 可只添加头部
 * 可只添加尾部
 * 可同时添加头部和尾部
 *
 * @author linjunhao
 * @date 2019/9/7
 */
public class HeadEndRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_NORMAL = 1;
    private static final int ITEM_TYPE_HEADER = 2;
    private static final int ITEM_TYPE_FOOTER = 3;

    private BaseRecyclerAdapter mAdapter;
    private View mHeaderView;
    private View mFooterView;

    public HeadEndRecyclerAdapter(BaseRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 获取 Item 的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0 && !checkHeaderNull()) {
            return ITEM_TYPE_HEADER;
        } else if (position == mAdapter.getItemCount() + 1) {
            return ITEM_TYPE_FOOTER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            return new RecyclerView.ViewHolder(mHeaderView){};
        } else if (viewType == ITEM_TYPE_FOOTER) {
            return new RecyclerView.ViewHolder(mFooterView){};
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0 && !checkHeaderNull()) {
            return ;
        } else if (position == mAdapter.getItemCount() + 1) {
            return ;
        } else {
            if (!checkHeaderNull()) {
                mAdapter.onBindViewHolder((ViewHolder) holder, position - 1);
            } else {
                mAdapter.onBindViewHolder((ViewHolder) holder, position);
            }
        }
    }


    @Override
    public int getItemCount() {
        int count = mAdapter.getItemCount();
        if (mHeaderView != null) {
            count += 1;
        }
        if (mFooterView != null) {
            count += 1;
        }
        return count;
    }

    /**
     * 添加头部 View
     *
     * @param view
     */
    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    /**
     * 添加尾部 View
     *
     * @param view
     */
    public void addFooterView(View view) {
        this.mFooterView = view;
    }

    /**
     * 判断是否有设置头部 View
     *
     * @return true 表示不为空，false 表示为空
     */
    private boolean checkHeaderNull() {
        return mHeaderView == null;
    }
}
