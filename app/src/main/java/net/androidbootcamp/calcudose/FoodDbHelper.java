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
    private static final String CREATE_QUERY = "CREATE TABLE "+ FoodContract.NewFoodInfo.TABLE_NAME+
            "("+ FoodContract.NewFoodInfo.FOOD_NAME+" TEXT,"+ FoodContract.NewFoodInfo.FOOD_SERVINGS+
            " TEXT,"+ FoodContract.NewFoodInfo.FOOD_CARB+" TEXT,"
            + FoodContract.NewFoodInfo.FOOD_FAT+" TEXT,"+ FoodContract.NewFoodInfo.FOOD_PROTEIN+" TEXT);";


    public FoodDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "Database created / opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS", "Table created...");
    }

    public void addFood(String name, String servings, String carb, String fat, String protein, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FoodContract.NewFoodInfo.FOOD_NAME, name);
        contentValues.put(FoodContract.NewFoodInfo.FOOD_SERVINGS, servings);
        contentValues.put(FoodContract.NewFoodInfo.FOOD_CARB, carb);
        contentValues.put(FoodContract.NewFoodInfo.FOOD_FAT, fat);
        contentValues.put(FoodContract.NewFoodInfo.FOOD_PROTEIN, protein);

        db.insert(FoodContract.NewFoodInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted...");

    }

    public Cursor getInformation(SQLiteDatabase db){
        Cursor cursor;
            String[] projections = {FoodContract.NewFoodInfo.FOOD_NAME, FoodContract.NewFoodInfo.FOOD_SERVINGS,
                    FoodContract.NewFoodInfo.FOOD_CARB, FoodContract.NewFoodInfo.FOOD_FAT,
                    FoodContract.NewFoodInfo.FOOD_PROTEIN};

        cursor = db.query(FoodContract.NewFoodInfo.TABLE_NAME, projections, null, null, null, null, null);

        return cursor;
    }

    public Cursor getFood(String food_name, SQLiteDatabase sqLiteDatabase){
        String[] projections = {FoodContract.NewFoodInfo.FOOD_NAME, FoodContract.NewFoodInfo.FOOD_CARB,
                FoodContract.NewFoodInfo.FOOD_FAT, FoodContract.NewFoodInfo.FOOD_PROTEIN};
        String selection = FoodContract.NewFoodInfo.FOOD_NAME+ " LIKE ?";
        String[] selection_args = {food_name};
        Cursor cursor = sqLiteDatabase.query(FoodContract.NewFoodInfo.TABLE_NAME, projections,
                selection, selection_args, null, null, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
