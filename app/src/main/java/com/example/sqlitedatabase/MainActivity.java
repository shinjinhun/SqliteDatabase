package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {
    String dbName, tbName;
    SQLiteDatabase sDB;

    TextView resultTxt;


    Button btnCreateDB, btnCreateTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText dbinputName = (EditText) findViewById(R.id.dbNameET);
        final EditText tbinputName = (EditText) findViewById(R.id.tbNameET);

        Button createDBbtn = (Button) findViewById(R.id.btnCreateDB);

        resultTxt = (TextView) findViewById(R.id.tv01);

        createDBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbName = dbinputName.getText().toString();
                createDB(dbName);
            }


        });

        Button createTBbtn = (Button) findViewById(R.id.btnCreateTB);
        createTBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinputName.getText().toString();
                createTB(tbName);

                int cnt = insertMember(tbName);

                msgOutput("[" + cnt + "] 개의 레코드가 추가되었습니다.");
            }
        });

        Button insertBtn = (Button) findViewById(R.id.btnInsert);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinputName.getText().toString();
                insertMemberParms(tbName);

                msgOutput("레코드가 추가되었습니다.");
            }
        });

        Button updateBtn = (Button) findViewById(R.id.btnUpdate);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinputName.getText().toString();
                updateMemberParms(tbName);

                msgOutput("레코드가 수정되었습니다..");
            }
        });


        Button deleteBtn = (Button) findViewById(R.id.btnDelete);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbName = tbinputName.getText().toString();
                deleteMemberParms(tbName);

                msgOutput("레코드가 삭제되었습니다..");
            }
        });


    }

    private void createDB(String name) {
        msgOutput("[" + name + "] 데이터 베이스 생성 중");

        try {
            sDB = openOrCreateDatabase(name, MODE_PRIVATE, null);
            msgOutput("[" + name + "] 데이터 베이스 생성 완료");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void msgOutput(String msg) {
        Log.d("MainActivity", msg);
        resultTxt.append("\n" + msg);
    }

    private void createTB(String name) {
        msgOutput("[" + name + "] 테이블 생성 중");

        sDB.execSQL("create table if not exists " + name + "("
                + "no integer PRIMARY KEY autoincrement, "
                + "name text, "
                + "address text, "
                + "tel text);" );

        msgOutput("[" + name + "] 테이블 생성 완료");
    }

    private int insertMember(String name) {
        msgOutput("[" + name + "] 테이블 멤버 추가");

        int cnt = 2;

        sDB.execSQL("insert into " + name + " (name, address, tel) values ('홍길동','서울','010-1234-5678');");
        sDB.execSQL("insert into " + name + " (name, address, tel) values ('김말동','대구','011-7846-1931');");

        return cnt;
    }

    private int insertMemberParms(String name) {
        msgOutput("인수를 이용한 레코드 추가하기!");

        int cnt = 1;
        ContentValues data = new ContentValues();

        data.put("name","임꺽정");
        data.put("address","광주");
        data.put("tel","123-123");
        sDB.insert(name, null, data);

        return cnt;
    }

    private int updateMemberParms(String name) {
        msgOutput("레코드 수정하기!");

        ContentValues data = new ContentValues();
        data.put("address", "부산");
        String[] whereArgs = {"임꺽정"};

        int i = sDB.update(name, data, "name=?", whereArgs);

        return i;

    }


    private int deleteMemberParms(String name) {
        msgOutput("레코드 삭제하기!");

        String[] whereArgs = {"임꺽정"};

        int i = sDB.delete(name, "name=?", whereArgs);

        return i;

    }

}
