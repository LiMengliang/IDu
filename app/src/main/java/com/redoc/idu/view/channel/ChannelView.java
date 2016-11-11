package com.redoc.idu.view.channel;

import android.support.v4.app.Fragment;

import com.redoc.idu.contract.IChannel;

/**
 * View of channel.
 * Created by limen on 2016/10/24.
 */
public class ChannelView implements IChannel.IChannelView {

    private IChannel.IChannelPresenter mChannelPresenter;
    private ChannelFragment mFragment;

    /**
     * {@inheritDoc}
     */
    @Override
    public Fragment getRootFragment() {
        if(mFragment == null) {
            mFragment = ChannelFragment.newInstance();
        }
        return mFragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDigests() {
        mFragment.updateDigests();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(IChannel.IChannelPresenter presenter) {
        mChannelPresenter = presenter;
        ((ChannelFragment)getRootFragment()).setPresenter(presenter);
    }
}
