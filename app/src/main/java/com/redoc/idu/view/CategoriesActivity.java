package com.redoc.idu.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.redoc.idu.R;
import com.redoc.idu.contract.ICategories;
import com.redoc.idu.contract.ICategory;
import com.redoc.idu.presenter.CategoriesPresenter;
import com.redoc.idu.view.widget.CategoryIconView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Categories activity.
 */
public class CategoriesActivity extends AppCompatActivity implements ICategories.ICategoriesView {

    /**
     * Selected category view.
     */
    private ICategory.ICategoryView mSelectedCategoryView;
    /**
     * The category selector bar.
     */
    @BindView(R.id.categorySelectorBar)
    LinearLayout mCategorySelectorBar;

    /**
     * The {@link ICategories.ICategoriesPresenter} instance.
     */
    private ICategories.ICategoriesPresenter mCategoriesPresenter;

    /**
     * A list of category icon view.
     */
    private List<CategoryIconView> mCategoryIconViews;

    /**
     * Selected category.
     */
    private ICategory.ICategoryPresenter mSelectedCategory;

    /**
     * Get presenter.
     * @return Category presenter.
     */
    ICategories.ICategoriesPresenter getCategoriesPresenter() {
        return mCategoriesPresenter;
    }

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
        mCategoriesPresenter = new CategoriesPresenter(this);
    }

    /**
     * Switch to category.
     * @param categoryPresenter Presenter of the category.
     */
    @Override
    public void switchToCategory(ICategory.ICategoryPresenter categoryPresenter) {
        categoryPresenter.onSelected();
        if(mSelectedCategory != categoryPresenter) {
            mSelectedCategory = categoryPresenter;
            ICategory.ICategoryView categoryView = categoryPresenter.getAttachedCategoryView();
            Fragment categoryFragment = categoryView.getOrCreateRootFragment();
            if(mSelectedCategoryView == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.contentView, categoryFragment).commit();
                mSelectedCategoryView = categoryView;
            }
            else {
                if(categoryFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().hide(mSelectedCategoryView.getOrCreateRootFragment()).show(categoryFragment).commit();
                }
                else {
                    getSupportFragmentManager().beginTransaction().hide(mSelectedCategoryView.getOrCreateRootFragment()).add(R.id.contentView, categoryFragment).commit();
                }
            }
        }
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
    private void setCategories(List<ICategory.ICategoryPresenter> categoryPresenters) {
        LinearLayout.LayoutParams layoutParameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParameters.weight = 1;
        int index = 0;
        for(final ICategory.ICategoryPresenter categoryPresenter : categoryPresenters) {
            CategoryIconView categoryIconView = new CategoryIconView(this);
            categoryIconView.setIconResourceId(categoryPresenter.getIconResourceId());
            categoryIconView.setName(categoryPresenter.getCategoryName());
            categoryIconView.setLayoutParams(layoutParameters);
            mCategorySelectorBar.addView(categoryIconView, index++);
            mCategoryIconViews.add(categoryIconView);
            categoryIconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCategoriesPresenter.onSelectACategory(categoryPresenter);
                }
            });
        }
        switchToCategory(categoryPresenters.get(0));
    }

    /**
     * Set presenter.
     * @param presenter The presenter.
     */
    @Override
    public void setPresenter(ICategories.ICategoriesPresenter presenter) {
        presenter.onAttached(this);
        setCategories(presenter.getCategoryPresenters());
    }
}
