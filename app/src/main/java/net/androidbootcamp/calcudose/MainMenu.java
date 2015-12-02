package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnLogFood = (Button)findViewById(R.id.btnLogFood);
        Button btnLogBG = (Button)findViewById(R.id.btnLogBloodGlucose);
        Button btnLog = (Button)findViewById(R.id.btnViewLogs);
        Button btnSettings = (Button)findViewById(R.id.btnSettings);

        btnLogFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, FoodDose.class));
            }
        });

        btnLogBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, LogBloodGlucose.class));
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, LogDataListActivity.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, Settings.class));
            }
        });

    }

}
