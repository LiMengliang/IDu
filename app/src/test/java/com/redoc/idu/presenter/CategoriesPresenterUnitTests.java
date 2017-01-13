package com.redoc.idu.presenter;

import com.redoc.idu.contract.ICategories;
import com.redoc.idu.contract.ICategory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit test of {@link CategoriesPresenter}
 * Created by limen on 2016/8/21.
 */
public class CategoriesPresenterUnitTests {
    /**
     * A mock categories view.
     */
    @Mock
    ICategories.ICategoriesView categoriesView;

    /**
     * A mock category presenter.
     */
    @Mock
    ICategory.ICategoryPresenter mCategoryPresenter;

    @Before
    public void setupCategoriesPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);
    }

    /**
     * CategoriesView.setPresenter is called when construct a CategoriesPresenter instance.
     */
    @Test
    public void CategoriesPresenter_setPresenter_called() {
        CategoriesPresenter categoriesPresenter = new CategoriesPresenter();
        categoriesView.setPresenter(categoriesPresenter);
        Mockito.verify(categoriesView).setPresenter(categoriesPresenter);
    }

    /**
     * CateogriesView.switchToCategory is called.
     */
    @Test
    public void CategoriesPresenter_onSelectACategory_switchToCategory_called() {
        CategoriesPresenter categoriesPresenter = new CategoriesPresenter();
        categoriesView.setPresenter(categoriesPresenter);
        categoriesPresenter.onSelectACategory(mCategoryPresenter);
        Mockito.verify(categoriesView).switchToCategory(mCategoryPresenter.getAttachedCategoryView());
    }
}
