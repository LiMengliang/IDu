package com.redoc.idu.view;

import android.support.v4.app.Fragment;

import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.contract.IMultiChannelsCategoryContract;

import java.util.List;

/**
 * Multi-channels category view.
 * Created by limen on 2016/8/27.
 */

public class MultiChannelsCategoryView implements IMultiChannelsCategoryContract.IMultiChannelsCategoryView {

    /**
     * The root fragment of the view.
     */
    private Fragment rootFragment;


    /**
     * Get or create the view of the category.
     *
     * @return Root fragment.
     */
    @Override
    public Fragment getOrCreateRootFragment() {
        if(rootFragment == null) {
            rootFragment = new MultiChannelsCategoryFragment();
        }
        return rootFragment;
    }

    /**
     * Set presenter.
     *
     * @param presenter The presenter.
     */
    @Override
    public void setPresenter(ICategoryContract.ICategoryPresenter presenter) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void switchToChannel(IChannelContract.IChannelPresenter channelPresenter) {

    }
}
