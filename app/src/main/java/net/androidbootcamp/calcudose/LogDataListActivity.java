package net.androidbootcamp.calcudose;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class LogDataListActivity extends AppCompatActivity {
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
        logListDataAdapter = new LogListDataAdapter(getApplicationContext(), R.layout.log_row_layout);
        listView.setAdapter(logListDataAdapter);
        logDbHelper = new LogDbHelper(getApplicationContext());
        sqLiteDatabase = logDbHelper.getReadableDatabase(); // read data from db
        cursor = logDbHelper.getLogEventInformation(sqLiteDatabase); //get information from db
        if(cursor.moveToFirst()){
            do{
                String name, bg, dose, oras, date;
                name = cursor.getString(0);
                bg = cursor.getString(1);
                dose = cursor.getString(2);
                oras = cursor.getString(3);
                //date = cursor.getString(4);
                LogDataProvider logdataProvider = new LogDataProvider(name, bg, dose, oras);
                logListDataAdapter.add(logdataProvider);

            }while (cursor.moveToNext());
        }
    }
}
