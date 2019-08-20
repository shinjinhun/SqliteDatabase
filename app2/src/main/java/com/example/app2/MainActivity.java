package com.example.app2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    DatabaseHelper databaseHelper;

    Button insertBtn, deleteBtn, updateBtn, selectBtn;
    TextView selectTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("jshin","view");


        databaseHelper = new DatabaseHelper(this);

        insertBtn = findViewById(R.id.btnInsert);
        deleteBtn = findViewById(R.id.btnDelete);
        updateBtn = findViewById(R.id.btnUpdate);
        selectBtn = findViewById(R.id.btnSelect);

        selectTxt = findViewById(R.id.tvSelect);

        insertBtn.setOnClickListener(listener);
        deleteBtn.setOnClickListener(listener);
        updateBtn.setOnClickListener(listener);
        selectBtn.setOnClickListener(listener);

    }


    View.OnClickListener listener = new View.OnClickListener() {
        SQLiteDatabase sDB;
        ContentValues data;

        @Override
        public void onClick(View v) {

            Log.e("jshin","click");

            switch (v.getId()) {
                case R.id.btnInsert:
                    try {
                        sDB = databaseHelper.getWritableDatabase();
                        // data = new ContentValues();
                        // data.put("name","유재석");
                        // data.put("id","test");
                        // data.put("pw","test1234");
                        // sDB.insert("member",null,data);

                        String sql = "INSERT INTO member (_id, name, id, pw) VALUES(null, '유재석','tete','test1234');";
                        sDB.execSQL(sql);

                        String sql1 = "INSERT INTO member (_id, name, id, pw) VALUES(null, '홍길동','hong','hong1234');";
                        sDB.execSQL(sql1);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(),"레코드 추가 완료!!",Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),"레코드 추가 오류!!",Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnDelete:
                    try {
                        sDB = databaseHelper.getWritableDatabase();

                        // sDB.delete("member",null,null); // 조건없이 모든 레코드를 삭제

                        String sql = "DELETE FROM member; ";
                        sDB.execSQL(sql);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(),"삭제 완료!!",Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),"삭제 오류!!",Toast.LENGTH_SHORT).show();
                    }
                    break;


                case R.id.btnUpdate:
                    try {
                        sDB = databaseHelper.getWritableDatabase();

                        // data = new ContentValues();
                        // data.put("name","홍길동");
                        // String[] whereArgs = {"유재석"};
                        // sDB.update("member",data,"name=?",whereArgs);

                        String sql = "UPDATE member SET name = '홍길동' WHERE name = '유재석' ; ";
                        sDB.execSQL(sql);

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(),"업데이트 완료!!",Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),"업데이 오류!!",Toast.LENGTH_SHORT).show();
                    }
                    break;


                case R.id.btnSelect:
                    try {
                        sDB = databaseHelper.getReadableDatabase();

                        String[] fields = {"name","id","pw"};
                        Cursor cursor = sDB.query( "member", fields, null, null, null, null, null);


                        // String sql = "select name, id, pw from member;";
                        // Cursor cursor = sDB.rawQuery(sql, null);

                        StringBuffer sb = new StringBuffer();
                        while (cursor.moveToNext()) {
                            String name = cursor.getString(0);
                            String id = cursor.getString(1);
                            String pw = cursor.getString(2);

                            sb.append("name : " + name).append(", id : " + id).append(", pw : " + pw + "\n");

                        }

                        selectTxt.setText(sb.toString());
                        cursor.close();

                        databaseHelper.close();
                        Toast.makeText(getBaseContext(),"조회 완료!!",Toast.LENGTH_SHORT).show();

                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),"조회 오류!!",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

}
