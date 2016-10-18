package com.redoc.idu;

/**
 * Created by limen on 2016/8/17.
 *
 * Presenter interface.
 */
public interface IPresenter {

    /**
     * On view attached.
     * @param view The view attached.
     */
    void onAttached(IView view);

    /**
     * On view detached.
     */
    void onDetached();
}
