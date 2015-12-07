package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchFood extends AppCompatActivity {

    TextView display_name, display_carbs, display_fat, display_protein;
    EditText Search_name;
    FoodDbHelper foodDbHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        Button btn_foodDose = (Button) findViewById(R.id.btn_foodDose);

        Search_name = (EditText) findViewById(R.id.search_name);
        display_name = (TextView) findViewById(R.id.displayName);
        display_carbs = (TextView) findViewById(R.id.displayCarbs);
        display_fat = (TextView) findViewById(R.id.displayFat);
        display_protein = (TextView) findViewById(R.id.displayProtein);

        display_name.setVisibility(View.GONE);
        display_carbs.setVisibility(View.GONE);
        display_fat.setVisibility(View.GONE);
        display_protein.setVisibility(View.GONE);

        btn_foodDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchFood.this, FoodDose.class));
            }
        });
    }

    public void searchFood(View view) {

        search_name = Search_name.getText().toString();

        foodDbHelper = new FoodDbHelper(getApplicationContext());
        sqLiteDatabase = foodDbHelper.getReadableDatabase();
        Cursor cursor = foodDbHelper.getFood(search_name, sqLiteDatabase);

        if (cursor.moveToFirst()) {
            String NAME = cursor.getString(0);
            String CARBS = cursor.getString(1);
            String FAT = cursor.getString(2);
            String PROTEIN = cursor.getString(3);

            display_name.setText(NAME);
            display_carbs.setText(CARBS);
            display_fat.setText(FAT);
            display_protein.setText(PROTEIN);

            display_name.setVisibility(View.VISIBLE);
            display_carbs.setVisibility(View.VISIBLE);
            display_fat.setVisibility(View.VISIBLE);
            display_protein.setVisibility(View.VISIBLE);
        }
    }

}
