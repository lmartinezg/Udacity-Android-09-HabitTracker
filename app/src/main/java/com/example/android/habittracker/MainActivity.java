package com.example.android.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestClass myTest = new TestClass(this);

        // For the test, each time the Main Activity is created,
        // the initial content of the table will be logged, then
        // some rows will be added and finally the content will
        // be logged again.
        // The table will grow with each execution because the same rows
        // with different IDs are added each time.

        // Log the initial content
        myTest.readAllHabits();

        // Insert some rows
        myTest.insertNewHabit(getString(R.string.entry_eat_fruit), HabitEntry.TYPE_FOOD, 1,
                HabitEntry.PERIOD_DAILY);
        myTest.insertNewHabit(getString(R.string.entry_sleep_eight), HabitEntry.TYPE_REST, 1,
                HabitEntry.PERIOD_DAILY);
        myTest.insertNewHabit(getString(R.string.entry_train_swimming), HabitEntry.TYPE_SPORTS, 2,
                HabitEntry.PERIOD_WEEKLY);
        myTest.insertNewHabit(getString(R.string.entry_train_spinning), HabitEntry.TYPE_SPORTS, 2,
                HabitEntry.PERIOD_WEEKLY);
        myTest.insertNewHabit(getString(R.string.go_running), HabitEntry.TYPE_OUTDOOR, 2,
                HabitEntry.PERIOD_WEEKLY);

        // Log the final content
        myTest.readAllHabits();

    }
}
