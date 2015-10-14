package net.androidbootcamp.calcudose;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText etxt_targetBloodGlucose = (EditText)findViewById(R.id.etxt_targetBloodGlucose);
        final EditText etxt_insulinSensitivyFactor = (EditText)findViewById(R.id.etxt_insulinSensitivityFactor);
        final EditText etxt_insulinToCarbRatio = (EditText)findViewById(R.id.etxt_insulinToCarbRatio);

        Button saveValues = (Button)findViewById(R.id.btn_settings_save);
        sharedPreferences = getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);

        saveValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTarget = etxt_targetBloodGlucose.getText().toString();
                String newIsf = etxt_insulinSensitivyFactor.getText().toString();
                String newRatio = etxt_insulinToCarbRatio.getText().toString();

                TextView currentTarget = (TextView)findViewById(R.id.currentTarget);
                TextView currentIsf = (TextView)findViewById(R.id.currentIsf);
                TextView currentRatio = (TextView)findViewById(R.id.currentRatio);

                currentTarget.setText(newTarget);
                currentIsf.setText(newIsf);
                currentRatio.setText(newRatio);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Target, newTarget);
                editor.putString(Isf, newIsf);
                editor.putString(IToCarbRatio, newRatio);
                editor.commit();
                Toast.makeText(Settings.this, "New Values Saved", Toast.LENGTH_LONG).show();
            }
        });
    }

}
