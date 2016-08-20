package com.redoc.idu.presenter;

import android.support.annotation.NonNull;

import com.redoc.idu.contract.ICategoriesContract;
import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.model.CategoriesProvider;
import com.redoc.idu.model.bean.Category;
import com.redoc.idu.news.presenter.NewsCategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories presenter.
 *
 * Created by limen on 2016/8/17.
 */
public class CategoriesPresenter implements ICategoriesContract.ICategoriesPresenter {

    /**
     * Categories model provider.
     */
    private CategoriesProvider mCategoriesProvider;

    /**
     * The attached {@link com.redoc.idu.contract.ICategoriesContract.ICategoriesView}
     */
    private ICategoriesContract.ICategoriesView mCategoriesView;

    /**
     * The attached {@link com.redoc.idu.contract.ICategoriesContract.ICategoriesPresenter}
     */
    private List<ICategoryContract.ICategoryPresenter> mCategoryPresenters;

    /**
     * Get categories.
     * @return All categories.
     */
    @Override
    public List<Category> getCategories() {
        return mCategoriesProvider.getCategories();
    }

    /**
     * Create a CategoriesPresenter instance.
     * @param view Attached view.
     */
    public CategoriesPresenter(@NonNull ICategoriesContract.ICategoriesView view) {
        mCategoriesProvider = new CategoriesProvider();
        mCategoryPresenters = new ArrayList<>();
        for(Category category : mCategoriesProvider.getCategories()) {
            ICategoryContract.ICategoryPresenter categoryPresenter = new NewsCategoryPresenter();
            mCategoryPresenters.add(categoryPresenter);
        }
        mCategoriesView = view;
        mCategoriesView.setPresenter(this);
    }

    /**
     *On presenter attached to view.
     */
    @Override
    public void onAttached() {
        mCategoriesView.setCategories(mCategoryPresenters);
    }

    /**
     * On presenter detach to view.
     */
    @Override
    public void onDetached() {

    }
}
