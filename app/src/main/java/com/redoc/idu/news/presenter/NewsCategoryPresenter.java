package com.redoc.idu.news.presenter;

import com.redoc.idu.contract.ICategoryContract;

/**
 * News category presenter.
 *
 * Created by limen on 2016/8/20.
 */
public class NewsCategoryPresenter implements ICategoryContract.ICategoryPresenter {

    /**
     * Get news category name.
     * @return News category name.
     */
    @Override
    public String getCategoryName() {
        return "新闻";
    }
}
