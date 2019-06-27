package com.example.silos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silos.Database.DBHelper;
import com.example.silos.Models.Silo;

import java.util.ArrayList;

public class SiloDAO {
    private SQLiteDatabase db;
    private DBHelper database;

    public static String TABLE = "silos";
    public static String ID = "id";
    public static String GRAIN_ID = "grain_id";
    public static String NAME = "name";
    public static String CAPACITY_TOTAL = "capacityTotal";
    public static String CAPACITY_ATUAL = "capacityAtual";


    public SiloDAO(Context context) {
        database = new DBHelper(context);
    }

    public ArrayList<Silo> list () {
        db = database.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] { ID, NAME, GRAIN_ID, CAPACITY_TOTAL, CAPACITY_ATUAL}, null,null,null,null,null);
        ArrayList<Silo> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Silo silo = new Silo();
                silo.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                silo.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                silo.setGrainId(cursor.getLong(cursor.getColumnIndex(GRAIN_ID)));
                silo.setCapacityTotal(cursor.getString(cursor.getColumnIndex(CAPACITY_TOTAL)));
                silo.setCapacityAtual(cursor.getString(cursor.getColumnIndex(CAPACITY_ATUAL)));
                result.add(silo);
            } while (cursor.moveToNext());
        }

        db.close();
        cursor.close();

        return result;
    }

    public long create (Silo silo) {

        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        values.put(GRAIN_ID, silo.getGrainId());
        values.put(NAME, silo.getName());
        values.put(CAPACITY_TOTAL, silo.getCapacityTotal());
        values.put(CAPACITY_ATUAL, silo.getCapacityAtual());

        long result = db.insert(TABLE, null, values);

        db.close();

        return result;
    }

    public int update (Silo silo) {

        ContentValues values = new ContentValues();
        db = database.getWritableDatabase();

        String where = ID + "=" + silo.getId();

        values.put(GRAIN_ID, silo.getGrainId());
        values.put(NAME, silo.getName());
        values.put(CAPACITY_TOTAL, silo.getCapacityTotal());
        values.put(CAPACITY_ATUAL, silo.getCapacityAtual());


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

    public Silo get (Long id) {
        String where = ID + "=" + id;
        db = database.getReadableDatabase();
        Cursor cursor = db.query(TABLE, new String[] { ID, GRAIN_ID, NAME, CAPACITY_TOTAL, CAPACITY_ATUAL }, where, null,null,null,null,null);

        if (cursor.moveToFirst()) {
            Silo silo = new Silo();
            silo.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            silo.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            silo.setGrainId(cursor.getLong(cursor.getColumnIndex(GRAIN_ID)));
            silo.setCapacityTotal(cursor.getString(cursor.getColumnIndex(CAPACITY_TOTAL)));
            silo.setCapacityAtual(cursor.getString(cursor.getColumnIndex(CAPACITY_ATUAL)));


            db.close();

            return silo;
        }

        db.close();

        return null;
    }

}
