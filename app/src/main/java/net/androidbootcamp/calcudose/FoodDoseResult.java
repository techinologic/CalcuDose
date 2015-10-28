package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FoodDoseResult extends AppCompatActivity {
    double numberOfServings;
    public static final String SETTINGS_PREFERENCES = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_dose_result);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);
        final SharedPreferences.Editor editor = settings.edit();

        TextView txtvResults = (TextView)findViewById(R.id.txtvResults2);
        TextView txtvBG = (TextView)findViewById(R.id.txtvBG2);
        TextView txtvTarget = (TextView)findViewById(R.id.txtvTarget2);

        //txtvBG.setText(settings.getString("CurrentBG", "Not set"));
        String sugar = settings.getString("CurrentBG", "Not set");
        //fat.setText(settings.getString("Fat", "Not set"));
        //carbs.setText(settings.getString("Carbs", "Not set"));
        //protein.setText(settings.getString("Protein", "Not set"));
        //servings.setText(settings.getString("ServingSize", "Not set"));

        String carbs = settings.getString("Carbs", "Not set");
        String isf = settings.getString("Isf", "Not set");
        String target = settings.getString("Target", "Not set");
        String ratio = settings.getString("IToCarbRatio", "Not set");
        String servings = settings.getString("Servings", "Not set");
        double foodDose = (Double.parseDouble(carbs)*Double.parseDouble(servings)) / Double.parseDouble(ratio);

        double result = (Double.parseDouble(sugar) - Double.parseDouble(target)) / Double.parseDouble(isf) + foodDose;

        DecimalFormat units = new DecimalFormat("###.##");

        txtvBG.setText("Recommended dose for " + sugar + " mg/dL blood glucose level is ");
        txtvResults.setText(units.format(result));
        txtvTarget.setText("units of insulin to be on target blood glucose of " + target +" mg/dL.");










    }
}
