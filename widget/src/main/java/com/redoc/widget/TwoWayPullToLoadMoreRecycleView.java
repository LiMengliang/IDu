package com.redoc.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Recycle view that can response to pull down to get latest, and pull up to get more.
 * Created by Mengliang Li on 3/5/2017.
 */

public class TwoWayPullToLoadMoreRecycleView extends LinearLayout {

    private PullToLoadMoreRecyclerView mRecyclerView;
    private RelativeLayout mHeader;
    private Integer mStartY;
    private int mScrollDistance;
    private static int HeaderHeight = 200;
    private boolean isInGetLatestMode = false;
    private boolean mightInGetLatestModel = false;

    /**
     * Construct a {@link TwoWayPullToLoadMoreRecycleView}
     * @param context Context.
     */
    public TwoWayPullToLoadMoreRecycleView(Context context) {
        super(context);
        initializeView(context);
    }

    /**
     * Construct a {@link TwoWayPullToLoadMoreRecycleView}
     * @param context Context.
     * @param attrs Attributes.
     */
    public TwoWayPullToLoadMoreRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    /**
     * Construct a {@link TwoWayPullToLoadMoreRecycleView}
     * @param context Context.
     * @param attrs Attributes.
     * @param defStyleAttr Default attributes.
     */
    public TwoWayPullToLoadMoreRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);
    }

    /**
     * {@inheritDoc}
     * If user finger down when recycle view reaches its top, that means there is a potential that user wants to pull down to get latest.
     * If that's true, and in ACTION_MOVE, user are pulling down, that menas users wants to pull down to get latest, (GetLatestMode)
     * so this view group need to intercept the event.
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                if(!isInGetLatestMode) {
                    isInGetLatestMode = mightInGetLatestModel && mStartY < (int)event.getRawY();
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if(mRecyclerView.getTranslationY() == 0 && mRecyclerView.isReachedHead()) {
                    mStartY = (int)event.getRawY();
                    mightInGetLatestModel = true;
                }
                break;
        }
        return isInGetLatestMode;
    }

    /**
     * {@inheritDoc}
     * Return true if it is a "GetLatestMode".
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if(isInGetLatestMode) {
                    mightInGetLatestModel = false;
                    isInGetLatestMode = false;
                    scrollBy(0, -mScrollDistance - 200);
                    scrollBy(0, 200);
                    mScrollDistance = 0;
                }
                mStartY = null;
                break;
            case MotionEvent.ACTION_MOVE:
                int currentY = (int)event.getRawY();
                int delta =  mStartY - currentY;
                scrollBy(0, delta);
                mScrollDistance += (delta);
                mStartY = currentY;
               break;
            case MotionEvent.ACTION_DOWN:
                if(mightInGetLatestModel) {
                    mStartY = (int)event.getRawY();
                }
                break;
        }
        return isInGetLatestMode;
    }

    private void initializeView(Context context) {
        setOrientation(VERTICAL);
        mRecyclerView = new PullToLoadMoreRecyclerView(context);
        mHeader = createHeaderContainer(context);
        scrollBy(0, mHeader.getLayoutParams().height);
        this.addView(mHeader, 0);
        this.addView(mRecyclerView, 1);
        LinearLayout.LayoutParams recycleViewLayoutParameter =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        mRecyclerView.setLayoutParams(recycleViewLayoutParameter);
    }

    private RelativeLayout createHeaderContainer(Context context) {
        RelativeLayout headerContainer = new RelativeLayout(context);
        headerContainer.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HeaderHeight));
        return headerContainer;
    }

    /**
     * Set header.
     * @param header The header.
     */
    public void setHeader(View header) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        header.setLayoutParams(params);
        mHeader.addView(header);
    }

    /**
     * Set footer.
     * @param footer The footer.
     */
    public void setFooter(View footer) {
        mRecyclerView.setFooterView(footer);
    }

    /**
     * Set adapter for the inner recycle view.
     * @param adapter The adapter.
     */
    public void setInnerAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setInnerAdapter(adapter);
    }

    /**
     * Set layout manager.
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Set divider.
     * @param divider
     */
    public void setDivider(RecyclerView.ItemDecoration divider) {
        mRecyclerView.setDivider(divider);
    }

    /**
     * Force refresh.
     */
    public void forceRefresh() {
        mRecyclerView.forceRefresh();
    }

    /**
     * Set on scroll listener.
     * @param listener
     */
    public void setOnScrollListener(RecyclerView.OnScrollListener listener) {
        mRecyclerView.addOnScrollListener(listener);
    }
    /**
     * Get last visible item position.
     * @return Last visible item position.
     */
    public int getLastVisiblePosition() {
        return mRecyclerView.getLastVisiblePosition();
    }

    /**
     * Get first visible item position.
     * @return First visible item position.
     */
    public int getFirstVisiblePosition() {
        return getFirstVisiblePosition();
    }
}
