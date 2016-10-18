package com.redoc.idu.presenter.multichannel;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.IView;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.dao.ChannelDao;

/**
 * Presenter for channel.
 * Created by limen on 2016/9/5.
 */
public class MultiChannelPresenter implements IMultiChannelContract.IMultiChannelPresenter {
    private Channel mChannel;
    private IMultiChannelContract.IMultiChannelItemView mMultiChannelItemView;
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mCategory;

    public MultiChannelPresenter(Channel channel, IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter category) {
        mChannel = channel;
        mCategory = category;
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
        // IDuApplication.CategoryAndChannelManager.followAChannel(this.mChannel);

        mMultiChannelItemView.setChannelItemStyle(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unfollow() {
        IDuApplication.CategoryAndChannelManager.unfollowAChannel(this.mChannel);
        mMultiChannelItemView.setChannelItemStyle(false);
    }

    @Override
    public IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter getCategory() {
        return mCategory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {
        if(IMultiChannelContract.IMultiChannelItemView.class.isAssignableFrom(view.getClass())) {
            mMultiChannelItemView = (IMultiChannelContract.IMultiChannelItemView)view;
            mMultiChannelItemView.setChannelItemStyle(isFollowed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
