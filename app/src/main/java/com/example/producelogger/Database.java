package com.example.producelogger;

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

    public void addHarvest(Harvest harvest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Column_Date, harvest.getDate());
        contentValues.put(Column_Item, harvest.getItem());
        contentValues.put(Column_Weight, harvest.getWeight());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList<Harvest> getHarvests() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Harvest> harvests = new ArrayList<>();
        Harvest harvest;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                harvest = new Harvest();
                harvest.setDate(cursor.getString(1));
                harvest.setItem(cursor.getString(2));
                harvest.setWeight(cursor.getString(3));
                harvests.add(harvest);
            }
        }
        cursor.close();
        db.close();
        return harvests;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
