package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button btnLogs_main_menu = (Button)findViewById(R.id.btn_logs_main_menu);

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

        btnLogs_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogDataListActivity.this, MainMenu.class));
            }
        });
    }
}
