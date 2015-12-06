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
import android.widget.Toast;

public class FoodDataListActivity extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";


    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    FoodDbHelper foodDbHelper;
    Cursor cursor;
    FoodListDataAdapter foodListDataAdapter;

    String search_name;
    String display_name;


    String name, servings, carbs, fat, protein;
    int position;
    int fromWhichActivity = 1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_data_list);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);


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

                FoodDataProvider foodDataProvider = new FoodDataProvider(name, servings, carbs, fat, protein, position);

                foodListDataAdapter.add(foodDataProvider); //this will pass each row of data in adapter

            } while (cursor.moveToNext());
        }

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int pos, long l) {

                //save these values and pass them to listfoodActivity textviews.
                cursor.moveToPosition(pos);
                name = cursor.getString(0);
                servings = cursor.getString(1);
                carbs = cursor.getString(2);
                fat = cursor.getString(3);
                protein = cursor.getString(4);
                position = pos;

                SharedPreferences.Editor editor = settings.edit();

                editor.putString("lv_foodname", name);
                editor.putString("lv_foodfat", fat);
                editor.putString("lv_foodcarbs", carbs);
                editor.putString("lv_foodprotein", protein);
                editor.commit();


                Intent intent_foodDose = new Intent(FoodDataListActivity.this, ListFoodDose.class);
                Bundle bundle = new Bundle();

                //int tester = bundle.getInt("fromWhichActivity", 0);

                Toast.makeText(getApplicationContext(), name + " " + carbs + " " + fat + " " + protein, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), bundle.getString("lv_foodname"), Toast.LENGTH_SHORT).show();


                startActivity(intent_foodDose);
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
