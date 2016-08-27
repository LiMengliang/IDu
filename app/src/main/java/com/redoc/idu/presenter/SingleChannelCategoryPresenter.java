package com.redoc.idu.presenter;

import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.contract.ICategoryContract.ICategoryView;

/**
 * Single channel category presenter.
 * Created by limen on 2016/8/27.
 */
public abstract class SingleChannelCategoryPresenter implements ICategoryContract.ICategoryPresenter {

    /**
     * Get attached {@link ICategoryContract.ICategoryView} instance.
     *
     * @return Attached {@link ICategoryContract.ICategoryView} instance.
     */
    @Override
    public ICategoryContract.ICategoryView getAttachedCategoryView() {
        return null;
    }

    /**
     * On view attached.
     */
    @Override
    public void onAttached() {

    }

    /**
     * On view detached.
     */
    @Override
    public void onDetached() {

    }
}
