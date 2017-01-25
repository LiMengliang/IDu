package com.redoc.idu.framework.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

import java.util.List;

/**
 * Categories contract.
 *
 * Created by limen on 2016/8/17.
 */
public interface ICategories {
    /**
     * Categories view.
     */
    interface ICategoriesView extends IView<ICategoriesPresenter> {

        /**
         * Switch category.
         * @param categoryView View of the category.
         */
        void switchToCategory(ICategory.ICategoryView categoryView);
    }

    /**
     * Categories presenter.
     */
    interface ICategoriesPresenter extends IPresenter {
        /**
         * On select a category.
         * @param categoryPresenter Presenter of selected cateogry.
         */
        void onSelectACategory(ICategory.ICategoryPresenter categoryPresenter);

        /**
         * Get a list of {@link ICategoriesPresenter}
         * @return A list of  {@link ICategoriesPresenter} contained.
         */
        List<ICategory.ICategoryPresenter> getCategoryPresenters();
    }
}
