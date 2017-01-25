package com.redoc.idu.framework.presenter;

import com.redoc.idu.framework.contract.multichannel.IMultiChannelContract;
import com.redoc.idu.framework.model.MultiChannelsCategory;
import com.redoc.idu.framework.presenter.multichannel.MultiChannelsCategoryPresenter;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for {@link MultiChannelsCategoryPresenter}
 * Created by limen on 2016/8/27.
 */
public class MultiChannelsCategoryPresenterUnitTests {
    class MockMultiChannelsCategoryPresenter extends MultiChannelsCategoryPresenter {

        /**
         * {@inheritDoc}
         */
        @Override
        public void selectChannel(int index) {

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<IMultiChannelContract.IMultiChannelPresenter> getAllChannels() {
            return null;
        }

        /**
         * Get category name.
         *
         * @return Name of the category.
         */
        @Override
        public String getCategoryName() {
            return "Mock";
        }

        /**
         * Get category icon resource id.
         *
         * @return Id of icon.
         */
        @Override
        public int getSelectedIconResourceId() {
            return 0;
        }

        @Override
        public int getUnselectedIconResourceId() {
            return 0;
        }

        public MockMultiChannelsCategoryPresenter(MultiChannelsCategory multiChannelsCategory) {
            super(multiChannelsCategory);
        }
    }
    /**
     * Test {@link MultiChannelsCategoryPresenter} getAttachedCategoryView can return not null instance.
     */
    @Test
    public void MultiChannelCategoryPresenter_getAttachedCategoryView_notNull() {
        MultiChannelsCategoryPresenter multiChannelsCategoryPresenter = new MockMultiChannelsCategoryPresenter(null);
        Assert.assertNotNull(multiChannelsCategoryPresenter.getAttachedCategoryView());
    }
}
