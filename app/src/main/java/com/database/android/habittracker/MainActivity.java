package com.database.android.habittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.database.android.habittracker.data.HabitContract.HabitEntry;
import com.database.android.habittracker.data.HabitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        HabitTrackerDbHelper dbHelper = new HabitTrackerDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + HabitEntry.TABLE_NAME, null);
        TextView dbData = (TextView) findViewById(R.id.db_data);

        try {
            dbData.setText(String.format(getString(R.string.rows_in_table), cursor.getCount()));
        } finally {
            cursor.close();
        }
    }
}
