package com.redoc.idu.model.bean;

/**
 * Created by limen on 2016/8/17.
 *
 * Category definition.
 */
public abstract class Category {
    /**
     * Name of the category.
     */
    private String mCategoryName;

    /**
     * Category name getter.
     * @return Category name.
     */
    public String getmCategoryName() {
        return mCategoryName;
    }

    /**
     * Construct a category instance.
     * @param categoryName Category name.
     */
    public Category(String categoryName) {
        mCategoryName = categoryName;
    }
}
