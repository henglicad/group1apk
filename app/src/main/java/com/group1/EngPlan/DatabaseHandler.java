package com.group1.EngPlan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String LOG_TAG = DatabaseHandler.class.getSimpleName();
    SQLiteDatabase db;

    private static final String DATABASE_NAME = "EngPlan.db";
    private boolean firstTime = true;

    // COURSE_LIST_TABLE STRING DECLARATIONS
    private static final String COURSE_LIST_TABLE = "COURSE_LIST";
    private static final String COURSE_ID_COL = "COURSE_ID";
    private static final String COURSE_NAME_COL = "COURSE_NAME";
    private static final String COURSE_OFFERED_COL = "OFFERED";
    private static final String COURSE_PREREQ1_COL = "PREREQ1";
    private static final String COURSE_PREREQ2_COL = "PREREQ2";
    private static final String COURSE_TO1_COL = "TO1";
    private static final String COURSE_TO2_COL = "TO2";
    private static final String COURSE_TO3_COL = "TO3";
    private static final String COURSE_TO4_COL = "TO4";
    //IDEAL_SCHED STRING DECLARATIONS
    private static final String IDEAL_SCHED_TABLE = "IDEAL_SCHED";
    private static final String SEMESTER_COL = "SEMESTER";
    //RECORD_TABLE STRING DECLARATIONS
    private static final String RECORD_TABLE = "RECORD";
    private static final String STATUS_COL = "STATUS";
    //SAVED_SCHED_TABLE STRING DECLARATIONS
    private static final String SAVED_SCHED_TABLE = "SAVED_SCHED";
    //BACKUP_SCHED_TABLE STRING DECLARATIONS
    private static final String BACKUP_SCHED_TABLE = "BACKUP_SCHED";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
        db = getWritableDatabase();
    }

    @Override
      public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Start of onCreate() for the database");

        if(firstTime){
            createDatabase(db);
            firstTime = false;
        }
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

        /* BEGIN TABLE CREATION */
    // CREATE TABLES AND CALL FOR POPULATION
    public void createDatabase(SQLiteDatabase db){
        Log.d(LOG_TAG, "Building tables for database");

        db.execSQL("CREATE TABLE " + COURSE_LIST_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8) UNIQUE NOT null, " + COURSE_NAME_COL + " VARCHAR(50) NOT null, " + COURSE_OFFERED_COL + " CHAR(1), "
                + COURSE_PREREQ1_COL + " CHAR(8), " + COURSE_PREREQ2_COL + " CHAR(8), " + COURSE_TO1_COL + " CHAR(8), "
                + COURSE_TO2_COL + " CHAR(8), " + COURSE_TO3_COL + " CHAR(8), " + COURSE_TO4_COL + " CHAR(8));");
        populateCourseTable(db);

        db.execSQL("CREATE TABLE " + IDEAL_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
        populateIdealSchedTable(db);

        db.execSQL("CREATE TABLE " + RECORD_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + STATUS_COL + " int DEFAULT 0,"
                + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
        populateRecordTable(db);

        db.execSQL("CREATE TABLE " + SAVED_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
        populateSavedSchedTable(db);

        db.execSQL("CREATE TABLE " + BACKUP_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
        populateBackupSchedTable(db);
    }

    // POPULATE COURSE_LIST_TABLE WITH IDEAL PLAN
    private void populateCourseTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP1000', 'Career Management', 'B', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP2080', 'Work Term 1', 'F', 'COOP1000', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP2180', 'Work Term 2', 'W', 'COOP1000', 'COOP2080', null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG2010', 'Computer Architecture & Assembly Language', 'F', 'MATH1230', 'SENG1210', 'SENG3130', 'CENG3010', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG2030', 'Introduction to Digital Signal Processing', 'W', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG3010', 'Computer System Design', 'F', 'CENG2010', 'EPHY2990', 'CENG3020', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG3020', 'Real Time Systems Design', 'W', 'CENG3010', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG3310', 'Digital Communications Systems', 'F', null, null, 'CENG4320', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CENG4320', 'Communications Networks', 'W', 'CENG3310', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CHEM1520', 'Principles of Chemistry', 'W', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('CMNS1290', 'Introduction to Professional Writing', 'B', 'ENGL1100', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COMP3410', 'Operating Systems', 'B', 'SENG3110', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COMP3610', 'Database Management Systems Design', 'B', 'SENG3110', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('DRAF1520', 'Engineering Graphics', 'F', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EENG3010', 'Introduction to Control Systems', 'W', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGL1100', 'Introduction to University Writing', 'B', null, null, 'CMNS1290', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGR1100', 'Introduction to Engineering & Design', 'B', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGR2200', 'Engineering in Society, Health and Safety', 'F', null, null, 'ENGR2300', 'ENGR2400', 'ENGR3300', null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGR2300', 'Engineering Management', 'W', 'ENGR2200', null, 'SENG3130', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGR2400', 'Engineering Economics', 'W', 'ENGR2200', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('ENGR3300', 'Engineering Professional Ethics', 'W', 'ENGR2200', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY1150', 'Physics for Engineers 1', 'F', null, null, 'EPHY1250', 'EPHY1700', 'EPHY1990', null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY1250', 'Physics for Engineers 2', 'W', 'EPHY1150', null, 'EPHY2200', 'PHYS2250', 'PHYS2150', null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY1700', 'Engineering Mechanics 1', 'W', 'EPHY1150', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY1990', 'Introduction to Engineering Measurements', 'W', 'EPHY1150', 'MATH1130', null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY2200', 'Electrical Properties of Materials', 'F', 'EPHY1250', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY2300', 'Digital Electronics', 'W', 'PHYS2150', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('EPHY2990', 'ECE Design', 'W', 'PHYS2150', null, 'CENG3010', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('MATH1130', 'Enriched Calculus 1', 'F', null, null, 'EPHY1990', 'MATH1230', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('MATH1230', 'Enriched Calculus 2', 'W', 'MATH1130', null, 'CENG2010', 'PHYS2150', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('MATH1300', 'Linear Algebra for Engineers', 'F', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('MATH1700', 'Discrete Mathematics', 'B', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('PHYS2150', 'Circuit Analysis', 'F', 'EPHY1250', 'MATH1230', 'EPHY2300', 'EPHY2990', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('PHYS2250', 'Intermediate Electromagnetism', 'W', 'EPHY1250', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG1110', 'Programming for Engineers 1', 'F', null, null, 'SENG1210', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG1210', 'Programming for Engineers 2', 'W', 'SENG1110', null, 'SENG3110', 'CENG2010', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG3110', 'Algorithms & Data Structures', 'F', 'SENG1210', 'STAT2230', 'COMP3410', 'COMP3610', 'SENG3120', 'SENG3210');");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG3120', 'Software Engineering Design: Process & Principles', 'W', 'SENG3110', null, 'SENG4100', 'SENG4130', null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG3130', 'Software Requirements and Specifications', 'F', 'CENG2010', 'ENGR2300', 'SENG4230', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG3210', 'Applied Software Engineering', 'W', 'SENG3110', null, 'SENG4110', 'SENG4120', 'SENG4140', 'SENG4220');");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4100', 'Software Engineering Design Project', 'F', 'SENG3210', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4110', 'Software Testing & Verifications', 'F', 'SENG3210', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4120', 'Software Model Engineering & Formal Methods', 'F', 'SENG3210', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4130', 'Software Design Patterns', 'F', 'SENG3120', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4140', 'Software Quality Engineering', 'W', 'SENG3210', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4220', 'Software Security Engineering', 'W', 'SENG3210', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG4230', 'Software Estimation', 'W', 'SENG3130', null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('STAT2230', 'Probability and Statistics for Engineers', 'F', null, null, 'SENG3110', null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('NSCIXXXX', 'Natural Science Elective', 'B', null, null, null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG41XX', 'Upper Level Technical Elective-1', 'F', 'SENG3210', 'SENG3120', null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG42XX', 'Upper Level Technical Elective-2', 'F', 'SENG3210', 'SENG3120', null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG43XX', 'Upper Level Technical Elective-3', 'W', 'SENG3210', 'SENG3120', null, null, null, null);");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('SENG44XX', 'Upper Level Technical Elective-4', 'W', 'SENG3210', 'SENG3120', null, null, null, null);");
    }

    // POPULATE IDEAL_SCHED_TABLE WITH IDEAL PLAN
    private void populateIdealSchedTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('COOP1000', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('COOP2080', 'F4');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('COOP2180', 'W4');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG2010', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG2030', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG3010', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG3020', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG3310', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CENG4320', 'W5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CHEM1520', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('CMNS1290', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('COMP3410', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('COMP3610', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('DRAF1520', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EENG3010', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGL1100', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGR1100', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGR2200', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGR2300', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGR2400', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('ENGR3300', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY1150', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY1250', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY1700', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY1990', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY2200', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY2300', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('EPHY2990', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('MATH1130', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('MATH1230', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('MATH1300', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('MATH1700', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('PHYS2150', 'F2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('PHYS2250', 'W2');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG1110', 'F1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG1210', 'W1');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG3110', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG3120', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG3130', 'F3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG3210', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4100', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4110', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4120', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4130', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4140', 'W5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4220', 'W5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG4230', 'W5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('STAT2230', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('NSCIXXXX', 'W3');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG41XX', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG42XX', 'F5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG43XX', 'W5');");
        db.execSQL("INSERT INTO " + IDEAL_SCHED_TABLE + " VALUES ('SENG44XX', 'W5');");
    }

    // POPULATE RECORD_TABLE WITH MASTER PLAN
    private void populateRecordTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('COOP1000', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('COOP2080', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('COOP2180', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG2010', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG2030', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG3010', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG3020', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG3310', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CENG4320', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CHEM1520', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('CMNS1290', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('COMP3410', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('COMP3610', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('DRAF1520', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EENG3010', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGL1100', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGR1100', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGR2200', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGR2300', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGR2400', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('ENGR3300', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY1150', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY1250', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY1700', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY1990', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY2200', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY2300', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('EPHY2990', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('MATH1130', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('MATH1230', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('MATH1300', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('MATH1700', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('PHYS2150', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('PHYS2250', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG1110', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG1210', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG3110', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG3120', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG3130', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG3210', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4100', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4110', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4120', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4130', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4140', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4220', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG4230', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('STAT2230', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('NSCIXXXX', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG41XX', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG42XX', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG43XX', 0);");
        db.execSQL("INSERT INTO " + RECORD_TABLE + " VALUES ('SENG44XX', 0);");
    }

    // POPULATE SAVED_SCHED_TABLE WITH IDEAL PLAN FRO DEFAULT
    private void populateSavedSchedTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('COOP1000', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('COOP2080', 'F4');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('COOP2180', 'W4');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG2010', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG2030', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG3010', 'F3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG3020', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG3310', 'F3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CENG4320', 'W5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CHEM1520', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('CMNS1290', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('COMP3410', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('COMP3610', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('DRAF1520', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EENG3010', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGL1100', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGR1100', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGR2200', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGR2300', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGR2400', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('ENGR3300', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY1150', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY1250', 'W1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY1700', 'W1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY1990', 'W1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY2200', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY2300', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('EPHY2990', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('MATH1130', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('MATH1230', 'W1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('MATH1300', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('MATH1700', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('PHYS2150', 'F2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('PHYS2250', 'W2');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG1110', 'F1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG1210', 'W1');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG3110', 'F3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG3120', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG3130', 'F3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG3210', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4100', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4110', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4120', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4130', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4140', 'W5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4220', 'W5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG4230', 'W5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('STAT2230', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('NSCIXXXX', 'W3');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG41XX', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG42XX', 'F5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG43XX', 'W5');");
        db.execSQL("INSERT INTO " + SAVED_SCHED_TABLE + " VALUES ('SENG44XX', 'W5');");
    }

    // POPULATE BACKUP_SCHED_TABLE
    private void populateBackupSchedTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('COOP1000', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('COOP2080', 'F4');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('COOP2180', 'W4');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG2010', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG2030', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG3010', 'F3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG3020', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG3310', 'F3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CENG4320', 'W5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CHEM1520', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('CMNS1290', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('COMP3410', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('COMP3610', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('DRAF1520', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EENG3010', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGL1100', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGR1100', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGR2200', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGR2300', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGR2400', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('ENGR3300', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY1150', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY1250', 'W1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY1700', 'W1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY1990', 'W1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY2200', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY2300', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('EPHY2990', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('MATH1130', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('MATH1230', 'W1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('MATH1300', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('MATH1700', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('PHYS2150', 'F2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('PHYS2250', 'W2');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG1110', 'F1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG1210', 'W1');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG3110', 'F3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG3120', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG3130', 'F3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG3210', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4100', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4110', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4120', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4130', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4140', 'W5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4220', 'W5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG4230', 'W5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('STAT2230', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('NSCIXXXX', 'W3');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG41XX', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG42XX', 'F5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG43XX', 'W5');");
        db.execSQL("INSERT INTO " + BACKUP_SCHED_TABLE + " VALUES ('SENG44XX', 'W5');");
    }
        /* END TABLE CREATION */

        /* BEGIN SEND DATA */
    // FILL QUICK VIEW- CALLED WITH SEMESTER OF LIST VIEW TO POPULATE
    public Cursor fillQuickView(String semester){
        Log.d(LOG_TAG, "Filling Quick View");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT I." + COURSE_ID_COL + ", C." + COURSE_NAME_COL
                + " FROM " + IDEAL_SCHED_TABLE + " I JOIN " + COURSE_LIST_TABLE
                + " C ON C." + COURSE_ID_COL + " = I." + COURSE_ID_COL
                + " WHERE I." + SEMESTER_COL + " = '" + semester + "';";
        return db.rawQuery(query, null);
    }

    // SEND DATA FOR THE DETAILED COURSE SCREEN
    public Cursor getCourseData(String id){
        Log.d(LOG_TAG, "Getting Course Data");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + COURSE_LIST_TABLE + " WHERE " + COURSE_ID_COL + " = '" + id + "';";

        return db.rawQuery(query, null);
    }

    // PULL ALL DATA NEEDED FOR SCHEDULING FROM DATABASE
    public Cursor getSchedData(){
        Log.d(LOG_TAG, "Getting Course Data for Schedule");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT L." + COURSE_ID_COL + ", L." + COURSE_OFFERED_COL + ", L." + COURSE_PREREQ1_COL + ", L." + COURSE_PREREQ2_COL
                + ", L." + COURSE_TO1_COL + ", L." + COURSE_TO2_COL + ", L." + COURSE_TO3_COL + ", L." + COURSE_TO4_COL + ", R." + STATUS_COL + ", I." + SEMESTER_COL
                + " FROM " + COURSE_LIST_TABLE + " L JOIN " + RECORD_TABLE + " R ON L." + COURSE_ID_COL + " = R." + COURSE_ID_COL
                + " JOIN " + IDEAL_SCHED_TABLE + " I ON L." + COURSE_ID_COL + " = I." + COURSE_ID_COL + ";";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // DISPLAY SAVED SCHED- CALLED WITH SEMESTER OF LIST VIEW TO POPULATE
    public Cursor getSavedSched(String semester){
        Log.d(LOG_TAG, "Filling Saved Schedule");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT S." + COURSE_ID_COL + ", C." + COURSE_NAME_COL
                + " FROM " + SAVED_SCHED_TABLE + " S JOIN " + COURSE_LIST_TABLE + " C "
                + "ON C." + COURSE_ID_COL + " = S." + COURSE_ID_COL
                + " WHERE S." + SEMESTER_COL + " = '" + semester + "';";
        return db.rawQuery(query, null);
    }

    public Cursor sendDBData(){
        Log.d(LOG_TAG, "Filling cursor to write to file");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT C." + COURSE_ID_COL + ", C." +COURSE_NAME_COL + ", C." + COURSE_OFFERED_COL + ","
                + " I." + SEMESTER_COL + ", C." + COURSE_PREREQ1_COL + ", C." + COURSE_PREREQ2_COL + ","
                + " C." + COURSE_TO1_COL + ", C." + COURSE_TO2_COL + ", C." + COURSE_TO3_COL + ", C." + COURSE_TO4_COL
                + " FROM " + COURSE_LIST_TABLE + " C JOIN " + IDEAL_SCHED_TABLE + " I"
                + " ON C." + COURSE_ID_COL + " = I." + COURSE_ID_COL +";";
        return db.rawQuery(query, null);
    }
        /* END SEND DATA */

        /* BEGIN FILL DATA */
    // DENOTE PASS/FAIL
    public void setRecords(String id, int passed){
        Log.d(LOG_TAG, "Updating grades");
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("UPDATE " + RECORD_TABLE + " SET " + STATUS_COL + " = '" + passed + "' WHERE " + COURSE_ID_COL + " = '" + id + "';");
    }

    // UPDATE SAVED SCHEDULE WITH DATA FROM MAIN ALGORITHM
    public void setSavedSched(String id, String sem){
        Log.d(LOG_TAG, "Updating saved schedule");
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("UPDATE " + SAVED_SCHED_TABLE + " SET " + SEMESTER_COL + " = '" + sem + "' WHERE " + COURSE_ID_COL + " = '" + id + "';");
    }

    // UPDATE SAVED SCHED SEMESTERS BASED ON PASS/FAIL
    public void updateSemesters(){
        Log.d(LOG_TAG, "Updating null semesters");
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("UPDATE " + SAVED_SCHED_TABLE
                    + " SET " + SEMESTER_COL + " = NULL"
                    + " WHERE " + COURSE_ID_COL + " IN (SELECT " + COURSE_ID_COL
                                                    + " FROM " + RECORD_TABLE
                                                    + " WHERE " + STATUS_COL +  " = 1);");
    }

    // SEND PASS FAIL INFORMATION
    // SEND PASS FAIL INFORMATION
    public Cursor sendPassFail(String sem){
        Log.d(LOG_TAG, "Sending pass/fail info");
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT I." + COURSE_ID_COL + ", R." + STATUS_COL
                + " FROM " + IDEAL_SCHED_TABLE + " I JOIN " + RECORD_TABLE + " R "
                + "ON I." + COURSE_ID_COL + " = R." + COURSE_ID_COL
                + " WHERE I." + SEMESTER_COL + " = '" + sem + "';";
        return db.rawQuery(query, null);
    }

    // UPDATE BACKUP SCHED WITH SAVED SCHED
    //public void updateBackupSched(){

    //}

        /* END FILL DATA */
}