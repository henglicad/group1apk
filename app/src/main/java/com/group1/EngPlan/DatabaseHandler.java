package com.group1.EngPlan;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EngPlan.db";

    // COURSE_LIST_TABLE STRING DECLARATIONS
    public static final String COURSE_LIST_TABLE = "COURSE_LIST";
    public static final String COURSE_ID_COL = "COURSE_ID";
    public static final String COURSE_NAME_COL = "COURSE_NAME";
    public static final String COURSE_OFFERED_COL = "OFFERED";
    public static final String COURSE_PREREQ1_COL = "PREREQ1";
    public static final String COURSE_PREREQ2_COL = "PREREQ2";
    public static final String COURSE_TO1_COL = "TO1";
    public static final String COURSE_TO2_COL = "TO2";
    public static final String COURSE_TO3_COL = "TO3";
    public static final String COURSE_TO4_COL = "TO4";

    //IDEAL_SCHED STRING DECLARATIONS
    public static final String IDEAL_SCHED_TABLE = "IDEAL_SCHED";
    public static final String SEMESTER_COL = "SEMESTER";

    //RECORD_TABLE STRING DECLARATIONS
    public static final String RECORD_TABLE = "RECORD";
    public static final String STATUS_COL = "STATUS";

    //SAVED_SCHED_TABLE STRING DECLARATIONS
    public static final String SAVED_SCHED_TABLE = "SAVED_SCHED";

    //BACKUP_SCHED_TABLE STRING DECLARATIONS
    public static final String BACKUP_SCHED_TABLE = "BACKUP_SCHED";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    // CREATE ALL TABLES
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + COURSE_LIST_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + COURSE_NAME_COL + " VARCHAR(75), " + COURSE_OFFERED_COL + " CHAR(1), "
                + COURSE_PREREQ1_COL + " CHAR(8), " + COURSE_PREREQ2_COL + " CHAR(8), " + COURSE_TO1_COL + " CHAR(8), "
                + COURSE_TO2_COL + " CHAR(8), " + COURSE_TO3_COL + " CHAR(8), " + COURSE_TO4_COL + " CHAR(8));");
        populateCourseTable(db);

        db.execSQL("CREATE TABLE " + IDEAL_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2));");
        populateIdealSchedTable(db);

        db.execSQL("CREATE TABLE " + RECORD_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + STATUS_COL + " CHAR(1) DEFAULT 'N');");
        populateRecordTable(db);

        db.execSQL("CREATE TABLE " + SAVED_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2));");
        populateSavedSchedTable(db);

        db.execSQL("CREATE TABLE " + BACKUP_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2));");
        populateBackupSchedTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_LIST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + IDEAL_SCHED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SAVED_SCHED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BACKUP_SCHED_TABLE);
        onCreate(db);
    }

    // POPULATE COURSE_LIST_TABLE WITH MASTER VALUES
    public void populateCourseTable(SQLiteDatabase db){
        // enter info from ahmed
    }

    // ADD ALL LINES TO COURSE_LIST_TABLE
    public boolean insertIntoCourseList(String id, String name, String sem, String pr1, String pr2, String t1, String t2, String t3, String t4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURSE_ID_COL, id);
        contentValues.put(COURSE_NAME_COL, name);
        contentValues.put(COURSE_OFFERED_COL, sem);
        contentValues.put(COURSE_PREREQ1_COL, pr1);
        contentValues.put(COURSE_PREREQ2_COL, pr2);
        contentValues.put(COURSE_TO1_COL, t1);
        contentValues.put(COURSE_TO2_COL, t2);
        contentValues.put(COURSE_TO3_COL, t3);
        contentValues.put(COURSE_TO4_COL, t4);

        long result = db.insert(COURSE_LIST_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // POPULATE IDEAL_SCHED_TABLE WITH MASTER PLAN
    public void populateIdealSchedTable(SQLiteDatabase db){
        // enter info from ahmed
    }

    // ADD ALL LINES TO IDEAL_SCHED_TABLE
    public boolean insertIntoIdealSched(String id, String sem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURSE_ID_COL, id);
        contentValues.put(SEMESTER_COL, sem);

        long result = db.insert(IDEAL_SCHED_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // POPULATE RECORD_TABLE WITH MASTER PLAN
    public void populateRecordTable(SQLiteDatabase db){
        // enter info from ahmed
    }

    // ADD ALL LINES TO RECORD_TABLE
    public boolean insertIntoRecprd(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURSE_ID_COL, id);

        long result = db.insert(RECORD_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // POPULATE SAVED_SCHED_TABLE WITH MASTER PLAN
    public void populateSavedSchedTable(SQLiteDatabase db){
        // enter info from ahmed
    }

    // ADD ALL LINES TO SAVED_SCHED_TABLE
    public boolean insertIntoSavedSched(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURSE_ID_COL, id);

        long result = db.insert(SAVED_SCHED_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    // POPULATE BACKUP_SCHED_TABLE
    public void populateBackupSchedTable(SQLiteDatabase db){
        // enter info from ahmed
    }

    // ADD ALL LINES TO BACKUP_SCHED_TABLE
    public boolean insertIntoBackupSched(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COURSE_ID_COL, id);

        long result = db.insert(BACKUP_SCHED_TABLE, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
