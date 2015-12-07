package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class FoodDataListActivity extends AppCompatActivity {

    private static final String SETTINGS_PREFERENCES = "Settings";

    private Cursor cursor;

    private String name;
    private String servings;
    private String carbs;
    private String fat;
    private String protein;
    int position;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_data_list);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);

        ListView listView = (ListView) findViewById(R.id.food_list_view);
        FoodListDataAdapter foodListDataAdapter = new FoodListDataAdapter(getApplicationContext());
        listView.setAdapter(foodListDataAdapter);

        FoodDbHelper foodDbHelper = new FoodDbHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = foodDbHelper.getReadableDatabase();
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
            public void onItemClick(AdapterView<?> a, View v, int pos, long l) {

                //retrieve listview data onclick and set to textviews.
                cursor.moveToPosition(a.getCount() - pos - 1);
                name = cursor.getString(0);
                servings = cursor.getString(1);
                carbs = cursor.getString(2);
                fat = cursor.getString(3);
                protein = cursor.getString(4);

                SharedPreferences.Editor editor = settings.edit();

                editor.putString("lv_foodname", name);
                editor.putString("lv_foodfat", fat);
                editor.putString("lv_foodcarbs", carbs);
                editor.putString("lv_foodprotein", protein);
                editor.commit();

                Intent intent_foodDose = new Intent(FoodDataListActivity.this, ListFoodDose.class);

                startActivity(intent_foodDose);
            }
        });

        //search food button
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDataListActivity.this, SearchFood.class));
            }
        });
        //add new food button
        btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDataListActivity.this, AddFood.class));
            }
        });
    }

}
