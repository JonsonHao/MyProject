package com.linjunhao.myproject.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * RecyclerView.ViewHolder 封装类
 * 默认实现了 setText 和 setImage
 * 如果界面中有其他控件需要设置，可以新建一个类继承该 ViewHolder 仿照 setText 和 setImage 实现
 *
 * @author linjunhao
 * @date 2019/9/7
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static ViewHolder get(ViewGroup parent, int layoutId) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new ViewHolder(convertView);
    }

    protected <T extends View> T getView(int id) {
        View v = mViews.get(id);
        if (v == null) {
            v = mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return (T) v;
    }

    public void setText(int id, String value) {
        TextView view = getView(id);
        view.setText(value);
    }

    public void setImage(int id, int value) {
        ImageView view = getView(id);
        view.setImageResource(value);
    }
}
