package net.androidbootcamp.calcudose;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class AddFood extends AppCompatActivity {

    private EditText foodservings;
    private EditText foodname;
    private EditText foodcarb;
    private EditText foodfat;
    private EditText foodprotein;
    private final Context context = this;
    private FoodDbHelper foodDbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private String name;
    private String servings;
    private String carb;
    private String fat;
    private String protein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        foodname = (EditText) findViewById(R.id.add_foodname);
        foodservings = (EditText) findViewById(R.id.add_foodservings);
        foodcarb = (EditText) findViewById(R.id.add_foodcarbs);
        foodfat = (EditText) findViewById(R.id.add_foodfat);
        foodprotein = (EditText) findViewById(R.id.add_foodprotein);

        //scanner widgets
        Button scanBtn = (Button) findViewById(R.id.scan_button);
        //btnAddFood_calculateDose = (Button) findViewById(R.id.btnAddFood_calculateDose);
        //contentTxt = (TextView) findViewById(R.id.contenttxt);
        Button btn_saveFood = (Button) findViewById(R.id.btn_saveFood);
        //


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.scan_button) {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(AddFood.this);
                    scanIntegrator.initiateScan();
                }
            }
        });

        btn_saveFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    private void addFood() {
        if (foodname != null && foodfat != null && foodcarb != null && foodprotein != null) {
            name = foodname.getText().toString();
            servings = foodservings.getText().toString();
            carb = foodcarb.getText().toString();
            fat = foodfat.getText().toString();
            protein = foodprotein.getText().toString();

            foodDbHelper = new FoodDbHelper(context);
            sqLiteDatabase = foodDbHelper.getWritableDatabase();
            foodDbHelper.addFood(name, servings, carb, fat, protein, sqLiteDatabase);
            Toast.makeText(getBaseContext(), "Food Saved", Toast.LENGTH_LONG).show();
            foodDbHelper.close();
            startActivity(new Intent(AddFood.this, FoodDataListActivity.class));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //set foodname to edittext
            String oreo_barcode = "044000029524";
            if (scanContent.equals(oreo_barcode)) {
                String oreo_name = "Double Stuf Oreo";
                foodname.setText(oreo_name, TextView.BufferType.EDITABLE);
                String oreo_servings = "1";
                foodservings.setText(oreo_servings, TextView.BufferType.EDITABLE);
                String oreo_carb = "21";
                foodcarb.setText(oreo_carb, TextView.BufferType.EDITABLE);
                String oreo_fat = "7";
                foodfat.setText(oreo_fat, TextView.BufferType.EDITABLE);
                String oreo_prot = "1";
                foodprotein.setText(oreo_prot, TextView.BufferType.EDITABLE);

            }

        } else {
            Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFoodScanner() {

        foodDbHelper = new FoodDbHelper(context);
        sqLiteDatabase = foodDbHelper.getWritableDatabase();
        foodDbHelper.addFood(name, servings, carb, fat, protein, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Food Saved", Toast.LENGTH_LONG).show();
        foodDbHelper.close();
    }


}
