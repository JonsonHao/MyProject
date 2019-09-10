package com.linjunhao.myproject.recyclerview;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * 类或接口的描述信息
 *
 * @author linjunhao
 * @date 2019/9/10
 */
public class SimpleItemTouchCallBack<T> extends ItemTouchHelper.Callback {

    private BaseRecyclerAdapter mAdapter;
    private List<T> mData;

    public SimpleItemTouchCallBack(BaseRecyclerAdapter adapter, List<T> data) {
        this.mAdapter = adapter;
        this.mData = data;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeFlag(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = viewHolder.getAdapterPosition();
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mData.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.itemView.setBackgroundColor(0xffbcbcbc);
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.itemView.setBackgroundColor(0xffeeeeee);
    }
}
