package com.redoc.idu.contract.singlechannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.ICategoryContract;

/**
 * Created by limen on 2016/10/18.
 * Contract between single channel category view and single channel category presenter.
 */
public interface ISingleChannelCategoryContract {
    /**
     * Single channel category view.
     */
    interface ISingleChannelCategoryView extends ICategoryContract.ICategoryView, IView<ISingleChannelCategoryPresenter> {

    }

    /**
     * Single channel category presenter.
     */
    interface ISingleChannelCategoryPresenter extends ICategoryContract.ICategoryPresenter {

    }
}
