package com.redoc.idu.presenter;

import android.support.annotation.NonNull;

import com.redoc.idu.audios.presenter.AudioCategoryPresenter;
import com.redoc.idu.contract.ICategoriesContract;
import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.model.CategoriesProvider;
import com.redoc.idu.model.bean.Category;
import com.redoc.idu.news.presenter.NewsCategoryPresenter;
import com.redoc.idu.settings.presenter.SettingsCategoryPresenter;

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
     * On a category is selected.
     * @param categoryPresenter Presenter of selected category.
     */
    @Override
    public void onSelectACategory(ICategoryContract.ICategoryPresenter categoryPresenter) {
        // TODO: modify model here.
        mCategoriesView.switchToCategory(categoryPresenter);
    }

    /**
     * Create a CategoriesPresenter instance.
     * @param view Attached view.
     */
    public CategoriesPresenter(@NonNull ICategoriesContract.ICategoriesView view) {
        mCategoriesProvider = new CategoriesProvider();
        mCategoryPresenters = new ArrayList<>();
        // TODO: We need a extensible way to add categories.
        for(Category category : mCategoriesProvider.getCategories()) {
            if(category.getmCategoryName() == "首页") {
                ICategoryContract.ICategoryPresenter categoryPresenter = new NewsCategoryPresenter();
                mCategoryPresenters.add(categoryPresenter);
            }
            else if(category.getmCategoryName() == "音频") {
                ICategoryContract.ICategoryPresenter categoryPresenter = new AudioCategoryPresenter();
                mCategoryPresenters.add(categoryPresenter);
            }
            else if(category.getmCategoryName() == "设置") {
                ICategoryContract.ICategoryPresenter categoryPresenter = new SettingsCategoryPresenter();
                mCategoryPresenters.add(categoryPresenter);
            }
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

    /**
     * Get Category Presenters.
     * @return A list of category presenter.
     */
    public List<ICategoryContract.ICategoryPresenter> getCategoryPresenters() {
        return mCategoryPresenters;
    }
}
