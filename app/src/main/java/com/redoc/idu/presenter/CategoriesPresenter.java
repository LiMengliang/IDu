package com.redoc.idu.presenter;

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
    // TODO: Think about if we can do anything to avoid ooo and memory leak
    private List<ICategory.ICategoryPresenter> mCategoryPresenters;

    /**
     * On a category is selected.
     * @param categoryPresenter Presenter of selected category.
     */
    @Override
    public void onSelectACategory(ICategory.ICategoryPresenter categoryPresenter) {
        ICategory.ICategoryView categoryView = categoryPresenter.getAttachedCategoryView();
        // TODO: modify model here.
        categoryPresenter.onSelected();
        mCategoriesView.switchToCategory(categoryView);
    }

    /**
     * Create a CategoriesPresenter instance.
     */
    public CategoriesPresenter() {
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
    }

    /**
     *On presenter attached to view.
     * @param view The view attached.
     */
    @Override
    public void onAttached(IView view) {
        if(view instanceof ICategories.ICategoriesView)
        {
            mCategoriesView = (ICategories.ICategoriesView)view;
        }
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
