package com.redoc.idu.view.category.multiChannel;

import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;

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
        mMultiChannelsCategoryPresenter.onAttached(this);
        getOrCreateRootFragment().setPresenter(mMultiChannelsCategoryPresenter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter getPresenter() {
        return mMultiChannelsCategoryPresenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToChannel(IMultiChannelContract.IMultiChannelPresenter selectedChannel) {
        int index = mMultiChannelsCategoryPresenter.getAllFollowedChannels().indexOf(selectedChannel);
        rootFragment.switchToChannel(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showChannelsManager() {
        getOrCreateRootFragment().showChannelManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideChannelsManager() {
        getOrCreateRootFragment().hideChannelsManager(true);
    }
}
