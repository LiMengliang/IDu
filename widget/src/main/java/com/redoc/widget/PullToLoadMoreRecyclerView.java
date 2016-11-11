package com.redoc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import static android.support.v7.widget.RecyclerView.*;

/**
 * A recycle view that be able to get more when pull down.
 * Created by limen on 2016/11/6.
 */
public class PullToLoadMoreRecyclerView extends RecyclerView {

    private View mFooterView;
    private RecyclerViewWithFooterAdapter mAdapter;

    /**
     * Construct an instance.
     * @param context Context.
     */
    public PullToLoadMoreRecyclerView(Context context) {
        super(context);
    }

    /**
     * Construct a instance.
     * @param context Context
     * @param attrs Attributes.
     */
    public PullToLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Construct a instance.
     * @param context The context.
     * @param attrs The attributes.
     * @param defStyle The default style.
     */
    public PullToLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Set footer to the recycle view.
     * @param footerView The footer view.
     */
    public void setFooterView(View footerView) {
        if(mAdapter == null) {
            mFooterView = footerView;
        }
        else {
            mAdapter.setFooterView(footerView);
        }
    }

    /**
     * Set inner adapter.
     * @param adapter The inner adapter.
     */
    public void setInnerAdapter(RecyclerView.Adapter adapter) {
        mAdapter = new RecyclerViewWithFooterAdapter(adapter);
        setAdapter(mAdapter);
        if(mFooterView != null) {
            mAdapter.setFooterView(mFooterView);
        }
    }

    public void forceRefresh() {
        if(mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Get last visible item position.
     * @return Last visible item position.
     */
    public int getLastVisiblePosition() {
        int position;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager)layoutManager;
            int[] lastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        }
        else {
            position = layoutManager.getItemCount() - 1;
        }
        return position;
    }

    /**
     * Get first visible item position.
     * @return First visible item position.
     */
    public int getFirstVisiblePosition() {
        int position;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
        }
        else if (layoutManager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] firstPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
            position = getMinPosition(firstPositions);
        }
        else {
            position = layoutManager.getItemCount() - 1;
        }
        return position;
    }

    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    private int getMinPosition(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    /**
     * Adapter for recycler with footer.
     */
    static class RecyclerViewWithFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private View mFooterView;
        private RecyclerView.Adapter mInnerAdapter;
        private int FooterViewType = 1;
        private int InnerViewType = 2;

        /**
         * Construct a instance.
         * @param innerAdapter The inner adapter.
         */
        public RecyclerViewWithFooterAdapter(RecyclerView.Adapter innerAdapter) {
            mInnerAdapter = innerAdapter;
        }

        /**
         * Set footer view.
         * @param footerView Footer view.
         */
        public void setFooterView(View footerView) {
            mFooterView = footerView;
        }

        private boolean isFooterViewPosition(int position) {
            return position >= mInnerAdapter.getItemCount();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == FooterViewType) {
                return new FooterViewHolder(mFooterView);
            }
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(isFooterViewPosition(position)) {
                return;
            }
            mInnerAdapter.onBindViewHolder(holder, position);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getItemViewType(int position) {
            if(isFooterViewPosition(position)) {
                return FooterViewType;
            }
            return InnerViewType;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getItemCount() {
            if(mInnerAdapter.getItemCount() > 0) {
                return mInnerAdapter.getItemCount() + 1;
            }
            return 0;
        }

        /**
         * Footer view holder.
         */
        class FooterViewHolder extends RecyclerView.ViewHolder {
            /**
             * Construct a instance.
             * @param view Footer view.
             */
            public FooterViewHolder(View view) {
                super(view);
            }
        }
    }

}
