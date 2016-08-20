package com.redoc.idu.model;

import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.bean.MultiChannelCategory;
import com.redoc.idu.model.bean.SingleChannelCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories provider
 *
 * Created by limen on 2016/8/17.
 */
public class CategoriesProvider {
    private MultiChannelCategory mNews = new MultiChannelCategory("首页");
    private SingleChannelCategory mAudio = new SingleChannelCategory("音频");
    private SingleChannelCategory mSettings = new SingleChannelCategory("设置");

    /**
     * Get categories
     * @return All categories.
     */
    public List<Category> getCategories() {
        return new ArrayList<Category>()
        {
            {
                add(mNews);
                add(mAudio);
                add(mSettings);
            }
        };
    }
}
