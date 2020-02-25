package com.group1.EngPlan;


import android.database.Cursor;
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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
    DatabaseHandler dbh;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp(){
        dbh = new DatabaseHandler(getApplicationContext());
    }

    @Test
    public void courseDetailsTest() {
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
                allOf(withId(R.id.courseInfoTextView), withText("EPHY1150"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("EPHY1150")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.CourseName), withText("Physics for Engineers 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Physics for Engineers 1")));

        // check database is made with correct values
        Cursor data = dbh.getCourseData("EPHY1150");
        data.moveToFirst();

        String temp = data.getString(1);
        assertEquals("Physics for Engineers 1", temp);

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.Offering), withText("This course is offered in Fall semester"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("This course is offered in Fall semester")));

        // check database is made with correct values
        temp = data.getString(2);
        assertEquals("F", temp);

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.ToList), withText("EPHY1250\nEPHY1700\nEPHY1990\n"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        textView4.check(matches(withText("EPHY1250\nEPHY1700\nEPHY1990\n")));

        // check database is made with correct values (no prereqs)
        temp = data.getString(3);
        assertEquals(null, temp);
        temp = data.getString(4);
        assertEquals(null, temp);

        // check database is made with correct values (to list)
        temp = data.getString(5);
        temp = temp + " " + data.getString(6) + " " + data.getString(7) + " ";
        assertEquals("EPHY1250 EPHY1700 EPHY1990 ", temp);

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
