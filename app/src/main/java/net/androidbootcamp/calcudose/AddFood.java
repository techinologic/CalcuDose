package net.androidbootcamp.calcudose;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFood extends AppCompatActivity {

    EditText foodservings, foodname, foodcarb, foodfat, foodprotein;
    Context context = this;
    FoodDbHelper foodDbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodname = (EditText) findViewById(R.id.add_foodname);
        foodservings = (EditText) findViewById(R.id.add_foodservings);
        foodcarb = (EditText) findViewById(R.id.add_foodcarbs);
        foodfat = (EditText) findViewById(R.id.add_foodfat);
        foodprotein = (EditText) findViewById(R.id.add_foodprotein);
    }

    public void addFood(View view){
        String name = foodname.getText().toString();
        String servings = foodservings.getText().toString();
        String carb = foodcarb.getText().toString();
        String fat = foodfat.getText().toString();
        String  protein = foodprotein.getText().toString();

        foodDbHelper = new FoodDbHelper(context);
        sqLiteDatabase = foodDbHelper.getWritableDatabase();
        foodDbHelper.addFood(name, servings, carb, fat, protein, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Food Saved", Toast.LENGTH_LONG).show();
        foodDbHelper.close();
    }
}
