package net.androidbootcamp.calcudose;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    public static final String SETTINGS_PREFERENCES = "Settings";
    public static final String Target = "targetKey";
    public static final String Isf = "IsfKey";
    public static final String IToCarbRatio = "ratioKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText etxt_targetBloodGlucose = (EditText)findViewById(R.id.etxt_targetBloodGlucose);
        final EditText etxt_insulinSensitivyFactor = (EditText)findViewById(R.id.etxt_insulinSensitivityFactor);
        final EditText etxt_insulinToCarbRatio = (EditText)findViewById(R.id.etxt_insulinToCarbRatio);

        Button saveValues = (Button)findViewById(R.id.btn_settings_save);

        final TextView currentTarget = (TextView)findViewById(R.id.currentTarget);
        final TextView currentIsf = (TextView)findViewById(R.id.currentIsf);
        final TextView currentRatio = (TextView)findViewById(R.id.currentRatio);

        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);
        final SharedPreferences.Editor editor = settings.edit();

        currentTarget.setText(settings.getString("Target", "Not set"));
        currentIsf.setText(settings.getString("Isf", "Not set"));
        currentRatio.setText(settings.getString("IToCarbRatio", "Not set"));


        saveValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTarget = etxt_targetBloodGlucose.getText().toString();
                String newIsf = etxt_insulinSensitivyFactor.getText().toString();
                String newRatio = etxt_insulinToCarbRatio.getText().toString();


                editor.putString("Target", etxt_targetBloodGlucose.getText().toString());
                editor.putString("Isf", etxt_insulinSensitivyFactor.getText().toString());
                editor.putString("IToCarbRatio", etxt_insulinToCarbRatio.getText().toString());
                editor.commit();

                currentTarget.setText(settings.getString("Target", "Not set"));
                currentIsf.setText(settings.getString("Isf", "Not set"));
                currentRatio.setText(settings.getString("IToCarbRatio", "Not set"));

                Toast.makeText(Settings.this, "New Values Saved", Toast.LENGTH_LONG).show();
            }
        });
    }

}
