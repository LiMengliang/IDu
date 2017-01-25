package com.redoc.idu.radio.model;

import com.redoc.idu.framework.model.bean.Category;

/**
 * Audio category
 *
 * Created by limen on 2016/8/28.
 */
public class AudioCategory{
    private Category mCategory;
    /**
     * Construct a category instance.
     */
    public AudioCategory(long id) {
        mCategory = new Category(id, "电台");
    }

    public AudioCategory(Category category) {
        mCategory = category;
    }

    public Category getCategory() {
        return mCategory;
    }
}
