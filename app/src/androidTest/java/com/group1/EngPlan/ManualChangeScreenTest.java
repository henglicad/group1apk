package com.group1.EngPlan;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ManualChangeScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void manualChangeScreenTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.MainActivitybtn3), withText("VIEW MY PLAN"),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_root),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withClassName(is("android.widget.LinearLayout")),
                                                                                1),
                                                                        0)),
                                                        1)),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.central_screen_toolbar),
                                        childAtPosition(
                                                childAtPosition(
                                                        allOf(withId(R.id.central_screen_drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        allOf(withId(R.id.action_bar_root),
                                                                                                childAtPosition(
                                                                                                        childAtPosition(
                                                                                                                withClassName(is("android.widget.LinearLayout")),
                                                                                                                1),
                                                                                                        0)),
                                                                                        1)),
                                                                        0)),
                                                        1),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        allOf(withId(R.id.nav_view),
                                                childAtPosition(
                                                        allOf(withId(R.id.central_screen_drawer_layout),
                                                                childAtPosition(
                                                                        allOf(withId(android.R.id.content),
                                                                                childAtPosition(
                                                                                        allOf(withId(R.id.action_bar_root),
                                                                                                childAtPosition(
                                                                                                        childAtPosition(
                                                                                                                withClassName(is("android.widget.LinearLayout")),
                                                                                                                1),
                                                                                                        0)),
                                                                                        1)),
                                                                        0)),
                                                        2)),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        DataInteraction constraintLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.custom_schedule_list_view),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(android.R.id.content),
                                                childAtPosition(
                                                        allOf(withId(R.id.action_bar_root),
                                                                childAtPosition(
                                                                        childAtPosition(
                                                                                withClassName(is("android.widget.LinearLayout")),
                                                                                1),
                                                                        0)),
                                                        1)),
                                        0),
                                0)))
                .atPosition(0);
        constraintLayout.perform(click());

        pressBack();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
