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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NumCoursesTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void numCoursesTest() {
        ViewInteraction appCompatButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.MainActivitybtn1), ViewMatchers.withText("Make a Plan"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton.perform(ViewActions.click());

        ViewInteraction appCompatRadioButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.yearRadioBtn), ViewMatchers.withText("First Year"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.yearInfoRadioGrp),
                                        childAtPosition(
                                                ViewMatchers.withClassName(Matchers.is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                0),
                        ViewMatchers.isDisplayed()));
        appCompatRadioButton.perform(ViewActions.click());

        DataInteraction appCompatCheckedTextView = Espresso.onData(Matchers.anything())
                .inAdapterView(Matchers.allOf(ViewMatchers.withId(R.id.select_dialog_listview),
                        childAtPosition(
                                ViewMatchers.withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(ViewActions.click());

        ViewInteraction appCompatButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.yearNextBtn), ViewMatchers.withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton2.perform(ViewActions.click());

        ViewInteraction appCompatRadioButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.courseNoRadioBtn1), ViewMatchers.withText("3 Courses"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.courseNoRadioGroup1),
                                        childAtPosition(
                                                ViewMatchers.withClassName(Matchers.is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                0),
                        ViewMatchers.isDisplayed()));
        appCompatRadioButton2.perform(ViewActions.click());

        ViewInteraction appCompatButton3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.courseNoNextBtn1), ViewMatchers.withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton3.perform(ViewActions.click());

        Espresso.pressBack();

        ViewInteraction appCompatButton4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.courseNoNextBtn1), ViewMatchers.withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton4.perform(ViewActions.click());

        Espresso.pressBack();

        ViewInteraction appCompatRadioButton4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.courseNoRadioBtn5), ViewMatchers.withText("7 Courses"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.courseNoRadioGroup1),
                                        childAtPosition(
                                                ViewMatchers.withClassName(Matchers.is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4),
                        ViewMatchers.isDisplayed()));
        appCompatRadioButton4.perform(ViewActions.click());

        ViewInteraction appCompatButton5 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.courseNoNextBtn1), ViewMatchers.withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatButton5.perform(ViewActions.click());

        Espresso.pressBack();

        Espresso.pressBack();
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
