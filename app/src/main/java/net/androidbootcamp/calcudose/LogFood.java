package net.androidbootcamp.calcudose;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LogFood extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_food);

        Button btnAddNewFood = (Button) findViewById(R.id.btnAddNewFood);
        btnAddNewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogFood.this, AddFood.class));
            }
        });

        Button btnMyFood = (Button) findViewById(R.id.btnFoodMyFoods);
        btnMyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogFood.this, LogFood2.class));
            }
        });

        Button btnSavedFoods = (Button)findViewById(R.id.btn_savedFoods);
        btnSavedFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogFood.this, FoodDataListActivity.class));
            }
        });


        Button btnSearchFood = (Button)findViewById(R.id.btn_searchFood);
        btnSearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogFood.this, SearchFood.class));
            }
        });



    }

}
