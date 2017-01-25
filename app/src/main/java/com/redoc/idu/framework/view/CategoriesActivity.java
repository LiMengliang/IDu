package com.redoc.idu.framework.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.redoc.idu.R;
import com.redoc.idu.framework.contract.ICategories;
import com.redoc.idu.framework.contract.ICategory;
import com.redoc.idu.framework.presenter.CategoriesPresenter;
import com.redoc.idu.utils.layout.LayoutUtils;

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
    private List<ICategory.ICategoryIconView> mCategoryIconViews;

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

        ViewGroup.LayoutParams layoutParams = mCategorySelectorBar.getLayoutParams();
        layoutParams.height = LayoutUtils.getNPercentOfScreenHeightInPixel(this, 0.06f);
        mCategorySelectorBar.setLayoutParams(layoutParams);

        mCategoryIconViews = new ArrayList<>();
        mCategoriesPresenter = new CategoriesPresenter();
        setPresenter(mCategoriesPresenter);
    }

    /**
     * Switch to category.
     * @param selectedCategoryView Selected view of the category.
     */
    @Override
    public void switchToCategory(ICategory.ICategoryView selectedCategoryView) {
        Fragment categoryFragment = selectedCategoryView.getOrCreateRootFragment();
        // If first fragment, use add transaction, otherwise use hide and show.
        if(mSelectedCategoryView == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contentView, categoryFragment).commit();
            mSelectedCategoryView = selectedCategoryView;
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
            ICategory.ICategoryIconView categoryIconView = categoryPresenter.getCategoryIconView();
            categoryIconView.setLayoutParameter(layoutParameters);
            mCategorySelectorBar.addView(categoryIconView.getView(), index++);
            mCategoryIconViews.add(categoryIconView);
            categoryIconView.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCategoriesPresenter.onSelectACategory(categoryPresenter);
                }
            });
        }
        switchToCategory(categoryPresenters.get(0).getAttachedCategoryView());
        mCategoryIconViews.get(0).select(true);
        mCategoryIconViews.get(1).select(false);
        mCategoryIconViews.get(2).select(false);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public ICategories.ICategoriesPresenter getPresenter() {
        return mCategoriesPresenter;
    }
}
