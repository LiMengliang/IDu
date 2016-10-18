package com.redoc.idu.presenter;

import com.redoc.idu.model.MultiChannelsCategory;
import com.redoc.idu.presenter.multichannel.MultiChannelsCategoryPresenter;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Unit test for {@link MultiChannelsCategoryPresenter}
 * Created by limen on 2016/8/27.
 */
public class MultiChannelsCategoryPresenterUnitTests {
    class MockMultiChannelsCategoryPresenter extends MultiChannelsCategoryPresenter {

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
        public int getIconResourceId() {
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
