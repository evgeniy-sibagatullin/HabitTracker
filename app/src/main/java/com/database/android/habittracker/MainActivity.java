package com.database.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.database.android.habittracker.data.HabitContract.HabitEntry;
import com.database.android.habittracker.data.HabitTrackerDbHelper;
import com.database.android.habittracker.model.Habit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertDummyData();
        displayDatabaseInfo();
        cleanDatabase();
    }

    private void insertDummyData() {
        insertHabit(new Habit("Clean hands", 1024));
        insertHabit(new Habit("Morning runs", 18, 3));
        insertHabit(new Habit("Stop eating bread", 3, "Quite difficult"));
        insertHabit(new Habit("Learn Android", 11, 5, "Love it"));
    }

    /**
     * single insert method that adds at least two values of
     * two different datatypes (e.g. INTEGER, STRING)
     * into the database using a ContentValues object and the insert() method
     */
    private void insertHabit(Habit habit) {
        HabitTrackerDbHelper dbHelper = new HabitTrackerDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE, habit.getTitle());
        values.put(HabitEntry.COLUMN_HABIT_AGE_WEEKS, habit.getAgeWeeks());
        values.put(HabitEntry.COLUMN_HABIT_USEFULNESS, habit.getUsefulness());
        values.put(HabitEntry.COLUMN_HABIT_NOTE, habit.getNote());

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving habit", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDatabaseInfo() {
        List<Habit> habits = getHabits();
        TextView dbData = (TextView) findViewById(R.id.db_data);

        dbData.setText("The habits table contains " + habits.size() + " habits.\n\n");

        dbData.append(HabitEntry.COLUMN_HABIT_TITLE + " - " +
                HabitEntry.COLUMN_HABIT_AGE_WEEKS + " - " +
                HabitEntry.COLUMN_HABIT_USEFULNESS + " - [" +
                HabitEntry.COLUMN_HABIT_NOTE + "]\n");

        for (Habit habit : habits) {
            dbData.append("\n" + habit.getTitle() + " - " +
                    habit.getAgeWeeks() + " - " +
                    habit.getUsefulness() + " - [" +
                    habit.getNote() + "]");
        }
    }

    private List<Habit> getHabits() {
        Cursor cursor = getCursorForRead();

        int titleColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TITLE);
        int ageWeeksColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_AGE_WEEKS);
        int usefulnessColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_USEFULNESS);
        int noteColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NOTE);

        List<Habit> habits = new ArrayList<>();
        while (cursor.moveToNext()) {
            habits.add(new Habit(cursor.getString(titleColumnIndex),
                    cursor.getInt(ageWeeksColumnIndex),
                    cursor.getInt(usefulnessColumnIndex),
                    cursor.getString(noteColumnIndex)));
        }

        return habits;
    }

    /**
     * Single read method that returns a Cursor object
     */
    private Cursor getCursorForRead() {
        HabitTrackerDbHelper dbHelper = new HabitTrackerDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_TITLE,
                HabitEntry.COLUMN_HABIT_AGE_WEEKS,
                HabitEntry.COLUMN_HABIT_USEFULNESS,
                HabitEntry.COLUMN_HABIT_NOTE};

        return db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    private void cleanDatabase() {
        HabitTrackerDbHelper dbHelper = new HabitTrackerDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + HabitEntry.TABLE_NAME + ";");
    }
}
