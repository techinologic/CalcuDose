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
 * Created by Paolo T. inocencion on 11/15/2015.
 */
public class LogListDataAdapter extends ArrayAdapter {
    List list = new ArrayList<>();

    public LogListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LogLayoutHandler{
        TextView NAME, BG, DOSE, ORAS;
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

    @Override
    public Object getItem(int position) {
        return list.get(getCount() - position - 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        LogLayoutHandler logLayoutHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.log_row_layout,parent,false);
            logLayoutHandler = new LogLayoutHandler();
            logLayoutHandler.NAME = (TextView) row.findViewById(R.id.text_log_food_name);
            logLayoutHandler.BG = (TextView) row.findViewById(R.id.text_log_bg);
            logLayoutHandler.DOSE = (TextView) row.findViewById(R.id.text_log_dose);
            logLayoutHandler.ORAS = (TextView) row.findViewById(R.id.text_log_time);
            //logLayoutHandler.DATE = (TextView) row.findViewById(R.id.text_log_date);
            row.setTag(logLayoutHandler);
        }
        else {
            logLayoutHandler = (LogLayoutHandler) row.getTag();
        }

        LogDataProvider logdataProvider = (LogDataProvider) this.getItem(position);
        logLayoutHandler.NAME.setText(logdataProvider.getName());
        logLayoutHandler.BG.setText(logdataProvider.getBg());
        logLayoutHandler.DOSE.setText(logdataProvider.getDose());
        logLayoutHandler.ORAS.setText(logdataProvider.getOras());
        //logLayoutHandler.DATE.setText(logdataProvider.getDate());

        return row;
    }
}
