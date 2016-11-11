package com.redoc.idu.presenter.multichannel;

import com.redoc.idu.IView;
import com.redoc.idu.contract.ICategory;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelManagerContract;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;
import com.redoc.idu.model.MultiChannelsCategory;
import com.redoc.idu.view.category.multiChannel.MultiChannelsCategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter of multi-channel category.
 * Created by limen on 2016/8/27.
 */
public abstract class MultiChannelsCategoryPresenter implements IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter {

    private MultiChannelsCategory mMultiChannelsCategory;
    /**
     * Multi-channel category view.
     */
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryView mMultiChannelsCategoryView;

    /**
     * Presenter to selected channel.
     */
    private IMultiChannelContract.IMultiChannelPresenter mSelectedChannel;

    /**
     * Presenters to all channels.
     */
    private List<IMultiChannelContract.IMultiChannelPresenter> mChannels;

    /**
     * Presenters to followed channels.
     */
    private List<IMultiChannelContract.IMultiChannelPresenter> mFollowedChannels;

    private IMultiChannelManagerContract.IMultiChannelManagerPresenter mMultiChannelsManagerPresenter;

    /**
     * Construct a {@link MultiChannelsCategoryPresenter}
     */
    public MultiChannelsCategoryPresenter(MultiChannelsCategory multiChannelsCategory) {
        mMultiChannelsCategory = multiChannelsCategory;
        mMultiChannelsCategoryView = new MultiChannelsCategoryView();
        mMultiChannelsManagerPresenter = new MultiChannelsManagerPresenter(getAllChannels());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public  void selectChannel(int index) {
        IMultiChannelContract.IMultiChannelPresenter channel = mFollowedChannels.get(index);
        if(!channel.isFollowed()) {
            channel = mFollowedChannels.get(0);
        }
        mSelectedChannel = channel;
        mMultiChannelsCategoryView.switchToChannel(mSelectedChannel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateChannelSelection() {
        if(mSelectedChannel == null || !mSelectedChannel.isFollowed()) {
            mSelectedChannel = mFollowedChannels.get(0);
            selectChannel(0);
            return;
        }
        int index = mFollowedChannels.indexOf(mSelectedChannel);
        selectChannel(index);
        return;
    }

    /**
     * Get all followed channels.
     *
     * @return Presenters of all followed channels.
     */
    @Override
    public List<IMultiChannelContract.IMultiChannelPresenter> getAllFollowedChannels() {
        mFollowedChannels = new ArrayList<>();
        for(IMultiChannelContract.IMultiChannelPresenter channel : getAllChannels()) {
            if(channel.isFollowed()) {
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
    public IMultiChannelContract.IMultiChannelPresenter getSelectedChannel() {
        if(mSelectedChannel != null && mSelectedChannel.isFollowed()) {
            return mSelectedChannel;
        }
        selectChannel(0);
        return mSelectedChannel;
    }

    @Override
    public IMultiChannelManagerContract.IMultiChannelManagerPresenter getMultiChannelManager() {
        return mMultiChannelsManagerPresenter;
    }

    /**
     * Get attached {@link ICategory.ICategoryView}
     *
     * @return Attached {@link ICategory.ICategoryView}
     */
    @Override
    public IMultiChannelsCategoryContract.IMultiChannelsCategoryView getAttachedCategoryView() {
        return mMultiChannelsCategoryView;
    }

    /**
     * On view attached.
     */
    @Override
    public void onAttached(IView view) {
        mMultiChannelsCategoryView.initialize();
    }

    /**
     * On view detached.
     */
    @Override
    public void onDetached() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSelected() {
        mMultiChannelsCategoryView.initialize();
        if(mSelectedChannel == null) {
            mSelectedChannel = getAllFollowedChannels().get(0);
        }
        mMultiChannelsCategoryView.switchToChannel(mSelectedChannel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startManage() {
        mMultiChannelsCategoryView.showChannelsManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmOrCancelManage(boolean confirmOrCancel) {
        mMultiChannelsCategoryView.hideChannelsManager();
        if(confirmOrCancel) {
        }
        else {
        }
    }

    protected MultiChannelsCategory getMultiChannelsCategory() {
        return mMultiChannelsCategory;
    }
}
