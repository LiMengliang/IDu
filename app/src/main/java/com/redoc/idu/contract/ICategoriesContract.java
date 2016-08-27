package com.redoc.idu.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;
import com.redoc.idu.model.bean.Category;

import java.util.List;

/**
 * Categories contract.
 *
 * Created by limen on 2016/8/17.
 */
public interface ICategoriesContract {
    /**
     * Categories view.
     */
    interface ICategoriesView extends IView<ICategoriesPresenter> {

        /**
         * Switch category.
         * @param categoryPresenter Presenter of the category.
         */
        void switchToCategory(ICategoryContract.ICategoryPresenter categoryPresenter);

        /**
         * Add category.
         */
        void addCategory();

        /**
         * Set categories.
         * @param categoryPresenters A list of category presenter.
         */
        void setCategories(List<ICategoryContract.ICategoryPresenter> categoryPresenters);
    }

    /**
     * Categories presenter.
     */
    interface ICategoriesPresenter extends IPresenter {
        /**
         * Get categories.
         * @return Categories.
         */
        List<Category> getCategories();

        /**
         * On select a category.
         * @param categoryPresenter Presenter of selected cateogry.
         */
        void onSelectACategory(ICategoryContract.ICategoryPresenter categoryPresenter);
    }
}
