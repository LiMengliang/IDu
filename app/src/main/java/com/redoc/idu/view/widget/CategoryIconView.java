package com.redoc.idu.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redoc.idu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Category icon view.
 * Created by limen on 2016/8/19.
 */
public class CategoryIconView extends LinearLayout {
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

    /**
     * Construct a CategoryIconView instance.
     * @param context The context.
     */
    public CategoryIconView(Context context) {
        this(context, null);
    }

    /**
     * Construct a CategoryIconView instance with attributes.
     * @param context The context.
     * @param attrs The attributes.
     */
    public CategoryIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_category, this, true);
        ButterKnife.bind(this);
        if(attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryIconView);
            setIconResourceId(R.drawable.category_main);
            setName(typedArray.getString(R.styleable.CategoryIconView_name));
            typedArray.recycle();
        }
    }

    /**
     * Set icon resource.
     * @param iconResourceId The resource id.
     */
    public void setIconResourceId(int iconResourceId) {
        mIcon.setImageResource(iconResourceId);
    }

    /**
     * Set icon name.
     * @param name The name of the category.
     */
    public void setName(String name) {
        mName.setText(name);
    }
}
