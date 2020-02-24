package com.group1.EngPlan;

import android.content.Context;
import android.database.Cursor;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;

public class DatabaseHandlerTest {
    private DatabaseHandler dbh;

    @Before
    public void setUp(){
        //Context context = ApplicationProvider.getApplicationContext();
        Context mockContext = RuntimeEnvironment.systemContext;
        MockContext mContext = new MockContext();
        dbh = new DatabaseHandler(mContext);
        //dbh.createDatabase();
    }

    @Test // test createDatabase, populateXTable, fillQuickView
    public void fillQuickViewTest(){
        Cursor data = dbh.fillQuickView("F1");

        data.moveToFirst();
        String courseId = data.getString(0);
        assertEquals(courseId, "DRAF1520");
    }

    @Test // test getCourseData by id
    public void getCourseDataTest(){
        Cursor data = dbh.getCourseData("PHYS2250");

        data.moveToFirst();
        String prereq = data.getString(3);
        assertEquals(prereq, "EPHY1250");
    }

    @Test // test getRecords, setRecords
    public void setGetRecordsTest(){
        dbh.setRecords("COMP3410",0);
        dbh.setRecords("COMP3610",1);

        Cursor data = dbh.getRecords();//13,12

        data.moveToPosition(10);
        int passfail = data.getInt(1);
        assertEquals(passfail, 0); // to test no db changes

        data.moveToPosition(11);
        passfail = data.getInt(1);
        assertEquals(passfail, 0); // to test db written to 0

        data.moveToPosition(12);
        passfail = data.getInt(1);
        assertEquals(passfail, 1); // to test db written to 1
    }

    @Test // test setSavedSched, getSavedSched
    public void setGetSavedSched(){
        Cursor data = dbh.getSavedSched("W2");

        data.moveToPosition(0);
        String course = data.getString(0);
        assertEquals(course, "CENG2030");

        dbh.setSavedSched("CENG2030", "W3");

        data = dbh.getSavedSched("W3");
        data.moveToPosition(0);
        course = data.getString(0);
        assertEquals(course, "CENG2030");

        data = dbh.getSavedSched("W2");
        data.moveToPosition(0);
        course = data.getString(0);
        assertEquals(course, "COMP1520");
    }
}