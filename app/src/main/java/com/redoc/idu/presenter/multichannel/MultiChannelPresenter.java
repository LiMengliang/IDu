package com.redoc.idu.presenter.multichannel;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.IView;
import com.redoc.idu.contract.IChannel;
import com.redoc.idu.contract.IDigest;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.contract.multichannel.IMultiChannelsCategoryContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.view.channel.ChannelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter for channel.
 * Created by limen on 2016/9/5.
 */
public abstract class MultiChannelPresenter implements IMultiChannelContract.IMultiChannelPresenter {
    private Channel mChannel;
    private IMultiChannelContract.IMultiChannelItemView mMultiChannelItemView;
    private IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter mCategory;
    private IChannel.IChannelView mChannelView;
    private List<IDigest.IDigestPresenter> mDigests;

    /**
     * Create a instance.
     * @param channel Channel.
     * @param category Category presenter.
     */
    public MultiChannelPresenter(Channel channel, IMultiChannelsCategoryContract.IMultiChannelsCategoryPresenter category) {
        mChannel = channel;
        mCategory = category;
        mDigests = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChannelId() {
        return mChannel.getCHANNEL_ID();
    }

    @Override
    public String getChannelDigestsLink(int index) {
        String linkFormat = mChannel.getLINK();
        return String.format(linkFormat, index);
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
    public IChannel.IChannelView getOrCreateChannelView() {
        if(mChannelView == null) {
            mChannelView = new ChannelView();
            mChannelView.setPresenter(this);
        }
        return mChannelView;
    }

    @Override
    public List<IDigest.IDigestPresenter> allCachedDigests() {
        return mDigests;
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

    protected void addDigestsToCachedDigestsHead(List<? extends IDigest.IDigestPresenter> digests) {
        List<IDigest.IDigestPresenter> tempDigests = new ArrayList<>(digests);
        tempDigests.addAll(mDigests);
        mDigests = tempDigests;
    }

    protected void addDigestsToCachedDigestsEnd(List<? extends IDigest.IDigestPresenter> digests){
        mDigests.addAll(digests);
    }
}
