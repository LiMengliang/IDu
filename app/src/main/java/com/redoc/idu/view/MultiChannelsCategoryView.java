package com.redoc.idu.view;

import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.contract.IMultiChannelsCategoryContract;

/**
 * Multi-channels category view.
 * Created by limen on 2016/8/27.
 */

public class MultiChannelsCategoryView implements IMultiChannelsCategoryContract.IMultiChannelsCategoryView {

    /**
     * The root fragment of the view.
     */
    private MultiChannelsCategoryFragment rootFragment;

    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mMultiChannelsCategoryPresenter;


    /**
     * Get or create the view of the category.
     *
     * @return Root fragment.
     */
    @Override
    public MultiChannelsCategoryFragment getOrCreateRootFragment() {
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
    public void setPresenter(IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter presenter) {
        mMultiChannelsCategoryPresenter = presenter;
        mMultiChannelsCategoryPresenter.onAttached();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        getOrCreateRootFragment().setChannels(mMultiChannelsCategoryPresenter.getAllChannels());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToChannel(IChannelContract.IChannelPresenter channelPresenter) {

    }
}
