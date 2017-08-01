package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

import static com.example.android.habittracker.data.HabitDbHelper.LOG_TAG;

public class TestClass {

    /* Context */
    private final Context mContext;

    /* DB Helper */
    private final HabitDbHelper mDbHelper;

    /* Indexes for the cursor */
    private int mIdxId;
    private int mIdxDesc;
    private int mIdxType;
    private int mIdxTimes;
    private int mIdxPeriod;

    /* Constructor. To get the context and the database */
    public TestClass(Context mContext) {
        this.mContext = mContext;
        this.mDbHelper = new HabitDbHelper(mContext);
    }

    /**
     * Insert a new habit row in the habits table
     *
     * @param desc   The description
     * @param type   The type
     * @param times  The times this habit is repeated
     * @param period The period for times value (hourly, daily, etc.)
     */
    public void insertNewHabit(String desc, int type, int times, int period) {

        Log.i(LOG_TAG, mContext.getString(R.string.inserting_one_row));

        // Create and/or open a database to write to it
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Prepare the values for a new row
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_DESC, desc);
        values.put(HabitEntry.COLUMN_HABIT_TYPE, type);
        values.put(HabitEntry.COLUMN_HABIT_TIMES, times);
        values.put(HabitEntry.COLUMN_HABIT_PERIOD, period);

        // Insert the new row
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        /* Log the insertion */
        if (newRowId == -1) {
            Log.e(LOG_TAG, mContext.getString(R.string.error_saving));
        } else {
            Log.i(LOG_TAG, String.format(mContext.getString(R.string.habit_saved), newRowId));
        }
    }

    /**
     * Prepares a cursor to read the habits table
     *
     * @return The cursor
     */
    public Cursor prepareCursor() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_DESC,
                HabitEntry.COLUMN_HABIT_TYPE,
                HabitEntry.COLUMN_HABIT_TIMES,
                HabitEntry.COLUMN_HABIT_PERIOD};
        String sortOrder = HabitEntry._ID;
        Cursor cursor = db.query(HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);

        return cursor;
    }

    // Closes the cursor passed as a parameter
    public void freeResources(Cursor cursor) {
        cursor.close();
    }

    /**
     * Main method to read all habits
     */
    public void readAllHabits() {

        Log.i(LOG_TAG, mContext.getString(R.string.printing_all_start));

        Cursor cursor = prepareCursor();

        // Set column indexes
        mIdxId = cursor.getColumnIndex(HabitEntry._ID);
        mIdxDesc = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESC);
        mIdxType = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TYPE);
        mIdxTimes = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIMES);
        mIdxPeriod = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_PERIOD);

        try {
            while (cursor.moveToNext()) {
                String result = parseRow(cursor);
                Log.i(LOG_TAG, result);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            freeResources(cursor);
        }

        Log.i(LOG_TAG, mContext.getString(R.string.printing_all_end));
    }

    /**
     * Return a String with the results for a row
     *
     * @param cursor the cursor at current position
     * @return the string
     */
    private String parseRow(Cursor cursor) {

        long currentId = cursor.getLong(mIdxId);
        String currentDesc = cursor.getString(mIdxDesc);
        int currentType = cursor.getInt(mIdxType);
        int currentTimes = cursor.getInt(mIdxTimes);
        int currentPeriod = cursor.getInt(mIdxPeriod);

        String currentTypeString = null;
        switch (currentType) {
            case (HabitEntry.TYPE_OUTDOOR):
                currentTypeString = mContext.getString(R.string.type_outdoor);
                break;
            case (HabitEntry.TYPE_REST):
                currentTypeString = mContext.getString(R.string.type_rest);
                break;
            case (HabitEntry.TYPE_FOOD):
                currentTypeString = mContext.getString(R.string.type_food);
                break;
            case (HabitEntry.TYPE_SPORTS):
                currentTypeString = mContext.getString(R.string.type_sports);
                break;
        }

        String currentPeriodString = null;
        switch (currentPeriod) {
            case (HabitEntry.PERIOD_HOURLY):
                currentPeriodString = mContext.getString(R.string.period_hourly);
                break;
            case (HabitEntry.PERIOD_DAILY):
                currentPeriodString = mContext.getString(R.string.period_daily);
                break;
            case (HabitEntry.PERIOD_WEEKLY):
                currentPeriodString = mContext.getString(R.string.period_weekly);
                break;
            case (HabitEntry.PERIOD_MONTHLY):
                currentPeriodString = mContext.getString(R.string.period_monthly);
                break;
        }

        return currentId + " - " + currentDesc + " - " + currentTypeString + " - " +
                currentTimes + " - " + currentPeriodString;
    }

    public HabitDbHelper getDbHelper() {
        return mDbHelper;
    }
}
