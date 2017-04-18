package com.redoc.idu.framework.contract;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Contract between {@link ICategoryView} and {@link ICategoryPresenter}
 *
 * Created by limen on 2016/8/20.
 */
public interface ICategory {

    /**
     * Category view.
     */
    interface ICategoryView<T extends ICategoryPresenter> extends IView<T> {

        /**
         * Get or create the view of the category.
         * @return
         */
        Fragment getOrCreateRootFragment();
    }

    interface ICategoryIconView extends IView<ICategoryPresenter> {

        /**
         * Set layout parameter.
         * @param layoutParameter Layout parameters.
         */
        void setLayoutParameter(ViewGroup.LayoutParams layoutParameter);

        /**
         * Get view.
         * @return View of the category icon view.
         */
        View getView();

        void select(boolean selectOrNot);
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
        int getSelectedIconResourceId();

        int getUnselectedIconResourceId();

        /**
         * Get attached {@link ICategoryView} instance.
         * @return Attached {@link ICategoryView} instance.
         */
        ICategoryView getAttachedCategoryView();

        /**
         * Get category icon.
         * @return Category icon view.
         */
        ICategoryIconView getCategoryIconView();

        /**
         * On a category is selected.
         */
        void onSelected();

        /**
         * Get selected channel.
         * @return
         */
        IChannel.IChannelPresenter getSelectedChannel();
    }
}
