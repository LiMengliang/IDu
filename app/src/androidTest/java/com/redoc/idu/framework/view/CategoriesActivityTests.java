package com.redoc.idu.framework.view;

import android.support.test.espresso.assertion.PositionAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.redoc.idu.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;

/**
 * CategoriesActivity unit test.
 * Created by limen on 2016/8/24.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CategoriesActivityTests {
    @Rule
    public ActivityTestRule<CategoriesActivity> mCategoriesActivityRule =
            new ActivityTestRule<CategoriesActivity>(CategoriesActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                }
            };

    /**
     * Test layout correctly.
     */
    @Test
    public void categoriesActivity_selectorbar_layoutCorrectly() {
        Matcher<View> selectorBarMatcher = withId(R.id.categorySelectorBar);
        Matcher<View> seperatorMatcher = withId(R.id.separator);
        onView(selectorBarMatcher).check(matches(isDisplayed()));
        onView(selectorBarMatcher).check(PositionAssertions.isBottomAlignedWith(isRoot()));
        onView(selectorBarMatcher).check(PositionAssertions.isBelow(seperatorMatcher));
        onView(withParent(withId(R.id.contentView))).check(matches(isDisplayed()));
    }

    @Test
    public void categoriesActivity_newCategory_selected_multiChannelsFragmentShown() {
        // TODO: finish test
        // Matcher<View> newCategoryButtonMatcher = withText("新闻");
        // onView(newCategoryButtonMatcher).perform(click());
        // onView(withId(R.id.contentView)).check(hasDescendant(withClassName("MultiChannelsCategoryFragment")));
    }
}
