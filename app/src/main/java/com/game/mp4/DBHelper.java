package com.game.mp4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.game.mp4.bean.Video;
import com.game.mp4.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper helper = new DBHelper();

    public static DBHelper getHelper() {
        return helper;
    }

    private DBHelper() {
        super(App.app, "car.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists user (" +
                "id integer primary key autoincrement," +
                "full_name text," +
                "username text," +
                "password text)");
        sqLiteDatabase.execSQL("create table if not exists video (" +
                "id integer primary key autoincrement," +
                "userid integer," +
                "link integer" +
                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean signUp(String fullName, String username, String password) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from user where username=?", new String[]{username});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cursor.close();
                return false;
            }
        }
        writableDatabase.execSQL("insert into user(full_name,username,password) values(?,?,?)",
                new Object[]{fullName, username, password});
        return true;
    }

    @SuppressLint("Range")
    public boolean login(String username, String password) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from user where username=?", new String[]{username});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                if (getAnString(cursor, "password").equals(password)) {
                    User user = new User();
                    user.id = getAnInt(cursor, "id");
                    user.username = getAnString(cursor, "username");
                    user.fullName = getAnString(cursor, "full_name");
                    user.password = getAnString(cursor, "password");
                    App.login(user);
                    return true;
                }

            }
        }
        return false;
    }

    @SuppressLint("Range")
    private String getAnString(Cursor cursor, String index) {
        return cursor.getString(cursor.getColumnIndex(index));
    }

    @SuppressLint("Range")
    private int getAnInt(Cursor cursor, String index) {
        return cursor.getInt(cursor.getColumnIndex(index));
    }

    public void addVideo(String videoLink) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", App.user.id);
        contentValues.put("link",videoLink);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.insert("video", null, contentValues);
    }



    public List<Video> queryMyVideo(int id) {
        ArrayList<Video> videos = new ArrayList<>();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from video where userid = ?", new String[]{id + ""});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Video order = new Video();
                order.id = getAnInt(cursor, "id");
                order.userid = getAnInt(cursor, "userid");
                order.link = getAnString(cursor, "link");
                videos.add(order);
            }
        }
        return videos;
    }
}
