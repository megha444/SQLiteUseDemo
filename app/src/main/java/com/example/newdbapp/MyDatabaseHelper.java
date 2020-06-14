package com.example.newdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="tasks.db";
    public static final String TABLE_NAME= "tasks";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TASKNAME="taskname";
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String query="Create table "+TABLE_NAME+" ("+COLUMN_ID+" Integer primary key autoincrement, "+COLUMN_TASKNAME+" Text);";
Log.d("DB",query);
db.execSQL(query);


    }

    public void addTask(Tasks tasks)
    {
        ContentValues values= new ContentValues();
        values.put(COLUMN_TASKNAME, tasks.get_taskname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void removeTaks(String taskname)
    {
        SQLiteDatabase db= getWritableDatabase();
        String query = "Delete from "+TABLE_NAME+" where "+COLUMN_TASKNAME+" = '"+taskname+"';";
        Log.d("DB",query);
        db.execSQL(query);
    }

    public String dbToString()
    {
        String dbstring="";
        SQLiteDatabase db= getWritableDatabase();
        String query ="Select * from "+TABLE_NAME+";";
        Log.d("DB",query);
        Cursor c= db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("taskname"))!=null)
            {
                dbstring+=c.getString(c.getColumnIndex("taskname"));
                dbstring+="\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbstring;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists "+TABLE_NAME);
onCreate(db);
    }
}
