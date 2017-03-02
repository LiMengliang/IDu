package com.redoc.idu.framework.view.category.singlechannel;

import android.support.v4.app.Fragment;

import com.redoc.idu.framework.contract.singlechannel.ISingleChannelCategoryContract;

/**
 * Single channel category view.
 * Created by limen on 2016/10/18.
 */
public abstract class SingleChannelCategoryView implements ISingleChannelCategoryContract.ISingleChannelCategoryView {

    private Fragment mRootFragment;
    @Override
    public Fragment getOrCreateRootFragment() {
        if(mRootFragment == null) {
            mRootFragment = createRootFragment();
        }
        return mRootFragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(ISingleChannelCategoryContract.ISingleChannelCategoryPresenter presenter) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ISingleChannelCategoryContract.ISingleChannelCategoryPresenter getPresenter() {
        return null;
    }

    /**
     * Create the root fragment.
     * @return A fragment.
     */
    protected Fragment createRootFragment() {
        return null;
    }
}
