package com.redoc.idu.presenter;

import com.redoc.idu.contract.ICategoriesContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit test of {@link CategoriesPresenter}
 * Created by limen on 2016/8/21.
 */
public class CategoriesPresenterUnitTest {
    /**
     * A mock categories view.
     */
    @Mock
    ICategoriesContract.ICategoriesView categoriesView;

    /**
     * Set up categories presenter.
     */
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
        // ICategoriesContract.ICategoriesView categoriesView = Mockito.mock(ICategoriesContract.ICategoriesView.class);
        CategoriesPresenter categoriesPresenter = new CategoriesPresenter(categoriesView);
        Mockito.verify(categoriesView).setPresenter(categoriesPresenter);
    }

    /**
     * CategoriesView.setCategories is called when a CategoriesPresenter is attached to view.
     */
    @Test
    public void CategoriesPresenter_onAttached_setCategories_called() {
        CategoriesPresenter categoriesPresenter = new CategoriesPresenter(categoriesView);
        categoriesPresenter.onAttached();
        Mockito.verify(categoriesView).setCategories(categoriesPresenter.getmCategoryPresenters());
    }
}
