package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class FoodDataListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    FoodDbHelper foodDbHelper;
    Cursor cursor;
    FoodListDataAdapter foodListDataAdapter;

    String search_name;
    String display_name;


    String name, servings, carbs, fat, protein;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_data_list);

        listView = (ListView) findViewById(R.id.food_list_view);
        foodListDataAdapter = new FoodListDataAdapter(getApplicationContext(), R.layout.food_row_layout);
        listView.setAdapter(foodListDataAdapter);

        foodDbHelper = new FoodDbHelper(getApplicationContext());
        sqLiteDatabase = foodDbHelper.getReadableDatabase();
        cursor = foodDbHelper.getInformation(sqLiteDatabase);

        Button btn_search = (Button) findViewById(R.id.btn_list_search);
        Button btn_addnew = (Button) findViewById(R.id.btn_addnew);

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
                servings = cursor.getString(1);
                carbs = cursor.getString(2);
                fat = cursor.getString(3);
                protein = cursor.getString(4);

                FoodDataProvider foodDataProvider = new FoodDataProvider(name, servings, carbs, fat, protein);

                foodListDataAdapter.add(foodDataProvider); //this will pass each row of data in adapter

            } while (cursor.moveToNext());
        }
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long l) {


                Toast.makeText(getApplicationContext(), "Future Feature", Toast.LENGTH_SHORT).show();
                //Intent mainIntent = new Intent(FoodDataListActivity.this, Servings.class);
                //startActivity(mainIntent);
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDataListActivity.this, SearchFood.class));
            }
        });

        btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDataListActivity.this, AddFood.class));
            }
        });
    }

}
