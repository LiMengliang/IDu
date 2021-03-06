package com.redoc.idu.settings.presenter;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.ICategory;
import com.redoc.idu.framework.contract.IChannel;
import com.redoc.idu.framework.presenter.SingleChannelCategoryPresenter;
import com.redoc.idu.settings.model.SettingsCategory;
import com.redoc.idu.settings.view.SettingsCategoryView;

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
     * {@inheritDoc}
     */
    @Override
    public ICategory.ICategoryView getAttachedCategoryView() {
        return new SettingsCategoryView();
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
    public int getSelectedIconResourceId() {
        return R.drawable.category_setting;
    }

    @Override
    public int getUnselectedIconResourceId() {
        return R.drawable.category_setting_unselected;
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void onSelected() {

    }

    /**
     * {@inheritdoc}
     */
    @Override
    public IChannel.IChannelPresenter getSelectedChannel() {
        return null;
    }
}
