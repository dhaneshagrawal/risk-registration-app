package com.example.bananaskin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bananaskin.db";

    public static final String TABLE_NAME = "users";

    public static final String COL_1 = "userID";
    public static final String COL_2 = "label";
    public static final String COL_3 = "isActive";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //This below code can be used for testing the database
       // SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user" +
                "( userID INTEGER PRIMARY KEY AUTOINCREMENT,label text, isActive INTEGER)"
                 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
