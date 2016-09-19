package com.redoc.idu.contract;

import android.support.v4.app.Fragment;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

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

        /**
         * Get or create the view of the category.
         * @return
         */
        Fragment getOrCreateRootFragment();
    }

    /**
     * Category presenter.
     */
    interface ICategoryPresenter extends IPresenter {
        /**
         * Get category name.
         * @return Name of the category.
         */
        String getCategoryName();

        /**
         * Get category icon resource id.
         *
         * @return Id of icon.
         */
        int getIconResourceId();

        /**
         * Get attached {@link ICategoryView} instance.
         * @return Attached {@link ICategoryView} instance.
         */
        ICategoryView getAttachedCategoryView();

        /**
         * On a category is selected.
         */
        void onSelected();
    }
}
