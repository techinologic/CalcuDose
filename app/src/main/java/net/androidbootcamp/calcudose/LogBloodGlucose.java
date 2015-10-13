package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogBloodGlucose extends AppCompatActivity {
    int bloodGlucose;
    double doseResult;
    int insulinToCarbRatio;
    int insulinSensitivityFactor = 70;
    int targetBloodSugar = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_blood_glucose);

        final EditText etxtBG = (EditText)findViewById(R.id.etxtBG);
        Button calculateDoseBtn = (Button)findViewById(R.id.btnCalculate);

        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bloodGlucose = Integer.parseInt(etxtBG.getText().toString());
                doseResult = Double.parseDouble(etxtBG.getText().toString());
                doseResult = (doseResult - targetBloodSugar) / insulinSensitivityFactor;

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

