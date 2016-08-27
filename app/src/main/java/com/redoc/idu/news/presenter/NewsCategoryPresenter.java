package com.redoc.idu.news.presenter;

import com.redoc.idu.presenter.MultiChannelsCategoryPresenter;

/**
 * News category presenter.
 *
 * Created by limen on 2016/8/20.
 */
public class NewsCategoryPresenter extends MultiChannelsCategoryPresenter {

    /**
     * Get news category name.
     * @return News category name.
     */
    @Override
    public String getCategoryName() {
        return "新闻";
    }
}
