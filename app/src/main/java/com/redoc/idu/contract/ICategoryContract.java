package com.redoc.idu.contract;

/**
 * Contract between {@link ICategoryView} and {@link ICategoryPresenter}
 *
 * Created by limen on 2016/8/20.
 */
public interface ICategoryContract {

    /**
     * Category view.
     */
    interface ICategoryView {

    }

    /**
     * Category presenter.
     */
    interface ICategoryPresenter {
        String getCategoryName();
    }
}
