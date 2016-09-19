package com.redoc.idu.news.presenter;

import com.redoc.idu.R;
import com.redoc.idu.contract.IChannelContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.MultiChannelsCategory;
import com.redoc.idu.presenter.ChannelPresenter;
import com.redoc.idu.presenter.MultiChannelsCategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * News category presenter.
 *
 * Created by limen on 2016/8/20.
 */
public class NewsCategoryPresenter extends MultiChannelsCategoryPresenter {

    /**
     * News category instance.
     */
    private MultiChannelsCategory mNewsCategory;

    /**
     * Channels.
     */
    private List<IChannelContract.IChannelPresenter> mChannels;

    /**
     * Construct a {@link NewsCategoryPresenter} instance.
     * @param newsCategory News category model.
     */
    public NewsCategoryPresenter(MultiChannelsCategory newsCategory) {
        super();
        mNewsCategory = newsCategory;
        getAttachedCategoryView().setPresenter(this);
    }

    /**
     * Get news category name.
     * @return News category name.
     */
    @Override
    public String getCategoryName() {
        return mNewsCategory.getCategory().getCATEGORY_NAME();
    }

    /**
     * Get category icon resource id.
     *
     * @return Id of icon.
     */
    @Override
    public int getIconResourceId() {
        return R.drawable.category_main;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IChannelContract.IChannelPresenter> getAllChannels() {
        if(mChannels == null) {
            mChannels = new ArrayList<>();
            for(Channel channel : mNewsCategory.getChannels()) {
                mChannels.add(new ChannelPresenter(channel));
            }
        }
        return mChannels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSelected() {
        super.onSelected();
    }
}
