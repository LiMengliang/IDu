package com.redoc.idu.news.model;

import com.redoc.idu.model.CategoriesProvider;
import com.redoc.idu.model.bean.BaseCategory;
import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.bean.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * News category.
 *
 * Created by limen on 2016/8/28.
 */
public class NewsCategory {
    private Category mCategory;

    /**
     * Construct a news category instance.
     */
    public NewsCategory(long id) {
        mCategory = new Category(id, "首页");
    }

    public NewsCategory(Category category) {
        mCategory = category;
    }

    public List<Channel> getChannels() {
        List<Channel> newsChannels = new ArrayList<>();
        for(Channel channel : CategoriesProvider.getChannels()) {
            if(channel.getCATEGORY_ID().equals(mCategory.getId().toString())) {
                newsChannels.add(channel);
            }
        }
        return newsChannels;
    }

    public Category getCategory() {
        return mCategory;
    }
}
