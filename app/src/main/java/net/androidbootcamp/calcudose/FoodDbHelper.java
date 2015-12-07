package net.androidbootcamp.calcudose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Paolo T. inocencion on 11/8/2015.
 */
public class FoodDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FOODINFO.DB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_QUERY =
            "CREATE TABLE " + InfoContract.NewInfo.FOOD_TABLE_NAME +
                    "(" + InfoContract.NewInfo.FOOD_NAME +
                    " TEXT," + InfoContract.NewInfo.FOOD_SERVINGS +
                    " TEXT," + InfoContract.NewInfo.FOOD_CARB +
                    " TEXT," + InfoContract.NewInfo.FOOD_FAT +
                    " TEXT," + InfoContract.NewInfo.FOOD_PROTEIN + " TEXT);";

    public FoodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Food Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Food Table created...");
    }

    public void addFood(String name, String servings, String carb, String fat, String protein,
                        SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InfoContract.NewInfo.FOOD_NAME, name);
        contentValues.put(InfoContract.NewInfo.FOOD_SERVINGS, servings);
        contentValues.put(InfoContract.NewInfo.FOOD_CARB, carb);
        contentValues.put(InfoContract.NewInfo.FOOD_FAT, fat);
        contentValues.put(InfoContract.NewInfo.FOOD_PROTEIN, protein);

        db.insert(InfoContract.NewInfo.FOOD_TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One food row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {
                InfoContract.NewInfo.FOOD_NAME,
                InfoContract.NewInfo.FOOD_SERVINGS,
                InfoContract.NewInfo.FOOD_CARB,
                InfoContract.NewInfo.FOOD_FAT,
                InfoContract.NewInfo.FOOD_PROTEIN
        };
        cursor = db.query(InfoContract.NewInfo.FOOD_TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public Cursor getFood(String food_name, SQLiteDatabase sqLiteDatabase) {
        String[] projections = {
                InfoContract.NewInfo.FOOD_NAME,
                InfoContract.NewInfo.FOOD_CARB,
                InfoContract.NewInfo.FOOD_FAT,
                InfoContract.NewInfo.FOOD_PROTEIN
        };
        String selection = InfoContract.NewInfo.FOOD_NAME + " LIKE ?";
        String[] selection_args = {food_name};
        Cursor cursor = sqLiteDatabase.query(InfoContract.NewInfo.FOOD_TABLE_NAME, projections,
                selection, selection_args, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
