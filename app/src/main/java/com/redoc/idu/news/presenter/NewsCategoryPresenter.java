package com.redoc.idu.news.presenter;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.framework.model.bean.Channel;
import com.redoc.idu.framework.model.MultiChannelsCategory;
import com.redoc.idu.framework.presenter.multichannel.MultiChannelsCategoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * News category presenter.
 *
 * Created by limen on 2016/8/20.
 */
public class NewsCategoryPresenter extends MultiChannelsCategoryPresenter {

    /**
     * Channels.
     */
    private List<IMultiChannelContract.IMultiChannelPresenter> mChannels;

    /**
     * Construct a {@link NewsCategoryPresenter} instance.
     * @param newsCategory News category model.
     */
    public NewsCategoryPresenter(MultiChannelsCategory newsCategory) {
        super(newsCategory);
        getAttachedCategoryView().setPresenter(this);
    }

    /**
     * Get news category name.
     * @return News category name.
     */
    @Override
    public String getCategoryName() {
        return getMultiChannelsCategory().getCategory().getCATEGORY_NAME();
    }

    /**
     * Get category icon resource id.
     *
     * @return Id of icon.
     */
    @Override
    public int getSelectedIconResourceId() {
        return R.drawable.category_main;
    }

    @Override
    public int getUnselectedIconResourceId() {
        return R.drawable.category_setting_unselected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IMultiChannelContract.IMultiChannelPresenter> getAllChannels() {
        if(mChannels == null) {
            mChannels = new ArrayList<>();
            for(Channel channel : getMultiChannelsCategory().getChannels()) {
                mChannels.add(new NewsChannelPresenter(channel, this));
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
