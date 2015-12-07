package net.androidbootcamp.calcudose;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class FoodListDoseResult extends AppCompatActivity {

    private double result;
    private int sugar;
    private final Context context = this;
    private String currentDate;
    public String resultInDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose_results);

        TextView txtvResults = (TextView) findViewById(R.id.txtvResults2);
        TextView txtvBG = (TextView) findViewById(R.id.txtvBG2);
        TextView txtvTarget = (TextView) findViewById(R.id.txtvTarget);
        TextView displayDate = (TextView) findViewById(R.id.displayDate);
        Button logEvent = (Button) findViewById(R.id.btnLog);

        currentDate = DateFormat.getDateTimeInstance().format(new Date());
        displayDate.setText(currentDate);

        Bundle bundle = getIntent().getExtras();
        result = bundle.getDouble("BG");
        sugar = bundle.getInt("sugar");
        int target = bundle.getInt("target");

        DecimalFormat units = new DecimalFormat("###.##");

        txtvBG.setText("Recommended dose for " + sugar + " mg/dL blood glucose level is ");
        resultInDouble = units.format(result);

        txtvResults.setText(units.format(result));
        txtvTarget.setText("units of insulin to be on target blood glucose of " + target + " mg/dL.");

        logEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLogEvent();
                startActivity(new Intent(FoodListDoseResult.this, LogDataListActivity.class));
            }
        });
    }

    private void addLogEvent() {
        String name = currentDate;
        String bg = Integer.toString(sugar);
        String dose = resultInDouble;
        String oras = "Food Dose: List";

        LogDbHelper logDbHelper = new LogDbHelper(context);
        SQLiteDatabase sqLiteDatabase = logDbHelper.getWritableDatabase();
        logDbHelper.addLogEvent(name, bg, dose, oras, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Food Dose Log Saved", Toast.LENGTH_LONG).show();
        logDbHelper.close();
    }

}