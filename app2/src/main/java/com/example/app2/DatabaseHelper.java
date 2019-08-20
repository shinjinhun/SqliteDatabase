package com.example.app2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, "Member.db", null, 1); // database 가 생성됨
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate() 호출");
        String sql = "CREATE TABLE member(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, id TEXT, pw TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"onUpgrade() 호출");
        String sql = "DROP TABLE IF EXISTS member";
        db.execSQL(sql);
        onCreate(db);
    }
}
