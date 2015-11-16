package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FoodDataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    FoodDbHelper foodDbHelper;
    Cursor cursor;
    FoodListDataAdapter foodListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_data_list);

        listView = (ListView) findViewById(R.id.food_list_view);
        foodListDataAdapter = new FoodListDataAdapter(getApplicationContext(), R.layout.food_row_layout);
        listView.setAdapter(foodListDataAdapter);

        foodDbHelper = new FoodDbHelper(getApplicationContext());
        sqLiteDatabase = foodDbHelper.getReadableDatabase();
        cursor = foodDbHelper.getInformation(sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                String name, servings, carbs, fat, protein;
                name = cursor.getString(0);
                servings = cursor.getString(1);
                carbs = cursor.getString(2);
                fat = cursor.getString(3);
                protein = cursor.getString(4);

                DataProvider dataProvider = new DataProvider(name, servings, carbs, fat, protein);

                foodListDataAdapter.add(dataProvider); //this will pass each row of data in adapter

            } while (cursor.moveToNext());
        }
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                Intent mainIntent = new Intent(FoodDataListActivity.this, Servings.class);
                startActivity(mainIntent);
            }
        });
    }

}
