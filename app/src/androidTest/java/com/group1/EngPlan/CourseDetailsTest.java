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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CourseDetailsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cD3() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.MainActivitybtn2), withText("Quick View"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ListViewIdeal),
                        childAtPosition(
                                withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                                0)))
                .atPosition(4);
        linearLayout.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.courseInfoTextView), withText("EPHY1150")));
        textView.check(matches(withText("EPHY1150")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.CourseName), withText("Physics for Engineers 1")));
        textView2.check(matches(withText("Physics for Engineers 1")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.Offering), withText("This course is offered in Fall semester")));
        textView3.check(matches(withText("This course is offered in Fall semester")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.ToListTitle), withText("Postrequisites")));
        textView4.check(matches(withText("Postrequisites")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.ToList), withText("EPHY1250")));
        textView5.check(matches(withText("EPHY1250")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.ToList2), withText("EPHY1700")));
        textView6.check(matches(withText("EPHY1700")));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.ToList), withText("EPHY1250")));
        appCompatTextView.perform(click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.courseInfoTextView), withText("EPHY1250")));
        textView7.check(matches(withText("EPHY1250")));

        pressBack();

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
