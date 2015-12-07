package net.androidbootcamp.calcudose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.util.Date;

public class FoodDose extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";
    final double ROUNDOFF = 0.5;
    public String oreo_name = "Double Stuf Oreo";
    public String oreo_servings = "1";
    public String oreo_carb = "21";
    public String oreo_fat = "7";
    public String oreo_prot = "1";
    public String oreo_barcode = "044000029524";
    SQLiteDatabase sqLiteDatabase;
    FoodDbHelper foodDbHelper;
    Cursor cursor;
    int bloodGlucose = -1;
    double carbs = 0;
    double doseResult;
    int insulinSensitivityFactor = 35;
    int insulinCarbRatio = 10;
    int targetBloodSugar = 100;
    EditText etxtBG;
    EditText et_foodname;
    EditText et_fat;
    EditText et_carb;
    EditText et_prot;
    String currentDate;
    Context context = this;
    String add_name;
    String add_servings;
    String add_carb;
    String add_fat;
    String add_protein;


    String name, servings, getCarbs, fat, protein;
    int position;

    int fromWhichActivitiy = 0;
    // 0=from foodDose
    // 1= from foodDataList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_dose);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);

        TextView currentTarget = (TextView) findViewById(R.id.tvCurrentTarget);
        TextView currentIsf = (TextView) findViewById(R.id.tvCurrentIsf);
        TextView currentRatio = (TextView) findViewById(R.id.tvCurrentRatio);
        TextView displayDate = (TextView) findViewById(R.id.displayDate);

        etxtBG = (EditText) findViewById(R.id.etxtBG); //enter blood glucose
        et_foodname = (EditText) findViewById(R.id.et_name);
        et_fat = (EditText) findViewById(R.id.et_fat);
        et_carb = (EditText) findViewById(R.id.et_carbs);
        et_prot = (EditText) findViewById(R.id.et_prot);

        //cursor that will receive database data from listview click.
        foodDbHelper = new FoodDbHelper(getApplicationContext());
        sqLiteDatabase = foodDbHelper.getReadableDatabase();
        cursor = foodDbHelper.getInformation(sqLiteDatabase);

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
                servings = cursor.getString(1);
                getCarbs = cursor.getString(2);
                fat = cursor.getString(3);
                protein = cursor.getString(4);
                position = cursor.getPosition();

            } while (cursor.moveToNext());
        }

        Button calculateDoseBtn = (Button) findViewById(R.id.btnCalculate);
        Button btn_call_emergency_contact = (Button) findViewById(R.id.btn_call_emergency_contact);
        Button btn_show_medical_id = (Button) findViewById(R.id.btn_show_medicalId);
        Button btn_call_911 = (Button) findViewById(R.id.btn_call_911);

        Button scanBtn = (Button) findViewById(R.id.btnScan);
        Button btn_addnew = (Button) findViewById(R.id.btn_addnew);

        currentTarget.setText(settings.getString("Target", "100"));
        currentIsf.setText(settings.getString("Isf", "35"));
        currentRatio.setText(settings.getString("IToCarbRatio", "10"));

        currentDate = DateFormat.getDateTimeInstance().format(new Date());
        displayDate.setText(currentDate);

        //Calculate dose button onclick run code
        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //|| (et_fat != null) || (et_carb != null) || (et_prot != null)) {
                targetBloodSugar = Integer.parseInt(settings.getString("Target", "100"));
                insulinSensitivityFactor = Integer.parseInt(settings.getString("Isf", "35"));
                insulinCarbRatio = Integer.parseInt(settings.getString("IToCarbRatio", "10"));

                if (et_carb.getText().toString().equals("")) {
                    carbs = 0;
                } else {
                    carbs = Double.parseDouble(et_carb.getText().toString());
                }

                if (carbs != 0) {
                    if (etxtBG.getText().toString().equals(null) || etxtBG.getText().toString().equals("")) {
                        Toast.makeText(FoodDose.this, "Please enter a valid blood glucose value",
                                Toast.LENGTH_LONG).show();
                    } else {
                        bloodGlucose = Integer.parseInt(etxtBG.getText().toString());
                        doseResult = Double.parseDouble(etxtBG.getText().toString());
                        doseResult = (doseResult - targetBloodSugar) / insulinSensitivityFactor;

                        if (doseResult < 0) {
                            doseResult = 0;
                        } else {

                            double withCarbs = (carbs / insulinCarbRatio);
                            doseResult = (((double) (long) (doseResult * 20 + ROUNDOFF)) / 20) + withCarbs;
                        }

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("BG", bloodGlucose);  //save BG to SharedPrefs

                        Intent intent = new Intent(FoodDose.this, FoodDoseResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putDouble("BG", doseResult);
                        intent.putExtras(bundle);

                        bundle.putInt("sugar", bloodGlucose);
                        intent.putExtras(bundle);

                        bundle.putInt("target", targetBloodSugar);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        //add addfood method to save food to food lists
                        addFoodToList();
                    }
                }//if edit texts are not null...
                else {
                    //calculate bloodglucose only
                    targetBloodSugar = Integer.parseInt(settings.getString("Target", "100"));
                    insulinSensitivityFactor = Integer.parseInt(settings.getString("Isf", "35"));

                    if (etxtBG.getText().toString().equals(null) || etxtBG.getText().toString().equals("")) {
                        Toast.makeText(FoodDose.this, "Please enter a valid blood glucose value",
                                Toast.LENGTH_LONG).show();
                    } else {
                        bloodGlucose = Integer.parseInt(etxtBG.getText().toString());
                        doseResult = Double.parseDouble(etxtBG.getText().toString());
                        doseResult = (doseResult - targetBloodSugar) / insulinSensitivityFactor;

                        if (doseResult < 0) {
                            doseResult = 0;
                        } else {
                            doseResult = ((double) (long) (doseResult * 20 + ROUNDOFF)) / 20;
                        }

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt("BG", bloodGlucose);  //save BG to SharedPrefs

                        Intent intent = new Intent(FoodDose.this, GlucoseDoseResults.class);
                        Bundle bundle = new Bundle();
                        bundle.putDouble("BG", doseResult);
                        intent.putExtras(bundle);

                        bundle.putInt("sugar", bloodGlucose);
                        intent.putExtras(bundle);

                        bundle.putInt("target", targetBloodSugar);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            }
        });

        btn_call_911.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:911")));
            }
        });

        btn_call_emergency_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:443-540-0310")));
            }
        });

        btn_show_medical_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDose.this, MedicalId.class));
            }
        });

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnScan) {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(FoodDose.this);
                    scanIntegrator.initiateScan();
                }

            }
        });

        btn_addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodDose.this, FoodDataListActivity.class));
            }
        });

    }


    //sets the result of barcode scan and matches it to saved food codes
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //set foodname to edittext
            if (scanContent.equals(oreo_barcode)) {
                et_foodname.setText(oreo_name, TextView.BufferType.EDITABLE);
                //foodservings.setText(oreo_servings, TextView.BufferType.EDITABLE);
                et_carb.setText(oreo_carb, TextView.BufferType.EDITABLE);
                et_fat.setText(oreo_fat, TextView.BufferType.EDITABLE);
                et_prot.setText(oreo_prot, TextView.BufferType.EDITABLE);
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void addFoodToList() {
        add_name = et_foodname.getText().toString();
        //servings = foodservings.getText().toString();
        add_carb = et_carb.getText().toString();
        add_fat = et_fat.getText().toString();
        add_protein = et_prot.getText().toString();

        foodDbHelper = new FoodDbHelper(context);
        sqLiteDatabase = foodDbHelper.getWritableDatabase();
        foodDbHelper.addFood(add_name, servings, add_carb, add_fat, add_protein, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Food Saved", Toast.LENGTH_LONG).show();
        foodDbHelper.close();
    }

}

