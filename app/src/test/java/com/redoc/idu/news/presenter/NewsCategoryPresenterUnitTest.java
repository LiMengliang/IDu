package com.redoc.idu.news.presenter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link NewsCategoryPresenter}
 * Created by limen on 2016/8/21.
 */
public class NewsCategoryPresenterUnitTest {
    /**
     * Test getCategoryName can return expected result.
     */
    @Test
    public void getCategoryName_isCorrect() {
        NewsCategoryPresenter newsCategoryPresenter = new NewsCategoryPresenter();
        assertEquals("News category name should be 新闻", "新闻", newsCategoryPresenter.getCategoryName());
    }
}
