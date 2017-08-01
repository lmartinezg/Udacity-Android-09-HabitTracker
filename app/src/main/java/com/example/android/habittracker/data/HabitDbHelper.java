package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habitTracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 2;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the habits table
        final String SQL_CREATE_HABITS_TABLE =
                "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                        HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        HabitEntry.COLUMN_HABIT_DESC + " TEXT NOT NULL," +
                        HabitEntry.COLUMN_HABIT_TYPE + " INTEGER NOT NULL," +
                        HabitEntry.COLUMN_HABIT_TIMES + " INTEGER NOT NULL, " +
                        HabitEntry.COLUMN_HABIT_PERIOD + " INTEGER NOT NULL);";

        Log.v(LOG_TAG, SQL_CREATE_HABITS_TABLE);

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Simple implementation of the method onUpgrade, as suggested
        // Drop the table and call onCreate() to create a new one.
        dropTables(db);
        onCreate(db);
    }

    /**
     * Drop tables from the database
     * @param db The SQLite Database
     */
    public void dropTables(SQLiteDatabase db){

        final String SQL_DROP_HABITS_TABLE =
                "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME + ";";

        Log.v(LOG_TAG, SQL_DROP_HABITS_TABLE);

        // Execute the SQL statement
        db.execSQL(SQL_DROP_HABITS_TABLE);

    }
}
