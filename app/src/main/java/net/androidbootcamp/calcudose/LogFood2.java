package net.androidbootcamp.calcudose;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LogFood2 extends ListActivity {
    public static final String SETTINGS_PREFERENCES = "Settings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_log_food2);

        String[] foodArray = {"Subway Italian B.M.T", "Medium Banana", "Red Lobster Admiral\'s Feast", "Cheesecake Factory White Chocolate Caramel Macadamia Nut Cheesecake"};
        setListAdapter (new ArrayAdapter<>(this, R.layout.activity_log_food2, R.id.coffee, foodArray));



    }
    protected void onListItemClick(ListView l, View v, int position, long id){
        final SharedPreferences settings = getSharedPreferences(SETTINGS_PREFERENCES, 0);
        final SharedPreferences.Editor editor = settings.edit();

        switch (position){
            case 0:
                editor.putString("Fat", "16");
                editor.putString("Carbs", "46");
                editor.putString("Protein", "20");
                editor.putString("Food", "Subway Italian B.M.T");
                editor.putString("ServingSize", "6 in. Sandwich");
                editor.commit();

                startActivity(new Intent(LogFood2.this, Servings.class));

                break;
            case 1:
                editor.putString("Fat", "0.4");
                editor.putString("Carbs", "27");
                editor.putString("Protein", "1.3");
                editor.putString("Food", "Banana");
                editor.putString("ServingSize", "medium sized banana");

                editor.commit();
                startActivity(new Intent(LogFood2.this, Servings.class));
                break;
            case 2:
                editor.putString("Fat", "62");
                editor.putString("Carbs", "97");
                editor.putString("Protein", "64");
                editor.putString("Food", "Red Lobster Admiral's Feast");
                editor.putString("ServingSize", "whole meal");

                editor.commit();
                startActivity(new Intent(LogFood2.this, Servings.class));
                break;
            case 3:
                editor.putString("Fat", "42");
                editor.putString("Carbs", "114");
                editor.putString("Protein", "0");
                editor.putString("Food", "Cheesecake Factory White Chocolate Caramel Macadamia Nut Cheesecake");
                editor.putString("ServingSize", "1 slice");

                editor.commit();
                startActivity(new Intent(LogFood2.this, Servings.class));
                break;

        }
    }

}
