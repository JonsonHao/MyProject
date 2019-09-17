package com.linjunhao.myproject.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linjunhao.myproject.R;
import com.linjunhao.myproject.recyclerview.BaseRecyclerAdapter;
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
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, Color.parseColor("#FFFFFF"));
        ButterKnife.bind(this);
    }
}
