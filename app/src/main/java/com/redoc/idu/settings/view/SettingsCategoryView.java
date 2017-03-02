package com.redoc.idu.settings.view;

import android.support.v4.app.Fragment;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.framework.view.category.singlechannel.SingleChannelCategoryView;

/**
 * View of settings.
 * Created by Mengliang Li on 2/25/2017.
 */

public class SettingsCategoryView extends SingleChannelCategoryView {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Fragment createRootFragment() {
        return new SettingsFragment();
    }
}
