package com.redoc.idu.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.redoc.idu.R;
import com.redoc.idu.contract.ICategoriesContract;
import com.redoc.idu.contract.ICategoryContract;
import com.redoc.idu.presenter.CategoriesPresenter;
import com.redoc.idu.view.widget.CategoryIconView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Categories activity.
 */
public class CategoriesActivity extends AppCompatActivity implements ICategoriesContract.ICategoriesView {

    /**
     * The category selector bar.
     */
    @BindView(R.id.categorySelectorBar)
    LinearLayout mCategorySelectorBar;

    /**
     * The {@link com.redoc.idu.contract.ICategoriesContract.ICategoriesPresenter} instance.
     */
    private ICategoriesContract.ICategoriesPresenter presenter;

    /**
     * A list of category icon view.
     */
    private List<CategoryIconView> mCategoryIconViews;

    /**
     * On create
     * @param savedInstanceState The bundle instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.bind(this);

        mCategoryIconViews = new ArrayList<>();
        presenter = new CategoriesPresenter(this);
    }

    /**
     * Switch to category.
     */
    @Override
    public void switchCategory() {

    }

    /**
     * Add category.
     */
    @Override
    public void addCategory() {

    }

    /**
     * Set categories.
     * @param categoryPresenters A list of category presenter.
     */
    @Override
    public void setCategories(List<ICategoryContract.ICategoryPresenter> categoryPresenters) {
        LinearLayout.LayoutParams layoutParameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParameters.weight = 1;
        int index = 0;
        for(ICategoryContract.ICategoryPresenter categoryPresenter : categoryPresenters) {
            CategoryIconView categoryIconView = new CategoryIconView(this);
            categoryIconView.setIconResourceId(R.drawable.category_main);
            categoryIconView.setName(categoryPresenter.getCategoryName());
            categoryIconView.setLayoutParams(layoutParameters);
            mCategorySelectorBar.addView(categoryIconView, index++);
            mCategoryIconViews.add(categoryIconView);
        }
    }

    /**
     * Set presenter.
     * @param presenter The presenter.
     */
    @Override
    public void setPresenter(ICategoriesContract.ICategoriesPresenter presenter) {
        presenter.onAttached();
    }
}
