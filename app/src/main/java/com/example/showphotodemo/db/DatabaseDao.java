package com.example.showphotodemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.showphotodemo.entity.DbMoment;
import com.example.showphotodemo.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jn-wr on 2015/8/22.
 */
public class DatabaseDao {

    private static SQliteOpenHelper mHelper;
    private static SQLiteDatabase mDatabase;
    private static Cursor mCursor;
    private static Context context;
    private static DatabaseDao commDao;

    public static DatabaseDao getInstance(Context context){
        if(commDao==null){
            return commDao=new DatabaseDao(context);
        }
        return commDao;
    }

    public DatabaseDao(Context context) {
        this.context=context;
        mHelper = new SQliteOpenHelper(context.getApplicationContext());
    }

    /**
     * 保存/更新 用户信息
     * @param moment
     */
    public void saveWeChatMoments(DbMoment moment) {
        mDatabase = mHelper.getWritableDatabase();
        int moment_id = moment.getMoment_id();
        ContentValues cv = new ContentValues();

        cv.put("moment_id", moment.getMoment_id());
        cv.put("content", moment.getContent());
        cv.put("order_id", moment.getOrder());

        mDatabase.insert("tb_moments", null, cv);

        close();
    }


    /**
     * 按顺序查找moments
     *
     * @param
     * @return
     */
    public List<DbMoment> getAllWetChatMoments() {
        mDatabase = mHelper.getWritableDatabase();
        mCursor = mDatabase.rawQuery("SELECT * from tb_moments ",null);

        List<DbMoment> frontList = new ArrayList<DbMoment>();

        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                DbMoment pageBean = new DbMoment();
                pageBean.setMoment_id(mCursor.getInt(mCursor.getColumnIndex("moment_id")));
                pageBean.setContent(mCursor.getString(mCursor.getColumnIndex("content")));
                pageBean.setOrder(mCursor.getInt(mCursor.getColumnIndex("order_id")));
                frontList.add(pageBean);
            } while (mCursor.moveToNext());
        }
        close();
        return frontList;
    }

    /**
     * 按顺序查找moments
     *
     * @param
     * @return
     */
    public List<DbMoment> getWetChatMoments(Integer offset, Integer limit) {
        mDatabase = mHelper.getWritableDatabase();
        mCursor = mDatabase.rawQuery("SELECT * from tb_moments WHERE order_id <= (SELECT " +
                "max(order_id) FROM tb_moments) ORDER BY order_id DESC LIMIT ?, ?", new String[]{(offset * limit) + "", limit.toString()});

        List<DbMoment> frontList = new ArrayList<DbMoment>();

        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                DbMoment pageBean = new DbMoment();
                pageBean.setMoment_id(mCursor.getInt(mCursor.getColumnIndex("moment_id")));
                pageBean.setContent(mCursor.getString(mCursor.getColumnIndex("content")));
                pageBean.setOrder(mCursor.getInt(mCursor.getColumnIndex("order_id")));
                frontList.add(pageBean);
            } while (mCursor.moveToNext());
        }
        close();
        return frontList;
    }


    /**
     * 获得当前登录的微信用户
     *
     * @return
     */
    public UserInfo getUser() {
        UserInfo userInfo = null;
        mDatabase = mHelper.getWritableDatabase();
        mCursor = mDatabase.query("tb_user", null, null, null, null, null, null);
        int count = mCursor.getCount();
        if (count > 0) {
            mCursor.moveToPosition(0);
            userInfo = new UserInfo();

            userInfo.setUser_id(mCursor.getShort(mCursor.getColumnIndex("user_id")));
            userInfo.setUsername(mCursor.getString(mCursor.getColumnIndex("username")));
            userInfo.setAvatar(mCursor.getString(mCursor.getColumnIndex("avatar")));
            userInfo.setProfile_image(mCursor.getString(mCursor.getColumnIndex("profile_image")));
            userInfo.setNick(mCursor.getString(mCursor.getColumnIndex("nick")));

        }
        close();
        return userInfo;
    }


    /**
     * 删除用户
     * @param moment_id
     */
    public void delMoment(int moment_id){
        mDatabase= mHelper.getWritableDatabase();
        mCursor = mDatabase.query("tb_moments", null, "moment_id = " + moment_id, null, null, null, null);
        if(mCursor.getCount()>0){
            mDatabase.execSQL("DELETE FROM tb_moments WHERE moment_id =" + moment_id);
        }
        close();
    }

    /**
     * 删除用户
     * @param user_id
     */
    public void delWeiUserInfo(int user_id){
        mDatabase= mHelper.getWritableDatabase();
        mCursor = mDatabase.query("tb_user", null, "user_id = " + user_id, null, null, null, null);
        if(mCursor.getCount()>0){
            mDatabase.execSQL("DELETE FROM tb_user WHERE user_id =" + user_id);
        }
        close();
    }

    /**
     * 清空用户表
     */
    public void clearUserTab(){
        mDatabase= mHelper.getWritableDatabase();
        mDatabase.execSQL("DELETE FROM tb_user");
        close();
    }

    /**
     * 删除用户
     * @param user_id
     */
    public void delUserInfo(int user_id){
        mDatabase= mHelper.getWritableDatabase();
        mCursor = mDatabase.query("tb_user", null, "user_id = " + user_id, null, null, null, null);
        if(mCursor.getCount()>0){
            mDatabase.execSQL("DELETE FROM tb_user WHERE user_id =" + user_id);
        }
        close();
    }

    /**
     * 保存/更新 用户信息
     * @param user
     */
    public void saveUserInfo(UserInfo user) {
        mDatabase = mHelper.getWritableDatabase();
        int userId = user.getUser_id();
        ContentValues cv = new ContentValues();

        // 保存用户信息表
//         "CREATE TABLE tb_user (" +
//                " user_id INTEGER(10) PRIMARY KEY NOT NULL, " +
//                " profile_image VARCHAR(255),"+
//                " avatar VARCHAR(255),"+
//                " nick VARCHAR(255),"+
//                " username VARCHAR(255)"+
//                ")";

        cv.put("user_id", user.getUser_id());
        cv.put("profile_image", user.getProfile_image());
        cv.put("avatar", user.getAvatar());
        cv.put("nick", user.getNick());
        cv.put("username", user.getUsername());

        mCursor = mDatabase.query("tb_user", null, "user_id = " + userId, null, null, null, null);
        if (mCursor.getCount() > 0) {
            mDatabase.update("tb_user", cv, "user_id = " + userId, null);
        } else {
            mDatabase.insert("tb_user", null, cv);
        }

        close();
    }



    /*
    * 关闭连接
    */
    private static void close() {
        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
    }
}
