package com.example.prj2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

class MyAdapter extends SimpleCursorAdapter {
    Cursor cursor;
    Context context;
    String[] from;
    int[] to;
    DBHelper dbHelper;

    public MyAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, DBHelper dbHelper) {
        super(context, layout, c, from, to, flags);
        this.context = context;
        this.cursor = c;
        this.from = from;
        this.to = to;
        this.dbHelper = dbHelper;
    }

    class ViewTemp implements View.OnClickListener {
        int pos;
        TextView textView;
        CheckBox checkBox;

        @Override
        public void onClick(View v) {
            int flag = 0;
            if (checkBox.isChecked()) {
                flag = 1;
            }
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("text", textView.getText().toString());
            cv.put("completed", flag);
            db.update("tasks", cv, "_id = ?", new String[]{pos + ""});
            db.close();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewTemp viewTemp;
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item, null, true);
            viewTemp = new ViewTemp();
            viewTemp.textView = (TextView) itemView.findViewById(R.id.itemText);
            viewTemp.checkBox = (CheckBox) itemView.findViewById(R.id.itemCheckbox);
            itemView.setTag(viewTemp);
        } else {
            viewTemp = (ViewTemp) itemView.getTag();
        }
        if (cursor.moveToPosition(position)) {
            viewTemp.textView.setText(cursor.getString(cursor.getColumnIndex("text")) + "");
            if (cursor.getInt(cursor.getColumnIndex("completed")) > 0) {
                viewTemp.checkBox.setChecked(true);
            }
            viewTemp.pos = cursor.getInt(cursor.getColumnIndex("_id"));
            viewTemp.checkBox.setOnClickListener(viewTemp);
        }
        return itemView;
    }
}