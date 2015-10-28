package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Servings extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";
    double servings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servings);

        final EditText etxt_numberofservings = (EditText)findViewById(R.id.etxt_numberofservings);
        final EditText etxtBG = (EditText)findViewById(R.id.etxtBG);


        final TextView foodBeingAdded = (TextView) findViewById(R.id.txtv_FoodBeingAdded);
        final TextView fat = (TextView) findViewById(R.id.nf_fat);
        final TextView carbs = (TextView) findViewById(R.id.nf_carbs);
        final TextView protein = (TextView) findViewById(R.id.nf_protein);
        final TextView servings = (TextView) findViewById(R.id.txtv_servings);


        Button calculateDoseForFood = (Button)findViewById(R.id.btnCalculateDoseFood);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);
        final SharedPreferences.Editor editor = settings.edit();


                foodBeingAdded.setText(settings.getString("Food", "Not set"));
                fat.setText(settings.getString("Fat", "Not set"));
                carbs.setText(settings.getString("Carbs", "Not set"));
                protein.setText(settings.getString("Protein", "Not set"));
                servings.setText(settings.getString("ServingSize", "Not set"));

        calculateDoseForFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editor.putString("Servings", etxt_numberofservings.getText().toString());
                editor.putString("CurrentBG", etxtBG.getText().toString());
                editor.commit();

                startActivity(new Intent(Servings.this, FoodDoseResult.class)); //edit this
            }
        });









    }
}
