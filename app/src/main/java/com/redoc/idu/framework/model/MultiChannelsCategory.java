package com.redoc.idu.framework.model;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.model.bean.Category;
import com.redoc.idu.framework.model.bean.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi channels category.
 *
 * Created by limen on 2016/8/28.
 */
public class MultiChannelsCategory {
    private Category mCategory;

    /**
     * Construct a Multi channels category instance.
     *
     * @param id ID of the category.
     */
    public MultiChannelsCategory(long id) {
        mCategory = new Category(id, "首页");
    }

    /**
     * Construct a Multi channels category instance.
     * @param category The category.
     */
    public MultiChannelsCategory(Category category) {
        mCategory = category;
    }

    /**
     * Get channels.
     * @return All channels.
     */
    public List<Channel> getChannels() {
        List<Channel> newsChannels = new ArrayList<>();
        for(Channel channel : IDuApplication.CategoryAndChannelManager.getChannels()) {
            if(channel.getCATEGORY_ID().equals(mCategory.getId().toString())) {
                newsChannels.add(channel);
            }
        }
        return newsChannels;
    }

    /**
     * Get category bean.
     * @return The category bean.
     */
    public Category getCategory() {
        return mCategory;
    }
}
