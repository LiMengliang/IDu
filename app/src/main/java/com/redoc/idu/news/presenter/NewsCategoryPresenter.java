package com.redoc.idu.news.presenter;

import android.util.AttributeSet;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;
import com.redoc.idu.contract.ICategory;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.MultiChannelsCategory;
import com.redoc.idu.presenter.multichannel.MultiChannelPresenter;
import com.redoc.idu.presenter.multichannel.MultiChannelsCategoryPresenter;
import com.redoc.idu.view.widget.CategoryIconView;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

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
    public int getIconResourceId() {
        return R.drawable.category_main;
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
