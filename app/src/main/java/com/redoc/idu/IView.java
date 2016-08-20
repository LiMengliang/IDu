package com.redoc.idu;

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
}
