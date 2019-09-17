package com.linjunhao.myproject.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.List;

/**
 * RecyclerView 万能适配器
 *
 * @author linjunhao
 * @date 2019/9/7
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements RecyclerViewItemTouch.ItemTouchListener {

    private List<T> mData;

    public BaseRecyclerAdapter(List<T> mData) {
        this.mData = mData;
    }

    public abstract int getLayoutId(int viewType);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        convert(holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public abstract void convert(ViewHolder holder, T data, int position);

}
