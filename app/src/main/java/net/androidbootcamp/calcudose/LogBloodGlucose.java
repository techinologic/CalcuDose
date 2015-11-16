package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogBloodGlucose extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";

    int bloodGlucose = -1;
    double doseResult;
    int insulinSensitivityFactor;
    int targetBloodSugar;
    final double ROUNDOFF = 0.5;
    EditText etxtBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_blood_glucose);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);

        TextView currentTarget = (TextView)findViewById(R.id.tvCurrentTarget);
        TextView currentIsf = (TextView)findViewById(R.id.tvCurrentIsf);
        TextView currentRatio = (TextView)findViewById(R.id.tvCurrentRatio);

        etxtBG = (EditText)findViewById(R.id.etxtBG); //enter blood glucose
        Button calculateDoseBtn = (Button)findViewById(R.id.btnCalculate);

        currentTarget.setText(settings.getString("Target", "Not set"));
        currentIsf.setText(settings.getString("Isf", "Not set"));
        currentRatio.setText(settings.getString("IToCarbRatio", "Not set"));

        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                targetBloodSugar = Integer.parseInt(settings.getString("Target", "Not set"));
                insulinSensitivityFactor = Integer.parseInt(settings.getString("Isf", "Not set"));

                if (etxtBG.getText().toString().equals(null) || etxtBG.getText().toString().equals("")) {
                    Toast.makeText(LogBloodGlucose.this, "Please enter a valid blood glucose value",
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

                    Intent intent = new Intent(LogBloodGlucose.this, DoseResults.class);
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

    }

}

