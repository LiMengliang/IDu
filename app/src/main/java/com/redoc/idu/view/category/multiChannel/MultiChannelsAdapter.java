package com.redoc.idu.view.category.multiChannel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.redoc.idu.contract.IChannel;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;

/**
 * Adapter to channels in a category.
 * Created by limen on 2016/10/19.
 */
public class MultiChannelsAdapter extends FragmentStatePagerAdapter {

    /**
     * Multi channel category presenter.
     */
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mMultiChannelsCategoryPresenter;

    /**
     * Construct a MultiChannelsAdapter instance.
     * @param fm Fragment manager.
     * @param presenter The {@link com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter} instance.
     */
    public MultiChannelsAdapter(FragmentManager fm, IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter presenter) {
        super(fm);
        this.mMultiChannelsCategoryPresenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment getItem(int position) {
        IChannel.IChannelPresenter channelPresenter = mMultiChannelsCategoryPresenter.getAllFollowedChannels().get(position);
        IChannel.IChannelView channelView = channelPresenter.getOrCreateChannelView();
        return channelView.getRootFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return mMultiChannelsCategoryPresenter.getAllFollowedChannels().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
