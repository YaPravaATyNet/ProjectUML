package com.example.projectuml;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "targetstore.db";
    private static final int SCHEMA = 1; // версия базы, если решишь что-то изменять, меняй версию на n+1, где n - предыдущая версия базы
    static final String TABLE = "targets";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_DAYS = "days";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_PROGRESS = "progress";
    public static final String COLUMN_STATE = "state";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA); // здесь произойдет магия, потом объясню
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
                + " TEXT, " + COLUMN_TYPE + " TEXT, " + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_HOURS + " INTEGER, " + COLUMN_DAYS + " INTEGER, "
                + COLUMN_START + " TEXT, " + COLUMN_PROGRESS + " INTEGER, "
                + COLUMN_STATE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public void insert(String name, String type, int quantity, int hours, int days, String start, int progress, String state){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_TYPE + ", " + COLUMN_QUANTITY + ", " + COLUMN_HOURS +
                ", " + COLUMN_DAYS + ", " + COLUMN_START + ", " + COLUMN_PROGRESS +
                ", " + COLUMN_STATE + ") VALUES ("+ "'" + name + "', "
                + "'" + type + "', '" + String.valueOf(quantity) +
                "', '" + String.valueOf(hours) + "', '" + String.valueOf(days) +
                "', '" + start + "', '" + String.valueOf(progress) + "', '" + String.valueOf(state) + "');");
        db.close();
    }

    public ArrayList<Target> getTargets() {
        ArrayList<Target> targets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            TargetType type = TargetType.valueOf(cursor.getString(2));
            int quantity = cursor.getInt(3);
            int hours = cursor.getInt(4);;
            int days = cursor.getInt(5);;
            String start = cursor.getString(6);
            int progress = cursor.getInt(7);
            TargetState state = TargetState.valueOf(cursor.getString(8));
            Target target = new Target(id, name, type, quantity, hours, days, start, progress, state);
            targets.add(target);
        }
        cursor.close();
        db.close();
        return  targets;
    }

    public void updateState(int id, String newState) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE + " SET " + COLUMN_STATE + " = '" + newState + "' WHERE " + COLUMN_ID + " = " + id);
        db.close();
    }

}
