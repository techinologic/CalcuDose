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

    EditText foodservings, foodname, foodcarb, foodfat, foodprotein;
    Context context = this;
    FoodDbHelper foodDbHelper;
    SQLiteDatabase sqLiteDatabase;

    String name;
    String servings;
    String carb;
    String fat;
    String protein;

    private Button scanBtn;
    private Button btnAddFood_calculateDose;
    private TextView formatTxt;
    private TextView contentTxt;

    public String oreo_name = "Double Stuf Oreo";
    public String oreo_servings = "1";
    public String oreo_carb = "21";
    public String oreo_fat = "7";
    public String oreo_prot = "1";
    public String oreo_barcode = "044000029524";

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
        scanBtn = (Button) findViewById(R.id.scan_button);
        btnAddFood_calculateDose = (Button) findViewById(R.id.btnAddFood_calculateDose);
        formatTxt = (TextView) findViewById(R.id.textView);
        //contentTxt = (TextView) findViewById(R.id.contenttxt);


        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.scan_button) {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(AddFood.this);
                    scanIntegrator.initiateScan();
                }

            }
        });

        btnAddFood_calculateDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add food data to calculate dose in dose results


                startActivity(new Intent(AddFood.this, DoseResults.class));
            }
        });


    }

    public void addFood(View view){
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
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //set foodname to edittext
            if (scanContent.equals(oreo_barcode)) {
                foodname.setText(oreo_name, TextView.BufferType.EDITABLE);
                foodservings.setText(oreo_servings, TextView.BufferType.EDITABLE);
                foodcarb.setText(oreo_carb, TextView.BufferType.EDITABLE);
                foodfat.setText(oreo_fat, TextView.BufferType.EDITABLE);
                foodprotein.setText(oreo_prot, TextView.BufferType.EDITABLE);


                //name = oreo_name;
                //servings = oreo_servings;
                //fat = oreo_fat;
                //carb = oreo_carb;
                //protein = oreo_prot;
                //addFoodScanner();
            }


            //formatTxt.setText("FORMAT: " + scanFormat);
            //contentTxt.setText("CONTENT: " + scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
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
