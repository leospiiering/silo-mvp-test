package com.example.silos.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.silos.DAO.GrainDAO;
import com.example.silos.DAO.SiloDAO;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "silo_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SiloDAO.TABLE + " ( " + SiloDAO.ID + " integer primary key autoincrement, "  + SiloDAO.NAME + " varchar(100) not null, " + SiloDAO.GRAIN_ID + " varchar(100) not null, " + SiloDAO.CAPACITY_TOTAL + " varchar(100) not null, "+ SiloDAO.CAPACITY_ATUAL + " varchar(100));");
        db.execSQL("create table " + GrainDAO.TABLE + " ( " + GrainDAO.ID + " integer primary key autoincrement, "  + GrainDAO.NAME + " varchar(100 ));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
