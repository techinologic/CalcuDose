package net.androidbootcamp.calcudose;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paolo T. inocencion on 11/8/2015.
 */
public class FoodListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public FoodListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /*@Override
    public Object getItem(int position) {
        return list.get(position);
    }*/

    @Override
    public Object getItem(int position) {
        return list.get(getCount() - position - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.food_row_layout, parent, false);

            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView) row.findViewById(R.id.txt_food_name);
            layoutHandler.SERVINGS = (TextView) row.findViewById(R.id.txt_food_servings);
            layoutHandler.CARBS = (TextView) row.findViewById(R.id.txt_food_carbs);
            layoutHandler.FAT = (TextView) row.findViewById(R.id.txt_food_fat);
            layoutHandler.PROTEIN = (TextView) row.findViewById(R.id.txt_food_protein);
            row.setTag(layoutHandler);
        } else {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        FoodDataProvider foodDataProvider = (FoodDataProvider) this.getItem(position);
        layoutHandler.NAME.setText(foodDataProvider.getName());
        layoutHandler.SERVINGS.setText(foodDataProvider.getServings());
        layoutHandler.CARBS.setText(foodDataProvider.getCarbs());
        layoutHandler.FAT.setText(foodDataProvider.getFat());
        layoutHandler.PROTEIN.setText(foodDataProvider.getProtein());

        return row;
    }

    static class LayoutHandler {
        TextView NAME, SERVINGS, CARBS, FAT, PROTEIN;
    }
}
