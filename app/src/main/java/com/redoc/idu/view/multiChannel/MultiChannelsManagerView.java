package com.redoc.idu.view.multiChannel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.redoc.idu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Multi channels manager view.
 * Created by limen on 2016/9/16.
 */
public class MultiChannelsManagerView extends LinearLayout {

    /**
     * The recycle view contained in the view.
     */
    @BindView(R.id.channels_grid)
    RecyclerView mChannelsGrid;

    /**
     * Number of collumns.
     */
    private int mCollumn;

    /**
     * Construct a MultiChannelsManagerView instance.
     * @param context The context.
     */
    public MultiChannelsManagerView(Context context) {
        this(context, null);
    }

    /**
     * Construct a MultiChannelsManagerView instance.
     * @param context The context.
     * @param attrs The attribute set.
     */
    public MultiChannelsManagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_multi_channels_manager, this, true);
        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiChannelsManagerView);
        mCollumn = typedArray.getInteger(R.styleable.MultiChannelsManagerView_collumns, 2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, mCollumn);
        mChannelsGrid.setLayoutManager(gridLayoutManager);
    }

    /**
     * Set adapter to the recycle view.
     * @param adapter The recycle adapter.
     */
    public void setChannelsAdapter(RecyclerView.Adapter adapter) {
        mChannelsGrid.setAdapter(adapter);
    }
}
