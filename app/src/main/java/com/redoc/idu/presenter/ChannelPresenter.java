package com.redoc.idu.presenter;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.dao.ChannelDao;

/**
 * Presenter for channel.
 * Created by limen on 2016/9/5.
 */
public class ChannelPresenter implements IChannelContract.IChannelPresenter {

    private Channel mChannel;
    public ChannelPresenter(Channel channel) {
        mChannel = channel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChannelId() {
        return mChannel.getCATEGORY_ID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChannelName() {
        return mChannel.getCHANNEL_NAME();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowed() {
        return mChannel.getFOLLOWED();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void follow() {
        IDuApplication.CategoryAndChannelManager.followAChannel(this.mChannel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unfollow() {
        IDuApplication.CategoryAndChannelManager.unfollowAChannel(this.mChannel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
