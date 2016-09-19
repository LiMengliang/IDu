package com.redoc.idu.news.presenter;

import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.MultiChannelsCategory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link NewsCategoryPresenter}
 * Created by limen on 2016/8/21.
 */
public class NewsCategoryPresenterUnitTests {
    /**
     * Test getCategoryName can return expected result.
     */
    @Test
    public void getCategoryName_isCorrect() {
        NewsCategoryPresenter newsCategoryPresenter = new NewsCategoryPresenter(new MultiChannelsCategory(new Category(1L, "首页")));
        assertEquals("News category name should be 首页", "首页", newsCategoryPresenter.getCategoryName());
    }
}
