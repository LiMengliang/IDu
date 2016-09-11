package com.redoc.idu.settings.presenter;

import com.redoc.idu.R;
import com.redoc.idu.presenter.SingleChannelCategoryPresenter;
import com.redoc.idu.settings.model.SettingsCategory;

/**
 * Settings category presenter.
 *
 * Created by limen on 2016/8/27.
 */
public class SettingsCategoryPresenter extends SingleChannelCategoryPresenter {

    private SettingsCategory mSettingsCategory;

    /**
     * Construct a {@link SettingsCategoryPresenter} instance.
     *
     * @param settingsCategory Model of settings category.
     */
    public SettingsCategoryPresenter(SettingsCategory settingsCategory) {
        mSettingsCategory = settingsCategory;
    }

    /**
     * Get category name.
     *
     * @return Name of the category.
     */
    @Override
    public String getCategoryName() {
        return mSettingsCategory.getCATEGORY_NAME();
    }

    /**
     * Get category icon resource id.
     *
     * @return Id of icon.
     */
    @Override
    public int getIconResourceId() {
        return R.drawable.category_setting;
    }

    @Override
    public void onSelected() {

    }
}
