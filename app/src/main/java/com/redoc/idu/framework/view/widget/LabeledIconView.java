package com.redoc.idu.framework.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.ICategory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Category icon view.
 * Created by limen on 2016/8/19.
 */
public class LabeledIconView extends LinearLayout implements ICategory.ICategoryIconView {
    /**
     * The icon image view.
     */
    @BindView(R.id.icon)
    ImageView mIcon;

    /**
     * The icon name text view.
     */
    @BindView(R.id.name)
    TextView mName;

    private int mSelectedIconResourceId;
    private int mUnselectedIconResourceId;

    private ICategory.ICategoryPresenter mCategoryPresenter;

    /**
     * Construct a CategoryIconView instance.
     * @param context The context.
     */
    public LabeledIconView(Context context) {
        this(context, null);
    }

    /**
     * Construct a CategoryIconView instance with attributes.
     * @param context The context.
     * @param attrs The attributes.
     */
    public LabeledIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_category, this, true);
        ButterKnife.bind(this);
        if(attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabeledIconView);
            // setIconResourceId(R.drawable.category_main);
           // setName(typedArray.getString(R.styleable.LabeledIconView_name));
            typedArray.recycle();
        }
    }

    /**
     * Set icon resource.
     */
    public void setIconResourceId(int selectedIconResourceId, int unselectedIconResourceId) {
        mUnselectedIconResourceId = unselectedIconResourceId;
        mSelectedIconResourceId = selectedIconResourceId;
        select(false);
    }

    /**
     * Set icon name.
     * @param name The name of the category.
     */
    public void setName(String name) {
        mName.setText(name);
    }

    /**
     * Select or unselect a icon.
     * @param selectOrNot Select or not.
     */
    public void select(boolean selectOrNot) {
        if(selectOrNot) {
            mIcon.setImageResource(mSelectedIconResourceId);
            mName.setSelected(true);
        }
        else {
            mIcon.setImageResource(mUnselectedIconResourceId);
            mName.setSelected(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLayoutParameter(ViewGroup.LayoutParams layoutParameter) {
        setLayoutParams(layoutParameter);
    }

    /**
     * Get view.
     * @return This.
     */
    public View getView() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(ICategory.ICategoryPresenter presenter) {
        mCategoryPresenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICategory.ICategoryPresenter getPresenter() {
        return mCategoryPresenter;
    }
}
