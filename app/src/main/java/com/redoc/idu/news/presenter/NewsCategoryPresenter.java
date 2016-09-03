package com.redoc.idu.news.presenter;

import com.redoc.idu.R;
import com.redoc.idu.news.model.NewsCategory;
import com.redoc.idu.presenter.MultiChannelsCategoryPresenter;

/**
 * News category presenter.
 *
 * Created by limen on 2016/8/20.
 */
public class NewsCategoryPresenter extends MultiChannelsCategoryPresenter {

    /**
     * News category instance.
     */
    private NewsCategory mNewsCategory;

    /**
     * Construct a {@link NewsCategoryPresenter} instance.
     * @param newsCategory News category model.
     */
    public NewsCategoryPresenter(NewsCategory newsCategory) {
        mNewsCategory = newsCategory;
    }

    /**
     * Get news category name.
     * @return News category name.
     */
    @Override
    public String getCategoryName() {
        return mNewsCategory.getmCategoryName();
    }

    /**
     * Get category icon resource id.
     *
     * @return Id of icon.
     */
    @Override
    public int getIconResourceId() {
        return R.drawable.category_main;
    }
}
