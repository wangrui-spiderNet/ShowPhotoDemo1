package com.example.showphotodemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.showphotodemo.Constants;

/**
 * Created by jn-wr on 2015/8/22.
 */
public class SQliteOpenHelper extends SQLiteOpenHelper {

    public SQliteOpenHelper(Context context) {
        super(context, "wechat.db", null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void initDatabase(SQLiteDatabase db) {
        String sql = null;

         /* {
        "profile-image": "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png",
            "avatar": "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png",
            "nick": "John Smith",
            "username": "jsmith"
        }*/

        // 保存用户信息表
        sql = "CREATE TABLE tb_user (" +
                " user_id INTEGER(10) PRIMARY KEY NOT NULL, " +
                " profile_image VARCHAR(255),"+
                " avatar VARCHAR(255),"+
                " nick VARCHAR(255),"+
                " username VARCHAR(255)"+
                ")";

        db.execSQL(sql);

        sql = "CREATE TABLE tb_moments ("+
                "moment_id INTEGER(10),"+
                "content VARCHAR(255)," +
                "order_id INTEGER(45)"+
                ")";

        db.execSQL(sql);

    }

}
