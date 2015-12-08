package com.example.prj2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity  extends AppCompatActivity {

    ListView listView;
    Button button;
    EditText edit;

    DBHelper dbHelper;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        dbHelper = new DBHelper(this);

        setList();

    }

    public void setList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("tasks", null, null, null, null, null, null);
        String[] arr  = {"text", "completed"};
        int[]    arr2 = {R.id.itemText, R.id.itemCheckbox};
        adapter = new MyAdapter(this, R.layout.item, cursor, arr, arr2, 0, dbHelper);
        listView.setAdapter(adapter);
        db.close();
    }

    public void addTask(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("text", edit.getText().toString());
        cv.put("completed", 0);
        db.insert("tasks", null, cv);

        edit.setText("");
        db.close();

        setList();
    }
}
