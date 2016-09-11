package com.redoc.idu.presenter;

import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.contract.IMultiChannelsCategoryContract;
import com.redoc.idu.view.MultiChannelsCategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter of multi-channel category.
 * Created by limen on 2016/8/27.
 */
public abstract class MultiChannelsCategoryPresenter implements IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter {

    /**
     * Multi-channel category view.
     */
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryView mMultiChannelsCategoryView;

    private IChannelContract.IChannelPresenter mSelectedChannel;

    private List<IChannelContract.IChannelPresenter> mFollowedChannels;

    /**
     * Construct a {@link MultiChannelsCategoryPresenter}
     */
    public MultiChannelsCategoryPresenter() {
        mMultiChannelsCategoryView = new MultiChannelsCategoryView();
    }

    /**
     * On a channel is selected.
     *
     * @param selectedChannel Presenter of selected channel.
     */
    @Override
    public void onSelectAChannel(IChannelContract.IChannelPresenter selectedChannel) {
        mSelectedChannel = selectedChannel;
        mMultiChannelsCategoryView.switchToChannel(selectedChannel);
    }

    /**
     * On follow a channel.
     *
     * @param followedChannel Presenter of the followed channel.
     */
    @Override
    public void onFollowAChannel(IChannelContract.IChannelPresenter followedChannel) {

    }

    /**
     * On unfollow a channel.
     *
     * @param unfollowedChannel Presenter of the unfollowed channel.
     */
    @Override
    public void onUnfollowAChannel(IChannelContract.IChannelPresenter unfollowedChannel) {

    }

    /**
     * Get all channels in this category.
     *
     * @return All channels of this category.
     */
    @Override
    public List<IChannelContract.IChannelPresenter> getAllChannels() {
        return null;
    }

    /**
     * Get all followed channels.
     *
     * @return Presenters of all followed channels.
     */
    @Override
    public List<IChannelContract.IChannelPresenter> getAllFollowedChannels() {
        if(mFollowedChannels == null) {
            mFollowedChannels = new ArrayList<>();
            for(IChannelContract.IChannelPresenter channel : getAllChannels()) {
                mFollowedChannels.add(channel);
            }
        }
        return mFollowedChannels;
    }

    /**
     * Get selected channel.
     *
     * @return Presenter of selected channel.
     */
    @Override
    public IChannelContract.IChannelPresenter getSelectedChannel() {
        return mSelectedChannel;
    }

    /**
     * Get attached {@link com.redoc.idu.contract.ICategoryContract.ICategoryView}
     *
     * @return Attached {@link com.redoc.idu.contract.ICategoryContract.ICategoryView}
     */
    @Override
    public ICategoryContract.ICategoryView getAttachedCategoryView() {
        return mMultiChannelsCategoryView;
    }

    /**
     * On view attached.
     */
    @Override
    public void onAttached() {

    }

    /**
     * On view detached.
     */
    @Override
    public void onDetached() {

    }

    @Override
    public void onSelected() {
        mMultiChannelsCategoryView.initialize();
        if(mSelectedChannel == null) {
            mSelectedChannel = getAllFollowedChannels().get(0);
        }
        mMultiChannelsCategoryView.switchToChannel(mSelectedChannel);
    }
}