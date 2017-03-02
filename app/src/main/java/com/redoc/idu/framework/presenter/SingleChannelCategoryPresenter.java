package com.redoc.idu.framework.presenter;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.IView;
import com.redoc.idu.framework.contract.ICategory;
import com.redoc.idu.framework.view.widget.LabeledIconView;

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
        LabeledIconView categoryIconView = new LabeledIconView(IDuApplication.Context);
        categoryIconView.setName(getCategoryName());
        categoryIconView.setIconResourceId(getSelectedIconResourceId(), getUnselectedIconResourceId());
        return categoryIconView;
    }
}
