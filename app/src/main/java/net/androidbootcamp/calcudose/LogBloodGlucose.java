package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

public class LogBloodGlucose extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";

    int bloodGlucose = -1;
    double doseResult;
    int insulinSensitivityFactor;
    int targetBloodSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_blood_glucose);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);

        TextView currentTarget = (TextView)findViewById(R.id.tvCurrentTarget);
        TextView currentIsf = (TextView)findViewById(R.id.tvCurrentIsf);
        TextView currentRatio = (TextView)findViewById(R.id.tvCurrentRatio);

        final EditText etxtBG = (EditText)findViewById(R.id.etxtBG); //enter blood glucose
        Button calculateDoseBtn = (Button)findViewById(R.id.btnCalculate);

        currentTarget.setText(settings.getString("Target", "Not set"));
        currentIsf.setText(settings.getString("Isf", "Not set"));
        currentRatio.setText(settings.getString("IToCarbRatio", "Not set"));

        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                targetBloodSugar = Integer.parseInt(settings.getString("Target", "Not set"));
                insulinSensitivityFactor = Integer.parseInt(settings.getString("Isf", "Not set"));
                bloodGlucose = Integer.parseInt(etxtBG.getText().toString());

                doseResult = Double.parseDouble(etxtBG.getText().toString());
                doseResult = (doseResult - targetBloodSugar) / insulinSensitivityFactor;



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
        });
    }


}

