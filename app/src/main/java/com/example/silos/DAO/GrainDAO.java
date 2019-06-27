package com.example.silos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silos.Database.DBHelper;
import com.example.silos.Models.Grain;
import com.example.silos.Models.Silo;

import java.util.ArrayList;

public class GrainDAO {
    private DBHelper database;
    private SQLiteDatabase db;

    public static String TABLE = "grains";
    public static String NAME = "name";
    public static String ID = "id";

    public GrainDAO (Context context) {
        database = new DBHelper(context);
    }

    public ArrayList<Grain> list () {
        db = database.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] { ID, NAME }, null,null,null,null,null);
        ArrayList<Grain> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Grain grain = new Grain();
                grain.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                grain.setName(cursor.getString(cursor.getColumnIndex(NAME)));

                result.add(grain);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public long create (Grain grain) {
        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        values.put(NAME, grain.getName());

        long result = db.insert(TABLE, null, values);

        db.close();

        if (result == -1)
            return -1;
        else
            return result;
    }

    public int update (Grain grain) {
        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        String where = ID + "=" + grain.getId();

        values.put(NAME, grain.getName());

        int response = db.update(TABLE, values, where,null);
        db.close();

        return response;
    }

    public Boolean remove (Long id) {
        db = database.getWritableDatabase();
        String where = ID + "=" + id;

        Boolean response = db.delete(TABLE, where, null) > 0;
        db.close();

        return response;
    }

    public Grain get (Long id) {

        String where = ID + "=" + id;
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE,new String[] {ID, NAME}, where,null,null,null,null);

        if(cursor.moveToFirst()) {
            Grain grain = new Grain();
            grain.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            grain.setName(cursor.getString(cursor.getColumnIndex(NAME)));

            db.close();

            return grain;
        }

        db.close();

        return null;
    }

}
