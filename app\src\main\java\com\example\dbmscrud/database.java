package com.example.dbmscrud;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
 private static final String dbname="signup.db";

    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String q="create table users(_id integer primary key autoincrement,name text,username text, password text)";
        db.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public boolean insertData(String name,String username,String password )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("username",username);
        c.put("password",password);
        long r=db.insert("users",null,c);
        return r != -1;
    }

    public Cursor getinfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
//       detail of last line: Cursor cursor=db.rawQuery("select * from users",null);
//             return cursor;
        return db.rawQuery("select * from users",null);
    }

    public boolean updateData(String name,String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("password",password);
        @SuppressLint("Recycle") Cursor cursor =db.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0) {
            long r = db.update("users",c,"username=?",new String[]{username});
            return r != -1;
        }
        else
        return false;
    }
    public boolean deleteData(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //cursor returns row wise data
        @SuppressLint("Recycle") Cursor cursor =db.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0) {
            long r = db.delete("users","username=?",new String[]{username});
            return r != -1;
        }
        else
            return false;
    }

}
