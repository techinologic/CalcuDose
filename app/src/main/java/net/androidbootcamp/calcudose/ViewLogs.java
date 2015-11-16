package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ViewLogs extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    LogDbHelper logDbHelper;
    Cursor cursor;
    LogListDataAdapter logListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_data_list);

        listView = (ListView) findViewById(R.id.log_list_view);
        logListDataAdapter = new LogListDataAdapter(getApplicationContext(),
                R.layout.log_row_layout);
        listView.setAdapter(logListDataAdapter);

        logDbHelper = new LogDbHelper(getApplicationContext());
        sqLiteDatabase = logDbHelper.getReadableDatabase();
        cursor = logDbHelper.getLogEventInformation(sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                String name, bg, dose, date;
                name = cursor.getString(0);
                bg = cursor.getString(1);
                dose = cursor.getString(2);
                date = cursor.getString(3);

                DataProvider dataProvider = new DataProvider(name, bg, dose, date);

                logListDataAdapter.add(dataProvider); //this will pass each row of data in adapter

            } while (cursor.moveToNext());
        }

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                Intent mainIntent = new Intent(ViewLogs.this, Servings.class);
                startActivity(mainIntent);
            }
        });

    }

}
