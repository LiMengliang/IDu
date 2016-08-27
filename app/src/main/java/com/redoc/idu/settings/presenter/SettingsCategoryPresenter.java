package com.redoc.idu.settings.presenter;

import com.redoc.idu.presenter.SingleChannelCategoryPresenter;

/**
 * Created by limen on 2016/8/27.
 */
public class SettingsCategoryPresenter extends SingleChannelCategoryPresenter {
    /**
     * Get category name.
     *
     * @return Name of the category.
     */
    @Override
    public String getCategoryName() {
        return "设置";
    }
}
