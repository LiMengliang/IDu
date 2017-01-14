package com.redoc.idu;

import android.view.View;

/**
 * Created by limen on 2016/8/17.
 * Base view.
 */
public interface IView<T extends IPresenter> {
    /**
     * Set presenter.
     * @param presenter The presenter.
     */
    void setPresenter(T presenter);

    /**
     * Get presenter;
     * @return The presenter.
     */
    T getPresenter();
}
