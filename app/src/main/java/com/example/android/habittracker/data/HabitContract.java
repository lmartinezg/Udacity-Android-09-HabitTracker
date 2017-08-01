package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    /* Private constructor to avoid instantiating this class */
    private HabitContract() {
    }

    /* Inner class that defines the table contents */
    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_DESC = "description";
        public static final String COLUMN_HABIT_TYPE = "type";
        public static final String COLUMN_HABIT_TIMES = "times";
        public static final String COLUMN_HABIT_PERIOD = "period";

        // Values for type
        public static final int TYPE_OUTDOOR = 0;
        public static final int TYPE_FOOD = 1;
        public static final int TYPE_DRINK = 2;
        public static final int TYPE_REST = 3;
        public static final int TYPE_SPORTS = 4;

        // Values for period
        public static final int PERIOD_HOURLY = 0;
        public static final int PERIOD_DAILY = 1;
        public static final int PERIOD_WEEKLY = 2;
        public static final int PERIOD_MONTHLY = 3;
        public static final int PERIOD_YEARLY = 4;

    }
}
