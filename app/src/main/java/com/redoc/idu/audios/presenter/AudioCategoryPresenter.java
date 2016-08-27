package com.redoc.idu.audios.presenter;

import com.redoc.idu.presenter.SingleChannelCategoryPresenter;

/**
 *  Audio Category Presenter
 * Created by limen on 2016/8/27.
 */
public class AudioCategoryPresenter extends SingleChannelCategoryPresenter {

    /**
     * Get category name.
     *
     * @return Name of the category.
     */
    @Override
    public String getCategoryName() {
        return "音频";
    }
}
