package net.androidbootcamp.calcudose;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ViewLogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logs);

        Toast.makeText(ViewLogs.this, "Blood Glucose and Dose Saved", Toast.LENGTH_LONG).show();

    }

}
