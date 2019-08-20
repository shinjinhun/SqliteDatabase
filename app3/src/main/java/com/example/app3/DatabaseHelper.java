package com.example.app3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "testDB";

    String sql;
    final static String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate() 호출");
        sql = "CREATE TABLE test(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age TEXT);";
        db.execSQL(sql);

        db.execSQL("insert into test values(null,'홍길북','22');");
        db.execSQL("insert into test values(null,'홍길남','26');");
        db.execSQL("insert into test values(null,'홍길서','27');");
        db.execSQL("insert into test values(null,'홍길동','30');");
        db.execSQL("insert into test values(null,'고길동','33');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS test");
        onCreate(db);
    }


}
