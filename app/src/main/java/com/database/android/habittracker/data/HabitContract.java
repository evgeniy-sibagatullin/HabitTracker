package com.database.android.habittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {}

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Title of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_TITLE = "title";

        /**
         * Age of the habit counted in weeks.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_AGE_WEEKS = "age_weeks";

        /**
         * Usefulness of the habit (from 0 'not useful' to 4 'extremely useful').
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_USEFULNESS = "usefulness";

        /**
         * Optional note about the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NOTE = "note";
    }
}
