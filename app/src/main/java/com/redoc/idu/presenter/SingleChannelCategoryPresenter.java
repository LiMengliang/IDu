package com.redoc.idu.presenter;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.IView;
import com.redoc.idu.contract.ICategory;
import com.redoc.idu.view.widget.CategoryIconView;

/**
 * Single channel category presenter.
 * Created by limen on 2016/8/27.
 */
public abstract class SingleChannelCategoryPresenter implements ICategory.ICategoryPresenter {

    /**
     * Get attached {@link ICategory.ICategoryView} instance.
     *
     * @return Attached {@link ICategory.ICategoryView} instance.
     */
    @Override
    public ICategory.ICategoryView getAttachedCategoryView() {
        return null;
    }

    /**
     * On view attached.
     * @param view View attached.
     */
    @Override
    public void onAttached(IView view) {

    }

    /**
     * On view detached.
     */
    @Override
    public void onDetached() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICategory.ICategoryIconView getCategoryIconView() {
        CategoryIconView categoryIconView = new CategoryIconView(IDuApplication.Context);
        categoryIconView.setName(getCategoryName());
        categoryIconView.setIconResourceId(getSelectedIconResourceId(), getUnselectedIconResourceId(), 0x595a5e, 0xCD6839);
        return categoryIconView;
    }
}
