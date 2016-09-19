package com.redoc.idu.view.multiChannel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redoc.idu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab head view.
 * Created by limen on 2016/9/15.
 */
public class TabHead extends LinearLayout {
    /**
     * Tab name color.
     */
    int mTabNameTextColor;

    /**
     * The text size.
     */
    float mTextSize;

    /**
     * The tab item count.
     */
    int count;

    /**
     * Linear layout parameters.
     */
    private LinearLayout.LayoutParams mParams;

    /**
     * Context.
     */
    private Context mContext;

    /**
     * On tab item selected listener.
     */
    private OnSelectTabListener mOnSelectTabItemListener;

    /**
     * Tab items.
     */
    private List<TabItem> mTabItems;

    /**
     * Construct a {@TabHead} instance.
     * @param context The context.
     */
    public TabHead(Context context) {
        super(context);
        mContext = context;
        mTabItems = new ArrayList<>();
    }

    /**
     * construct a {@TabHead} instance.
     * @param context The context.
     * @param attributeSet The attribute set.
     */
    public TabHead(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        mTabItems = new ArrayList<>();
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TabHead);
        mTabNameTextColor = typedArray.getColor(R.styleable.TabHead_tab_name_color, Color.WHITE);
        mTextSize = typedArray.getDimension(R.styleable.TabHead_tab_name_size, 12);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int chanelItemWidth = displayMetrics.widthPixels / 7;
        mParams = new LinearLayout.LayoutParams(chanelItemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        mParams.gravity = Gravity.CENTER_VERTICAL;
    }

    /**
     * Add a tab item.
     * @param name name of the item.
     */
    public void addTabItem(String name) {
        if(mTabItems == null) {
            mTabItems = new ArrayList<>();
        }
        TabItem tabItem = new TabItem(getContext(), this, name, mTabNameTextColor, mTextSize);
        mTabItems.add(tabItem);
        addView(tabItem.getText(), count, mParams);
        count++;
    }

    /**
     * Set tab items.
     * @param names Names of the tab items.
     */
    public void setTabItems(List<String> names) {
        if(mTabItems == null) {
            mTabItems = new ArrayList<>();
        }
        else {
            mTabItems.clear();
        }
        removeAllViews();
        for(int i = 0; i < names.size(); i++) {
            TabItem tabItem = new TabItem(getContext(), this, names.get(i), mTabNameTextColor, mTextSize);
            mTabItems.add(tabItem);
            addView(tabItem.getText(), i, mParams);
        }
    }

    /**
     * Get tab items.
     * @return A list of tab items.
     */
    public List<TabItem> getTabItems() {
        return mTabItems;
    }

    /**
     * Set on item selected listener.
     * @param listener The {@OnSelectTabListener} instance.
     */
    public void setOnTabItemSelectListener(OnSelectTabListener listener) {
        mOnSelectTabItemListener = listener;
    }

    /**
     * On tab item selected.
     * @param index The index of the tab item to select.
     */
    public void onTabItemSelected(int index) {
        for(int i = 0; i < mTabItems.size(); i++) {
            TabItem tabItem = mTabItems.get(i);
            if(i == index) {
                tabItem.setSelected(true);
                if(tabItem.mOnSelectTabListener != null) {
                    tabItem.mOnSelectTabListener.onSelect(index);
                }
            }
            else {
                tabItem.setSelected(false);
            }
        }
    }

    /**
     * Tab item.
     */
    static class TabItem {
        /**
         * If the item is selected.
         */
        private boolean mSelected;

        /**
         * The text view.
         */
        private TextView mTextView;

        /**
         * Listener to item selected.
         */
        private OnSelectTabListener mOnSelectTabListener;

        /**
         * Construct a tab item.
         * @param context The context.
         * @param tabHead The tab head this item belongs to.
         * @param tabName The tab name.
         * @param textColor Text color
         * @param textSize The text size.
         */
        public TabItem(Context context, final TabHead tabHead, String tabName, final int textColor, float textSize) {
            mTextView = new TextView(context);
            mTextView.setText(tabName);
            mTextView.setTextSize(textSize);
            mSelected = false;
            mTextView.setTextColor(textColor);
            final int index = tabHead.getTabItems().size();
            mTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    tabHead.onTabItemSelected(index);
                }
            });
        }

        /**
         * Get tab text view..
         * @return
         */
        public TextView getText() {
            return mTextView;
        }

        /**
         * Is selected item.
         * @return True if selected.
         */
        public boolean isSelected() {
            return mSelected;
        }

        /**
         * Set selected.
         * @param selected If selectd.
         */
        void setSelected(boolean selected) {
            mSelected = selected;
            if(selected) {
                mTextView.setAlpha(1.0f);
            }
            else {
                mTextView.setAlpha(0.5f);
            }
        }

        /**
         * Set on select listener.
         * @param listener The select tab listener.
         */
        void setOnSelectListener(OnSelectTabListener listener) {
            mOnSelectTabListener = listener;
        }
    }
}