package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Queue;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_table";
    public static final String COLUMN1="ID";
    public static final String COLUMN2="NAME";
    public static final String COLUMN3="SURNAME";
    public static final String COLUMN4="MARKS";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //language=sql
        String query= "CREATE TABLE "+TABLE_NAME+" ("+COLUMN1+" INTEGER PRIMARY KEY AUTO,"+
                                            COLUMN2+" TEXT,"+
                                            COLUMN3+" TEXT,"+
                                            COLUMN4+" INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //language=sql
        String query= "DROP TABLE IF EXISTS"+TABLE_NAME+";";
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insetData(String name, String surname, int marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2,name);
        contentValues.put(COLUMN3,surname);
        contentValues.put(COLUMN4,marks);
       long results =  db.insert(TABLE_NAME,null, contentValues);
       if(results==-1)
           return false;
       return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result= db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return result;
    }
}
