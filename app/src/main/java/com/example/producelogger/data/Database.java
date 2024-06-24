package com.example.producelogger.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProduceHarvestLog.db";

    public static final String TABLE_NAME = "Harvests";
    public static final String Column_Date = "Date";
    public static final String Column_Item = "Item";
    public static final String Column_Weight = "Weight";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + Column_Date + " VARSTRING, "
                + Column_Item + " VARSTRING, " + Column_Weight + " VARSTRING);");
    }

    /**
     * Adds a new Harvest to the Database
     *
     * @param harvest The Harvest to be added to the Database
     */
    public void addHarvest(Harvest harvest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Column_Date, harvest.getDate());
        contentValues.put(Column_Item, harvest.getItem());
        contentValues.put(Column_Weight, harvest.getWeight());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * Drops the current Database, then creates a new Database and adds all the Harvests in
     * harvests to it
     *
     * @param harvests The ArrayList of Harvests to be added
     */
    public void updateHarvests(ArrayList<Harvest> harvests) {
//        Log.e("DatabaseUpdate", harvests.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("create table " + TABLE_NAME + " ( " + Column_Date + " VARSTRING, "
                + Column_Item + " VARSTRING, " + Column_Weight + " VARSTRING);");
        for (Harvest harvest : harvests) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(Column_Date, harvest.getDate());
            contentValues.put(Column_Item, harvest.getItem());
            contentValues.put(Column_Weight, harvest.getWeight());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    /***
     * Gets an ArrayList of all Harvests recorded in the Database
     *
     * @return An ArrayList containing all the Harvests recorded in the Database
     */
    public ArrayList<Harvest> getHarvests() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Harvest> harvests = new ArrayList<>();
        Harvest harvest;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
//                cursor.getColumnCount();
                harvest = new Harvest();
                harvest.setDate(cursor.getString(0));
                harvest.setItem(cursor.getString(1));
                harvest.setWeight(cursor.getString(2));
                harvests.add(harvest);
            }
        }
        cursor.close();
        db.close();
//        Log.e("DatabaseGet", harvests.toString());
        return harvests;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
