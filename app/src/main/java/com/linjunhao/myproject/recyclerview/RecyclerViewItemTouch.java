package com.linjunhao.myproject.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


/**
 * RecyclerView item 的拖拽和侧滑效果类
 *
 * @author linjunhao
 * @date 2019/9/17
 */
public class RecyclerViewItemTouch extends ItemTouchHelper.Callback {

    private ItemTouchListener mItemTouchListener;

    public RecyclerViewItemTouch(ItemTouchListener itemTouchListener) {
        this.mItemTouchListener = itemTouchListener;
    }

    /**
     * 获取动作标识
     * 动作标识分：dragFlags 和 swipeFlags
     * dragFlags：列表滚动方向的动作标识（如竖直列表就是上和下，水平列表就是左和右）
     * wipeFlags：与列表滚动方向垂直的动作标识（如竖直列表就是左和右，水平列表就是上和下）
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * 是否开启 item 长按拖拽功能
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 针对 drag 状态，当 item 拖拽移动时触发
     *
     * @param recyclerView
     * @param viewHolder       当前被拖拽的 item 的 viewHolder
     * @param targetViewHolder 当前被拖拽的 item 下方的另一个 item 的 viewHolder
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,@NonNull RecyclerView.ViewHolder viewHolder,@NonNull RecyclerView.ViewHolder targetViewHolder) {
        mItemTouchListener.onItemMove(viewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
        return true;
    }

    /**
     * 针对 swipe 状态，当 item 侧滑消失时触发（竖直列表是侧滑，水平列表是竖滑）
     *
     * @param viewHolder
     * @param direction  滑动的方向
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mItemTouchListener.onItemRemove(viewHolder.getAdapterPosition());
    }

    /**
     * 当 item 被开始拖拽或侧滑时触发
     *
     * @param viewHolder
     * @param actionState 当前 item 的状态
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不管是拖拽或是侧滑，背景色都要变化
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ViewHolder holder = (ViewHolder) viewHolder;
            holder.itemView.setBackgroundColor(0x501296db);
        }
    }

    /**
     * 针对 drag 和 swipe 状态，当 item 的交互状态结束时触发
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        viewHolder.itemView.setAlpha(1);
        viewHolder.itemView.setScaleY(1);
    }

    /**
     * 针对 drag 和 swipe 状态，侧滑或者拖拽的过程会一直调用这个函数
     * 这里实现侧滑时，item 变小，透明度加大
     *
     * @param c
     * @param recyclerView
     * @param viewHolder        当前 item 对应的 viewholder
     * @param dX                item 在 x 轴方向上的偏移量
     * @param dY                item 在 y 轴方向上的偏移量
     * @param actionState       当前 item 的状态
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(value);
            viewHolder.itemView.setScaleY(value);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    /**
     * 针对 swipe 和 drag 状态，当手指离开之后，view 回到指定位置动画的持续时间
     * @param recyclerView
     * @param animationType
     * @param animateDx
     * @param animateDy
     * @return              动画时间
     */
    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 1000;
    }

    public interface ItemTouchListener {
        void onItemMove(int fromPosition, int toPosition);

        void onItemRemove(int position);
    }
}
