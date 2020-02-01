package com.group1.EngPlan;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String LOG_TAG = DatabaseHandler.class.getSimpleName();
    SQLiteDatabase db;

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
        Log.d(LOG_TAG, "In constructor for database");
        db = getWritableDatabase();
    }

    /* METHODS FOR DATABASE CREATION AND INITIALIZATION BEGIN */
    // CREATE ALL TABLES
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Start of onCreate() for the database");
        db.execSQL("CREATE TABLE " + COURSE_LIST_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8) UNIQUE NOT null, " + COURSE_NAME_COL + " VARCHAR(75) NOT null, " + COURSE_OFFERED_COL + " CHAR(1), "
                + COURSE_PREREQ1_COL + " CHAR(8), " + COURSE_PREREQ2_COL + " CHAR(8), " + COURSE_TO1_COL + " CHAR(8), "
                + COURSE_TO2_COL + " CHAR(8), " + COURSE_TO3_COL + " CHAR(8), " + COURSE_TO4_COL + " CHAR(8));");
        fillCourseTable(db);
            /*populateCourseTable(db);
            db.execSQL("CREATE TABLE " + IDEAL_SCHED_TABLE
                    + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                    + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
            populateIdealSchedTable(db);
            db.execSQL("CREATE TABLE " + RECORD_TABLE
                    + "(" + COURSE_ID_COL + " CHAR(8), " + STATUS_COL + " CHAR(1) DEFAULT 'N',"
                    + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
            populateRecordTable(db);
            db.execSQL("CREATE TABLE " + SAVED_SCHED_TABLE
                    + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                    + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
            populateSavedSchedTable(db);
        db.execSQL("CREATE TABLE " + BACKUP_SCHED_TABLE
                + "(" + COURSE_ID_COL + " CHAR(8), " + SEMESTER_COL + " CHAR(2),"
                + "FOREIGN KEY(" + COURSE_ID_COL + ") REFERENCES " + COURSE_LIST_TABLE + "(" + COURSE_ID_COL + "));");
        populateBackupSchedTable(db);*/

        Log.d(LOG_TAG, "End of onCreate() for the database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_LIST_TABLE);
        /*db.execSQL("DROP TABLE IF EXISTS " + IDEAL_SCHED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SAVED_SCHED_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BACKUP_SCHED_TABLE);*/
        onCreate(db);
    }

    public void fillCourseTable(SQLiteDatabase db){
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP1000', 'Career Management', 'B', null, null, null, null, null, null)");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP2080', 'Work Term 1', 'F', 'COOP1000', null, null, null, null, null)");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + " VALUES ('COOP2180', 'Work Term 2', 'F', 'COOP1000', 'COOP2180', null, null, null, null)");
        /*db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG2010", "Computer Architecture & Assembly Language", "F", "MATH1230", "SENG1210", "SENG3130", "CENG3010", null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG2030", "Introduction to Digital Signal Processing", "W", "MATH2110", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG3010", "Computer System Design", "F", "CENG2010", "EPHY2990", "CENG3020", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG3020", "Real Time Systems Design", "W", "CENG3010", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG3310", "Digital Communications Systems", "F", "EPHY2100", "MATH2240", "CENG4320", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CENG4320", "Communications Networks", "W", "CENG3310", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CHEM1520", "Principles of Chemistry", "W", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "CMNS1290", "Introduction to Professional Writing", "B", "ENGL1100", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "COMP3410", "Operating Systems", "B", "SENG3110", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "COMP3610", "Database Management Systems Design", "B", "SENG3110", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "DRAF1520", "Engineering Graphics", "F", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EENG3010", "Introduction to Control Systems", "W", "MATH2240", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGL1100", "Introduction to University Writing", "B", "MATH2240", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGR1100", "Introduction to Engineering & Design", "B", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGR2200", "Engineering in Society, Health and Safety", "F", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGR2300", "Engineering Management", "W", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGR2400", "Engineering Economics", "W", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "ENGR3300", "Engineering Professional Ethics", "W", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY1150", "Physics for Engineers 1", "F", null, null, "EPHY1250", "EPHY1700", "EPHY1990", "PHYS1250");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY1250", "Physics for Engineers 2", "W", "EPHY1150", null, "EPHY2200", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY1700", "Engineering Mechanics 1", "W", "EPHY1150", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY1990", "Introduction to Engineering Measurements", "W", "EPHY1150", "MATH1130", null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY2200", "Electrical Properties of Materials", "F", "EPHY1250", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY2300", "Digital Electronics", "W", "PHYS2150", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "EPHY2990", "ECE Design", "W", "PHYS2150", null, "CENG3010", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "MATH1130", "Enriched Calculus 1", "F", null, null, "EPHY1990", "MATH1230", "PHYS2150", null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "MATH1230", "Enriched Calculus 2", "W", "MATH1130", null, "CENG2010", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "MATH1300", "Linear Algebra for Engineers", "F", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "MATH1700", "Discrete Mathematics", "B", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "PHYS2150", "Circuit Analysis", "F", "EPHY1150", "MATH1130", "EPHY2300", "EPHY2990", null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "PHYS2250", "Intermediate Electromagnetism", "W", "EPHY1150", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG1110", "Programming for Engineers 1", "F", null, null, "SENG1210", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG1210", "Programming for Engineers 2", "W", "SENG1110", null, "SENG3110", "CENG2010", null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG3110", "Algorithms & Data Structures", "F", "SENG1210", "STAT2230", "COMP3410", "COMP3610", "SENG3120", "SENG3210");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG3120", "Software Engineering Design: Process & Principles", "W", "SENG3110", null, "SENG4100", "SENG4130", null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG3130", "Software Requirements and Specifications", "F", "CENG2010", "ENGR2300", "SENG4230", null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG3210", "Applied Software Engineering", "W", "SENG3110", null, "SENG4110", "SENG4120", "SENG4140", "SENG4220");
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4100", "Software Engineering Design Project", "F", "SENG3120", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4110", "Software Testing & Verifications", "F", "SENG3210", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4120", "Software Model Engineering & Formal Methods", "F", "SENG3210", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4130", "Software Design Patterns", "F", "SENG3120", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4140", "Software Quality Engineering", "W", "SENG3210", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4220", "Software Security Engineering", "W", "SENG3210", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG4230", "Software Estimation", "W", "SENG3130", null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "STAT2230", "Probability and Statistics for Engineers", "F", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "NSCIXXXX", "Natural Science Elective", "B", null, null, null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG41XX", "Upper Level Technical Elective-1", "F", "SENG3210", "SENG3120", null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG42XX", "Upper Level Technical Elective-2", "F", "SENG3210", "SENG3120", null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG43XX", "Upper Level Technical Elective-3", "W", "SENG3210", "SENG3120", null, null, null, null);
        db.execSQL("INSERT INTO " + COURSE_LIST_TABLE + "SENG44XX", "Upper Level Technical Elective-4", "W", "SENG3210", "SENG3120", null, null, null, null);*/
    }

    /*// POPULATE COURSE_LIST_TABLE WITH MASTER VALUES
    public void populateCourseTable(SQLiteDatabase db){
        insertIntoCourseList("COOP1000", "Career Management", "B", null, null, null, null, null, null);
        insertIntoCourseList("COOP2080", "Work Term 1", "F", "COOP1000", null, null, null, null, null);
        insertIntoCourseList("COOP2180", "Work Term 2", "F", "COOP1000", "COOP2180", null, null, null, null);
        insertIntoCourseList("CENG2010", "Computer Architecture & Assembly Language", "F", "MATH1230", "SENG1210", "SENG3130", "CENG3010", null, null);
        insertIntoCourseList("CENG2030", "Introduction to Digital Signal Processing", "W", "MATH2110", null, null, null, null, null);
        insertIntoCourseList("CENG3010", "Computer System Design", "F", "CENG2010", "EPHY2990", "CENG3020", null, null, null);
        insertIntoCourseList("CENG3020", "Real Time Systems Design", "W", "CENG3010", null, null, null, null, null);
        insertIntoCourseList("CENG3310", "Digital Communications Systems", "F", "EPHY2100", "MATH2240", "CENG4320", null, null, null);
        insertIntoCourseList("CENG4320", "Communications Networks", "W", "CENG3310", null, null, null, null, null);
        insertIntoCourseList("CHEM1520", "Principles of Chemistry", "W", null, null, null, null, null, null);
        insertIntoCourseList("CMNS1290", "Introduction to Professional Writing", "B", "ENGL1100", null, null, null, null, null);
        insertIntoCourseList("COMP3410", "Operating Systems", "B", "SENG3110", null, null, null, null, null);
        insertIntoCourseList("COMP3610", "Database Management Systems Design", "B", "SENG3110", null, null, null, null, null);
        insertIntoCourseList("DRAF1520", "Engineering Graphics", "F", null, null, null, null, null, null);
        insertIntoCourseList("EENG3010", "Introduction to Control Systems", "W", "MATH2240", null, null, null, null, null);
        insertIntoCourseList("ENGL1100", "Introduction to University Writing", "B", "MATH2240", null, null, null, null, null);
        insertIntoCourseList("ENGR1100", "Introduction to Engineering & Design", "B", null, null, null, null, null, null);
        insertIntoCourseList("ENGR2200", "Engineering in Society, Health and Safety", "F", null, null, null, null, null, null);
        insertIntoCourseList("ENGR2300", "Engineering Management", "W", null, null, null, null, null, null);
        insertIntoCourseList("ENGR2400", "Engineering Economics", "W", null, null, null, null, null, null);
        insertIntoCourseList("ENGR3300", "Engineering Professional Ethics", "W", null, null, null, null, null, null);
        insertIntoCourseList("EPHY1150", "Physics for Engineers 1", "F", null, null, "EPHY1250", "EPHY1700", "EPHY1990", "PHYS1250");
        insertIntoCourseList("EPHY1250", "Physics for Engineers 2", "W", "EPHY1150", null, "EPHY2200", null, null, null);
        insertIntoCourseList("EPHY1700", "Engineering Mechanics 1", "W", "EPHY1150", null, null, null, null, null);
        insertIntoCourseList("EPHY1990", "Introduction to Engineering Measurements", "W", "EPHY1150", "MATH1130", null, null, null, null);
        insertIntoCourseList("EPHY2200", "Electrical Properties of Materials", "F", "EPHY1250", null, null, null, null, null);
        insertIntoCourseList("EPHY2300", "Digital Electronics", "W", "PHYS2150", null, null, null, null, null);
        insertIntoCourseList("EPHY2990", "ECE Design", "W", "PHYS2150", null, "CENG3010", null, null, null);
        insertIntoCourseList("MATH1130", "Enriched Calculus 1", "F", null, null, "EPHY1990", "MATH1230", "PHYS2150", null);
        insertIntoCourseList("MATH1230", "Enriched Calculus 2", "W", "MATH1130", null, "CENG2010", null, null, null);
        insertIntoCourseList("MATH1300", "Linear Algebra for Engineers", "F", null, null, null, null, null, null);
        insertIntoCourseList("MATH1700", "Discrete Mathematics", "B", null, null, null, null, null, null);
        insertIntoCourseList("PHYS2150", "Circuit Analysis", "F", "EPHY1150", "MATH1130", "EPHY2300", "EPHY2990", null, null);
        insertIntoCourseList("PHYS2250", "Intermediate Electromagnetism", "W", "EPHY1150", null, null, null, null, null);
        insertIntoCourseList("SENG1110", "Programming for Engineers 1", "F", null, null, "SENG1210", null, null, null);
        insertIntoCourseList("SENG1210", "Programming for Engineers 2", "W", "SENG1110", null, "SENG3110", "CENG2010", null, null);
        insertIntoCourseList("SENG3110", "Algorithms & Data Structures", "F", "SENG1210", "STAT2230", "COMP3410", "COMP3610", "SENG3120", "SENG3210");
        insertIntoCourseList("SENG3120", "Software Engineering Design: Process & Principles", "W", "SENG3110", null, "SENG4100", "SENG4130", null, null);
        insertIntoCourseList("SENG3130", "Software Requirements and Specifications", "F", "CENG2010", "ENGR2300", "SENG4230", null, null, null);
        insertIntoCourseList("SENG3210", "Applied Software Engineering", "W", "SENG3110", null, "SENG4110", "SENG4120", "SENG4140", "SENG4220");
        insertIntoCourseList("SENG4100", "Software Engineering Design Project", "F", "SENG3120", null, null, null, null, null);
        insertIntoCourseList("SENG4110", "Software Testing & Verifications", "F", "SENG3210", null, null, null, null, null);
        insertIntoCourseList("SENG4120", "Software Model Engineering & Formal Methods", "F", "SENG3210", null, null, null, null, null);
        insertIntoCourseList("SENG4130", "Software Design Patterns", "F", "SENG3120", null, null, null, null, null);
        insertIntoCourseList("SENG4140", "Software Quality Engineering", "W", "SENG3210", null, null, null, null, null);
        insertIntoCourseList("SENG4220", "Software Security Engineering", "W", "SENG3210", null, null, null, null, null);
        insertIntoCourseList("SENG4230", "Software Estimation", "W", "SENG3130", null, null, null, null, null);
        insertIntoCourseList("STAT2230", "Probability and Statistics for Engineers", "F", null, null, null, null, null, null);
        insertIntoCourseList("NSCIXXXX", "Natural Science Elective", "B", null, null, null, null, null, null);
        insertIntoCourseList("SENG41XX", "Upper Level Technical Elective-1", "F", "SENG3210", "SENG3120", null, null, null, null);
        insertIntoCourseList("SENG42XX", "Upper Level Technical Elective-2", "F", "SENG3210", "SENG3120", null, null, null, null);
        insertIntoCourseList("SENG43XX", "Upper Level Technical Elective-3", "W", "SENG3210", "SENG3120", null, null, null, null);
        insertIntoCourseList("SENG44XX", "Upper Level Technical Elective-4", "W", "SENG3210", "SENG3120", null, null, null, null);
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
            Log.d(LOG_TAG, "Cannot insert " + COURSE_ID_COL + " INTO TABLE " + COURSE_LIST_TABLE);
        return result != -1;
    }
    // POPULATE IDEAL_SCHED_TABLE WITH MASTER PLAN
    private void populateIdealSchedTable(SQLiteDatabase db){
        insertIntoIdealSched("COOP1000", "F2");
        insertIntoIdealSched("COOP2080", "F4");
        insertIntoIdealSched("COOP2180", "W4");
        insertIntoIdealSched("CENG2010", "F2");
        insertIntoIdealSched("CENG2030", "W2");
        insertIntoIdealSched("CENG3010", "F3");
        insertIntoIdealSched("CENG3020", "W3");
        insertIntoIdealSched("CENG3310", "F3");
        insertIntoIdealSched("CENG4320", "W5");
        insertIntoIdealSched("CHEM1520", "W2");
        insertIntoIdealSched("CMNS1290", "F2");
        insertIntoIdealSched("COMP3410", "W3");
        insertIntoIdealSched("COMP3610", "W3");
        insertIntoIdealSched("DRAF1520", "F1");
        insertIntoIdealSched("EENG3010", "W3");
        insertIntoIdealSched("ENGL1100", "F1");
        insertIntoIdealSched("ENGR1100", "F1");
        insertIntoIdealSched("ENGR2200", "F2");
        insertIntoIdealSched("ENGR2300", "W2");
        insertIntoIdealSched("ENGR2400", "W2");
        insertIntoIdealSched("ENGR3300", "W3");
        insertIntoIdealSched("EPHY1150", "F1");
        insertIntoIdealSched("EPHY1250", "W1");
        insertIntoIdealSched("EPHY1700", "W1");
        insertIntoIdealSched("EPHY1990", "W1");
        insertIntoIdealSched("EPHY2200", "F2");
        insertIntoIdealSched("EPHY2300", "W2");
        insertIntoIdealSched("EPHY2990", "W2");
        insertIntoIdealSched("MATH1130", "F1");
        insertIntoIdealSched("MATH1230", "W1");
        insertIntoIdealSched("MATH1300", "F1");
        insertIntoIdealSched("MATH1700", "W2");
        insertIntoIdealSched("PHYS2150", "F2");
        insertIntoIdealSched("PHYS2250", "W2");
        insertIntoIdealSched("SENG1110", "F1");
        insertIntoIdealSched("SENG1210", "W1");
        insertIntoIdealSched("SENG3110", "F3");
        insertIntoIdealSched("SENG3120", "W3");
        insertIntoIdealSched("SENG3130", "F3");
        insertIntoIdealSched("SENG3210", "W3");
        insertIntoIdealSched("SENG4100", "F5");
        insertIntoIdealSched("SENG4110", "F5");
        insertIntoIdealSched("SENG4120", "F5");
        insertIntoIdealSched("SENG4130", "F5");
        insertIntoIdealSched("SENG4140", "W5");
        insertIntoIdealSched("SENG4220", "W5");
        insertIntoIdealSched("SENG4230", "W5");
        insertIntoIdealSched("STAT2230", "F5");
        insertIntoIdealSched("NSCIXXXX", "W3");
        insertIntoIdealSched("SENG41XX", "F5");
        insertIntoIdealSched("SENG42XX", "F5");
        insertIntoIdealSched("SENG43XX", "W5");
        insertIntoIdealSched("SENG44XX", "W5");
    }
    // ADD ALL LINES TO IDEAL_SCHED_TABLE
    private boolean insertIntoIdealSched(String id, String sem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID_COL, id);
        contentValues.put(SEMESTER_COL, sem);
        long result = db.insert(IDEAL_SCHED_TABLE, null, contentValues);
        if(result == -1)
            Log.d(LOG_TAG, "Cannot insert " + COURSE_ID_COL + " INTO TABLE " + IDEAL_SCHED_TABLE);
        return result != -1;
    }
    // POPULATE RECORD_TABLE WITH MASTER PLAN
    private void populateRecordTable(SQLiteDatabase db){
        insertIntoRecord("COOP1000");
        insertIntoRecord("COOP2080");
        insertIntoRecord("COOP2180");
        insertIntoRecord("CENG2010");
        insertIntoRecord("CENG2030");
        insertIntoRecord("CENG3010");
        insertIntoRecord("CENG3020");
        insertIntoRecord("CENG3310");
        insertIntoRecord("CENG4320");
        insertIntoRecord("CHEM1520");
        insertIntoRecord("CMNS1290");
        insertIntoRecord("COMP3410");
        insertIntoRecord("COMP3610");
        insertIntoRecord("DRAF1520");
        insertIntoRecord("EENG3010");
        insertIntoRecord("ENGL1100");
        insertIntoRecord("ENGR1100");
        insertIntoRecord("ENGR2200");
        insertIntoRecord("ENGR2300");
        insertIntoRecord("ENGR2400");
        insertIntoRecord("ENGR3300");
        insertIntoRecord("EPHY1150");
        insertIntoRecord("EPHY1250");
        insertIntoRecord("EPHY1700");
        insertIntoRecord("EPHY1990");
        insertIntoRecord("EPHY2200");
        insertIntoRecord("EPHY2300");
        insertIntoRecord("EPHY2990");
        insertIntoRecord("MATH1130");
        insertIntoRecord("MATH1230");
        insertIntoRecord("MATH1300");
        insertIntoRecord("MATH1700");
        insertIntoRecord("PHYS2150");
        insertIntoRecord("PHYS2250");
        insertIntoRecord("SENG1110");
        insertIntoRecord("SENG1210");
        insertIntoRecord("SENG3110");
        insertIntoRecord("SENG3120");
        insertIntoRecord("SENG3130");
        insertIntoRecord("SENG3210");
        insertIntoRecord("SENG4100");
        insertIntoRecord("SENG4110");
        insertIntoRecord("SENG4120");
        insertIntoRecord("SENG4130");
        insertIntoRecord("SENG4140");
        insertIntoRecord("SENG4220");
        insertIntoRecord("SENG4230");
        insertIntoRecord("STAT2230");
        insertIntoRecord("NSCIXXXX");
        insertIntoRecord("SENG41XX");
        insertIntoRecord("SENG42XX");
        insertIntoRecord("SENG43XX");
        insertIntoRecord("SENG44XX");
    }
    // ADD ALL LINES TO RECORD_TABLE
    private boolean insertIntoRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID_COL, id);
        long result = db.insert(RECORD_TABLE, null, contentValues);
        if(result == -1)
            Log.d(LOG_TAG, "Cannot insert " + COURSE_ID_COL + " INTO TABLE " + RECORD_TABLE);
        return result != -1;
    }
    // POPULATE SAVED_SCHED_TABLE WITH MASTER PLAN
    private void populateSavedSchedTable(SQLiteDatabase db){
        insertIntoSavedSched("COOP1000", "F2");
        insertIntoSavedSched("COOP2080", "F4");
        insertIntoSavedSched("COOP2180", "W4");
        insertIntoSavedSched("CENG2010", "F2");
        insertIntoSavedSched("CENG2030", "W2");
        insertIntoSavedSched("CENG3010", "F3");
        insertIntoSavedSched("CENG3020", "W3");
        insertIntoSavedSched("CENG3310", "F3");
        insertIntoSavedSched("CENG4320", "W5");
        insertIntoSavedSched("CHEM1520", "W2");
        insertIntoSavedSched("CMNS1290", "F2");
        insertIntoSavedSched("COMP3410", "W3");
        insertIntoSavedSched("COMP3610", "W3");
        insertIntoSavedSched("DRAF1520", "F1");
        insertIntoSavedSched("EENG3010", "W3");
        insertIntoSavedSched("ENGL1100", "F1");
        insertIntoSavedSched("ENGR1100", "F1");
        insertIntoSavedSched("ENGR2200", "F2");
        insertIntoSavedSched("ENGR2300", "W2");
        insertIntoSavedSched("ENGR2400", "W2");
        insertIntoSavedSched("ENGR3300", "W3");
        insertIntoSavedSched("EPHY1150", "F1");
        insertIntoSavedSched("EPHY1250", "W1");
        insertIntoSavedSched("EPHY1700", "W1");
        insertIntoSavedSched("EPHY1990", "W1");
        insertIntoSavedSched("EPHY2200", "F2");
        insertIntoSavedSched("EPHY2300", "W2");
        insertIntoSavedSched("EPHY2990", "W2");
        insertIntoSavedSched("MATH1130", "F1");
        insertIntoSavedSched("MATH1230", "W1");
        insertIntoSavedSched("MATH1300", "F1");
        insertIntoSavedSched("MATH1700", "W2");
        insertIntoSavedSched("PHYS2150", "F2");
        insertIntoSavedSched("PHYS2250", "W2");
        insertIntoSavedSched("SENG1110", "F1");
        insertIntoSavedSched("SENG1210", "W1");
        insertIntoSavedSched("SENG3110", "F3");
        insertIntoSavedSched("SENG3120", "W3");
        insertIntoSavedSched("SENG3130", "F3");
        insertIntoSavedSched("SENG3210", "W3");
        insertIntoSavedSched("SENG4100", "F5");
        insertIntoSavedSched("SENG4110", "F5");
        insertIntoSavedSched("SENG4120", "F5");
        insertIntoSavedSched("SENG4130", "F5");
        insertIntoSavedSched("SENG4140", "W5");
        insertIntoSavedSched("SENG4220", "W5");
        insertIntoSavedSched("SENG4230", "W5");
        insertIntoSavedSched("STAT2230", "F5");
        insertIntoSavedSched("NSCIXXXX", "W3");
        insertIntoSavedSched("SENG41XX", "F5");
        insertIntoSavedSched("SENG42XX", "F5");
        insertIntoSavedSched("SENG43XX", "W5");
        insertIntoSavedSched("SENG44XX", "W5");
    }
    // ADD ALL LINES TO SAVED_SCHED_TABLE
    private boolean insertIntoSavedSched(String id, String sem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID_COL, id);
        contentValues.put(SEMESTER_COL, sem);
        long result = db.insert(SAVED_SCHED_TABLE, null, contentValues);
        if(result == -1)
            Log.d(LOG_TAG, "Cannot insert " + COURSE_ID_COL + " INTO TABLE " + SAVED_SCHED_TABLE);
        return result != -1;
    }
    // POPULATE BACKUP_SCHED_TABLE
    private void populateBackupSchedTable(SQLiteDatabase db){
        insertIntoBackupSched("COOP1000");
        insertIntoBackupSched("COOP2080");
        insertIntoBackupSched("COOP2180");
        insertIntoBackupSched("CENG2010");
        insertIntoBackupSched("CENG2030");
        insertIntoBackupSched("CENG3010");
        insertIntoBackupSched("CENG3020");
        insertIntoBackupSched("CENG3310");
        insertIntoBackupSched("CENG4320");
        insertIntoBackupSched("CHEM1520");
        insertIntoBackupSched("CMNS1290");
        insertIntoBackupSched("COMP3410");
        insertIntoBackupSched("COMP3610");
        insertIntoBackupSched("DRAF1520");
        insertIntoBackupSched("EENG3010");
        insertIntoBackupSched("ENGL1100");
        insertIntoBackupSched("ENGR1100");
        insertIntoBackupSched("ENGR2200");
        insertIntoBackupSched("ENGR2300");
        insertIntoBackupSched("ENGR2400");
        insertIntoBackupSched("ENGR3300");
        insertIntoBackupSched("EPHY1150");
        insertIntoBackupSched("EPHY1250");
        insertIntoBackupSched("EPHY1700");
        insertIntoBackupSched("EPHY1990");
        insertIntoBackupSched("EPHY2200");
        insertIntoBackupSched("EPHY2300");
        insertIntoBackupSched("EPHY2990");
        insertIntoBackupSched("MATH1130");
        insertIntoBackupSched("MATH1230");
        insertIntoBackupSched("MATH1300");
        insertIntoBackupSched("MATH1700");
        insertIntoBackupSched("PHYS2150");
        insertIntoBackupSched("PHYS2250");
        insertIntoBackupSched("SENG1110");
        insertIntoBackupSched("SENG1210");
        insertIntoBackupSched("SENG3110");
        insertIntoBackupSched("SENG3120");
        insertIntoBackupSched("SENG3130");
        insertIntoBackupSched("SENG3210");
        insertIntoBackupSched("SENG4100");
        insertIntoBackupSched("SENG4110");
        insertIntoBackupSched("SENG4120");
        insertIntoBackupSched("SENG4130");
        insertIntoBackupSched("SENG4140");
        insertIntoBackupSched("SENG4220");
        insertIntoBackupSched("SENG4230");
        insertIntoBackupSched("STAT2230");
        insertIntoBackupSched("NSCIXXXX");
        insertIntoBackupSched("SENG41XX");
        insertIntoBackupSched("SENG42XX");
        insertIntoBackupSched("SENG43XX");
        insertIntoBackupSched("SENG44XX");
    }
    // ADD ALL LINES TO BACKUP_SCHED_TABLE WITH null VALUES
    private boolean insertIntoBackupSched(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID_COL, id);
        long result = db.insert(BACKUP_SCHED_TABLE, null, contentValues);
        if(result == -1)
            Log.d(LOG_TAG, "Cannot insert " + COURSE_ID_COL + " INTO TABLE " + BACKUP_SCHED_TABLE);
        return result != -1;
    }
    /* METHODS FOR DATABASE CREATION AND INITIALIZATION END */

    public Cursor returnValue(){
        Log.d(LOG_TAG, "In return value");
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + COURSE_LIST_TABLE + ";";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}