package com.redoc.idu.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redoc.idu.R;

/**
 * Category icon view.
 * Created by limen on 2016/8/19.
 */
public class CategoryIconView extends LinearLayout {
    /**
     * The icon image view.
     */
    private ImageView mIcon;

    /**
     * The icon name text view.
     */
    private TextView mName;

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
        mIcon = (ImageView)findViewById(R.id.icon);
        mName = (TextView)findViewById(R.id.name);
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
