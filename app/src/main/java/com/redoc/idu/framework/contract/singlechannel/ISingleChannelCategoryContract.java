package com.redoc.idu.framework.contract.singlechannel;

import com.redoc.idu.framework.contract.ICategory;

/**
 * Created by limen on 2016/10/18.
 * Contract between single channel category view and single channel category presenter.
 */
public interface ISingleChannelCategoryContract {
    /**
     * Single channel category view.
     */
    interface ISingleChannelCategoryView extends ICategory.ICategoryView<ISingleChannelCategoryPresenter> {

    }

    /**
     * Single channel category presenter.
     */
    interface ISingleChannelCategoryPresenter extends ICategory.ICategoryPresenter {

    }
}
