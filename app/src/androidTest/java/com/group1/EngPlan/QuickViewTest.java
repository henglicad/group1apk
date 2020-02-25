package com.group1.EngPlan;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QuickViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void quickViewTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.MainActivitybtn2), withText("Quick View"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.TextViewSemester), withText("Fall 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ListViewIdeal),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Fall 1")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.TextViewSemester), withText("Winter 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ListViewIdeal),
                                        8),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Winter 1")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewCourseCode), withText("DRAF1520"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ListViewIdeal),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("DRAF1520")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TextView2), withText("Engineering Graphics"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ListViewIdeal),
                                        1),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Engineering Graphics")));
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
