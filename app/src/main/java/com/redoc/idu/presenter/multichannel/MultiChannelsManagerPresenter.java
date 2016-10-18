package com.redoc.idu.presenter.multichannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelManagerContract;

import java.util.List;

/**
 * Multi channels manager presenter.
 * Created by limen on 2016/9/25.
 */
public class MultiChannelsManagerPresenter implements IMultiChannelManagerContract.IMultiChannelManagerPresenter {
    private List<IMultiChannelContract.IMultiChannelPresenter> mChannels;

    /**
     * Construct a MultiChannelManagerPresenter.
     * @param channels
     */
    public MultiChannelsManagerPresenter(List<IMultiChannelContract.IMultiChannelPresenter> channels) {
        mChannels = channels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IMultiChannelContract.IMultiChannelPresenter> getAllChannels() {
        return mChannels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void followAChannel(IMultiChannelContract.IMultiChannelPresenter channel) {
        channel.follow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unFollowAChannel(IMultiChannelContract.IMultiChannelPresenter channel) {
        channel.unfollow();
    }

    @Override
    public void onAttached(IView view) {

    }

    @Override
    public void onDetached() {

    }
}
