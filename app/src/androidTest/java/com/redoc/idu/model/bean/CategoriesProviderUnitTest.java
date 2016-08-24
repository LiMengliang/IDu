package com.redoc.idu.model.bean;

import com.redoc.idu.model.CategoriesProvider;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link CategoriesProvider}
 * Created by limen on 2016/8/21.
 */
public class CategoriesProviderUnitTest {
    /**
     * Test getCategories can return expected result.
     */
    @Test
    public void getCategories_isCorrect() {
        CategoriesProvider categoriesProvider = new CategoriesProvider();
        List<Category> categories = categoriesProvider.getCategories();
        assertEquals("Should have 3 categories", 3, categories.size());
    }
}

