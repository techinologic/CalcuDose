package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ListFoodDose extends AppCompatActivity {

    private static final String SETTINGS_PREFERENCES = "Settings";

    private int bloodGlucose = -1;
    private double carbs = 0;
    private double doseResult;
    private int insulinSensitivityFactor = 35;
    private int insulinCarbRatio = 10;
    private int targetBloodSugar = 100;
    private final double ROUNDOFF = 0.5;

    private EditText etxtBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food_dose);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);

        TextView currentTarget = (TextView) findViewById(R.id.tvCurrentTarget);
        TextView currentIsf = (TextView) findViewById(R.id.tvCurrentIsf);
        TextView currentRatio = (TextView) findViewById(R.id.tvCurrentRatio);
        TextView displayDate = (TextView) findViewById(R.id.displayDate);

        TextView foodname = (TextView) findViewById(R.id.tv_dffl_foodname);
        final TextView foodfat = (TextView) findViewById(R.id.tv_dffl_fat);
        final TextView foodcarbs = (TextView) findViewById(R.id.tv_dffl_carbs);
        TextView foodprotein = (TextView) findViewById(R.id.tv_dffl_protein);

        Bundle bundle = getIntent().getExtras();

        foodname.setText(settings.getString("lv_foodname", "foodname"));
        foodfat.setText(settings.getString("lv_foodfat", "foodfat"));
        foodcarbs.setText(settings.getString("lv_foodcarbs", "foodcarbs"));
        foodprotein.setText(settings.getString("lv_foodprotein", "foodprotein"));

        etxtBG = (EditText) findViewById(R.id.etxtBG); //enter blood glucose

        Button calculateDoseBtn = (Button) findViewById(R.id.btnCalculate);
        Button btn_call_emergency_contact = (Button) findViewById(R.id.btn_call_emergency_contact);
        Button btn_show_medical_id = (Button) findViewById(R.id.btn_show_medicalId);
        Button btn_call_911 = (Button) findViewById(R.id.btn_call_911);

        currentTarget.setText(settings.getString("Target", "100"));
        currentIsf.setText(settings.getString("Isf", "35"));
        currentRatio.setText(settings.getString("IToCarbRatio", "10"));

        String currentDate = DateFormat.getDateTimeInstance().format(new Date());
        displayDate.setText(currentDate);

        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                targetBloodSugar = Integer.parseInt(settings.getString("Target", "100"));
                insulinSensitivityFactor = Integer.parseInt(settings.getString("Isf", "35"));
                insulinCarbRatio = Integer.parseInt(settings.getString("IToCarbRatio", "10"));


                //carbs from textview_carb
                carbs = Double.parseDouble(foodcarbs.getText().toString());

                if (etxtBG.getText().toString().equals(null) || etxtBG.getText().toString().equals("")) {
                    Toast.makeText(ListFoodDose.this, "Please enter a valid blood glucose value",
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

                    Intent intent = new Intent(ListFoodDose.this, FoodListDoseResult.class);
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
        });

        //Call buttons emergency and 911 onclick
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
                startActivity(new Intent(ListFoodDose.this, MedicalId.class));
            }
        });


    }
}
