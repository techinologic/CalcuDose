package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton imgBtnLogFood = (ImageButton)findViewById(R.id.imgBtnLogFood);
        imgBtnLogFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Correction.class));
            }
        });

    }
}
