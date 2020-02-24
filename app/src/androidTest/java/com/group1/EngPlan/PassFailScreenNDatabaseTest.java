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

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PassFailScreenNDatabaseTest {
    DatabaseHandler db;
    @Before
    public void setUp(){
        db = new DatabaseHandler(getApplicationContext());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void passFailScreenNDatabaseTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.MainActivitybtn1), withText("Make a Plan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.yearRadioBtn2), withText("Second Year"),
                        childAtPosition(
                                allOf(withId(R.id.yearInfoRadioGrp),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.yearNextBtn), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.courseNoRadioBtn3), withText("5 Courses"),
                        childAtPosition(
                                allOf(withId(R.id.courseNoRadioGroup1),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.courseNoNextBtn1), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatCheckBox6 = onView(
                allOf(withId(R.id.courseCheckBox),
                        childAtPosition(
                                withParent(withId(R.id.passFailListView)),
                                2),
                        isDisplayed()));
        appCompatCheckBox6.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.passFailNextBtn), withText("Next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());

        int[] reference = {1, 1, 1, 1, 0, 1, 0, 1};
        int check;
        Cursor data = db.getRecords();
        data.moveToFirst();
        for(int i = 0; i < 8; i++){
            check = data.getInt(1);
            assertEquals(check, reference[i]);
            data.moveToNext();
        }
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
