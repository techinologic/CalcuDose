package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DoseResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose_results);
        TextView txtvResults = (TextView)findViewById(R.id.txtvResults);


        Bundle bundle = getIntent().getExtras();
        double result = bundle.getDouble("BG");
        txtvResults.setText(Double.toString(result));






    }
}
