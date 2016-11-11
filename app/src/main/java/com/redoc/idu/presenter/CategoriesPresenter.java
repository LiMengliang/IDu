package com.redoc.idu.presenter;

import android.support.annotation.NonNull;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.IView;
import com.redoc.idu.radio.model.AudioCategory;
import com.redoc.idu.radio.presenter.AudioCategoryPresenter;
import com.redoc.idu.contract.ICategories;
import com.redoc.idu.contract.ICategory;
import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.MultiChannelsCategory;
import com.redoc.idu.news.presenter.NewsCategoryPresenter;
import com.redoc.idu.settings.model.SettingsCategory;
import com.redoc.idu.settings.presenter.SettingsCategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories presenter.
 *
 * Created by limen on 2016/8/17.
 */
public class CategoriesPresenter implements ICategories.ICategoriesPresenter {

    /**
     * The attached {@link ICategories.ICategoriesView}
     */
    private ICategories.ICategoriesView mCategoriesView;

    /**
     * The attached {@link ICategories.ICategoriesPresenter}
     */
    private List<ICategory.ICategoryPresenter> mCategoryPresenters;

    /**
     * On a category is selected.
     * @param categoryPresenter Presenter of selected category.
     */
    @Override
    public void onSelectACategory(ICategory.ICategoryPresenter categoryPresenter) {
        // TODO: modify model here.
        mCategoriesView.switchToCategory(categoryPresenter);
    }

    /**
     * Create a CategoriesPresenter instance.
     * @param view Attached view.
     */
    public CategoriesPresenter(@NonNull ICategories.ICategoriesView view) {
        mCategoryPresenters = new ArrayList<>();
        // TODO: We need a extensible way to add categories.
        for(Category category :  IDuApplication.CategoryAndChannelManager.getCategories()) {
            if(category.getCATEGORY_NAME().equals("首页")) {
                mCategoryPresenters.add(new NewsCategoryPresenter(new MultiChannelsCategory(category)));
            }
            else if(category.getCATEGORY_NAME().equals( "电台")) {
                mCategoryPresenters.add(new AudioCategoryPresenter(new AudioCategory(category)));
            }
        }
        mCategoryPresenters.add(new SettingsCategoryPresenter(new SettingsCategory()));
        mCategoriesView = view;
        mCategoriesView.setPresenter(this);
    }

    /**
     *On presenter attached to view.
     * @param view The view attached.
     */
    @Override
    public void onAttached(IView view) {
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
    @Override
    public List<ICategory.ICategoryPresenter> getCategoryPresenters() {
        return mCategoryPresenters;
    }
}
