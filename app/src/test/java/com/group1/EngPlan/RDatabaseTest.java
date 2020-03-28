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
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RDatabaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void rDatabaseTest() {
        ViewInteraction appCompatButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.fileButton), ViewMatchers.withText("Admin")));
        appCompatButton.perform(ViewActions.click());

        ViewInteraction appCompatEditText = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.credential),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.custom),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        appCompatEditText.perform(ViewActions.replaceText("abcd"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.credential), ViewMatchers.withText("abcd")));
        appCompatEditText2.perform(ViewActions.click());

        ViewInteraction appCompatButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Okay")));
        appCompatButton2.perform(ViewActions.click());

        ViewInteraction appCompatButton3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.fileButton), ViewMatchers.withText("Admin")));
        appCompatButton3.perform(ViewActions.click());

        ViewInteraction appCompatButton4 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button2), ViewMatchers.withText("Cancel")));
        appCompatButton4.perform(ViewActions.click());

        ViewInteraction appCompatButton5 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.fileButton), ViewMatchers.withText("Admin")));
        appCompatButton5.perform(ViewActions.click());

        ViewInteraction appCompatEditText3 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.credential),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.custom),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        appCompatEditText3.perform(ViewActions.replaceText("1234"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatButton6 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Okay")));
        appCompatButton6.perform(ViewActions.scrollTo(), ViewActions.click());

        ViewInteraction appCompatButton7 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.button_download), ViewMatchers.withText("Download Database")));
        appCompatButton7.perform(ViewActions.click());

        ViewInteraction appCompatButton8 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.button_upload), ViewMatchers.withText("Upload Database")));
        appCompatButton8.perform(ViewActions.click());

        ViewInteraction appCompatButton9 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Yes, upload")));
        appCompatButton9.perform(ViewActions.click());

        ViewInteraction appCompatButton10 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Okay")));
        appCompatButton10.perform(ViewActions.click());

        ViewInteraction appCompatButton11 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.button_logout), ViewMatchers.withText("Log Out")));
        appCompatButton11.perform(ViewActions.click());
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
