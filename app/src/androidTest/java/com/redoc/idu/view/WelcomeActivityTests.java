package com.redoc.idu.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.redoc.idu.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * WelcomeActivity test.
 * Created by limen on 2016/8/21.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class WelcomeActivityTests {
    /**
     * Test rule
     */
    @Rule
     public ActivityTestRule<WelcomeActivity> mWelcomeActivityRule =
             new ActivityTestRule<WelcomeActivity>(WelcomeActivity.class) {
                 @Override
                 protected void beforeActivityLaunched() {
                     super.beforeActivityLaunched();
                 }
             };

    /**
     * Test CategoriesActivity should be shown after click entry_button.
     */
    @Test
    public void clickEnterButton_CategoriesActivity_isShown() {
        onView(withId(R.id.enter_button)).perform(click());
        onView(withId(R.id.categorySelectorBar)).check(matches(isDisplayed()));
    }
}
