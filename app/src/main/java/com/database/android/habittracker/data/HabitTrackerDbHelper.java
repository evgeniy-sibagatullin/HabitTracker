package com.database.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.database.android.habittracker.data.HabitContract.HabitEntry;

public class HabitTrackerDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habit_tracker.db";
    private static final int DATABASE_VERSION = 1;

    public HabitTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_TITLE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_AGE_WEEKS + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_USEFULNESS + " INTEGER NOT NULL DEFAULT 2, "
                + HabitEntry.COLUMN_HABIT_NOTE + " TEXT);";
        sqLiteDatabase.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
