package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DoseResults extends AppCompatActivity {
    double resultUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose_results);
        TextView txtvResults = (TextView)findViewById(R.id.txtvResults);
        TextView txtvBG = (TextView)findViewById(R.id.txtvBG);
        TextView txtvTarget = (TextView)findViewById(R.id.txtvTarget);



        Bundle bundle = getIntent().getExtras();
        double result = bundle.getDouble("BG");
        int sugar = bundle.getInt("sugar");
        int target = bundle.getInt("target");

        DecimalFormat units = new DecimalFormat("##,###.##");

        txtvBG.setText("Blood glucose of " + sugar + "mg/dL needs ");
        txtvResults.setText(units.format(result));
        txtvTarget.setText("units of insulin to be on target blood glucose of " + target +" mg/dL.");

        Button logEvent = (Button)findViewById(R.id.btnLog);
        logEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoseResults.this, ViewLogs.class));
            }
        });



    }

}