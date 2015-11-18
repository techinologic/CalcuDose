package net.androidbootcamp.calcudose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Paolo T. inocencion on 11/15/2015.
 */
public class LogDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "LOGINFO.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_QUERY_LOG_EVENT =
            "CREATE TABLE " + InfoContract.NewInfo.LOG_TABLE_NAME +
                    "(" + InfoContract.NewInfo.FOOD_NAME +
                    " TEXT," + InfoContract.NewInfo.USER_BG +
                    " TEXT," + InfoContract.NewInfo.USER_DOSE +
                    " TEXT," + InfoContract.NewInfo.LOG_CURRENT_TIME +
                    " TEXT," + InfoContract.NewInfo.LOG_DATE + " TEXT);";

    public LogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Log Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY_LOG_EVENT);
        Log.e("DATABASE OPERATIONS", "Log Table created...");
    }

    public void addLogEvent(String name, String bg, String dose, String oras, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InfoContract.NewInfo.FOOD_NAME, name);
        contentValues.put(InfoContract.NewInfo.USER_BG, bg);
        contentValues.put(InfoContract.NewInfo.USER_DOSE, dose);
        contentValues.put(InfoContract.NewInfo.LOG_CURRENT_TIME, oras);

        db.insert(InfoContract.NewInfo.LOG_TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One log row inserted...");
    }

    public Cursor getLogEventInformation(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {
                InfoContract.NewInfo.FOOD_NAME,
                InfoContract.NewInfo.USER_BG,
                InfoContract.NewInfo.USER_DOSE,
                InfoContract.NewInfo.LOG_CURRENT_TIME,
        };
        cursor = db.query(InfoContract.NewInfo.LOG_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
