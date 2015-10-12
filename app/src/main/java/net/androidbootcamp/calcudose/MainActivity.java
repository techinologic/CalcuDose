package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    double bgResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etxtBG = (EditText)findViewById(R.id.etxtBG);
        Button calculateDoseBtn = (Button) findViewById(R.id.btnCalculate);


        calculateDoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bgResult = Double.parseDouble(etxtBG.getText().toString());


                Intent intent = new Intent(MainActivity.this, DoseResults.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("BG", bgResult);
                intent.putExtras(bundle);
                startActivity(intent);





            }
        });
    }
}
