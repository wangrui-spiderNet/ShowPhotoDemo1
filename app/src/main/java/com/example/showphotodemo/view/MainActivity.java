package com.example.showphotodemo.view;
/**
 * created by wangrui 2015/11/21
 */

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.showphotodemo.Constants;
import com.example.showphotodemo.R;
import com.example.showphotodemo.db.DatabaseDao;
import com.example.showphotodemo.entity.DbMoment;
import com.example.showphotodemo.entity.UserInfo;
import com.example.showphotodemo.entity.WechatMoment;
import com.example.showphotodemo.net.PostRequestCallBack;
import com.example.showphotodemo.view.adapter.MyListAdapter;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView list;

    private View headView;
    private ImageView ivHeadBg;
    private ImageView ivHeadImg;
    private TextView tvName;

    private MyListAdapter mAdapter;
    public static int screenWidth;
    public static int screenHeight;

    private UserInfo userInfo;
    private ArrayList<WechatMoment> wechats;
    private List<DbMoment> dbMoments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_photo);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        wechats = new ArrayList<WechatMoment>();

        list = (ListView) findViewById(R.id.list);

        initHeadView();

        mAdapter = new MyListAdapter(MainActivity.this, wechats, true);
        list.setAdapter(mAdapter);

        userInfo = DatabaseDao.getInstance(getApplicationContext()).getUser();
        dbMoments = DatabaseDao.getInstance(getApplicationContext()).getWetChatMoments(1, 5);

        requestMethod();

        Log.e(Constants.APP_TAG, "数据库返回用户信息:" + DatabaseDao.getInstance(getApplicationContext()).getUser());
        Log.e(Constants.APP_TAG, "数据库返回comments:" + DatabaseDao.getInstance(getApplicationContext()).getAllWetChatMoments());
        Log.e(Constants.APP_TAG, "数据库返回前五条:" + DatabaseDao.getInstance(getApplicationContext()).getWetChatMoments(1, 5));

    }

    private void initHeadView() {
        headView = LayoutInflater.from(this).inflate(R.layout.layout_head_view, null);
        ivHeadBg = (ImageView) headView.findViewById(R.id.iv_bg);
        ivHeadImg = (ImageView) headView.findViewById(R.id.ivHead);
        tvName = (TextView) headView.findViewById(R.id.tv_name);
        list.addHeaderView(headView);

    }

    @Override
    public void onClick(View v) {

    }

    private void requestMethod() {

        if (userInfo == null) {
            appAction.getUser(new PostRequestCallBack() {
                @Override
                public void startPost() {

                }

                @Override
                public void endPost() {

                }

                @Override
                public void success(String respons) {

                    Gson gson = new Gson();
                    userInfo = gson.fromJson(respons, UserInfo.class);

                    ImageLoader.getInstance().displayImage(userInfo.getProfile_image(), ivHeadImg);
                    ImageLoader.getInstance().displayImage(userInfo.getAvatar(), ivHeadBg);
                    tvName.setText(userInfo.getNick());

                    DatabaseDao.getInstance(getApplicationContext()).saveUserInfo(userInfo);

                }

                @Override
                public void localFaild(int errorCode) {

                }

                @Override
                public void serviceFaild(int errorCode, String respons) {

                }
            });
        } else {
            ImageLoader.getInstance().displayImage(userInfo.getProfile_image(), ivHeadImg);
            ImageLoader.getInstance().displayImage(userInfo.getAvatar(), ivHeadBg);
            tvName.setText(userInfo.getNick());
        }


        if (dbMoments == null) {
            appAction.getWeChatMomentList(new PostRequestCallBack() {
                @Override
                public void startPost() {

                }

                @Override
                public void endPost() {

                }

                @Override
                public void success(String respons) {
                    Gson gson = new Gson();
                    try {

                        JSONArray array = new JSONArray(respons);

                        wechats.clear();

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject js = (JSONObject) array.get(i);

                            DbMoment moment = new DbMoment();
                            moment.setOrder(i);
                            moment.setMoment_id(100 + i);
                            moment.setContent(js.toString());

                            DatabaseDao.getInstance(getApplicationContext()).delMoment(moment.getMoment_id());
                            DatabaseDao.getInstance(getApplicationContext()).saveWeChatMoments(moment);

                            WechatMoment wechatMoment = gson.fromJson(js.toString(), WechatMoment.class);

                            wechats.add(wechatMoment);

                        }

                        mAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void localFaild(int errorCode) {

                }

                @Override
                public void serviceFaild(int errorCode, String respons) {

                }
            });
        }else{

            Gson gson = new Gson();
            for(DbMoment moment: dbMoments){
                WechatMoment wechatMoment = gson.fromJson(moment.getContent(), WechatMoment.class);
                wechats.add(wechatMoment);
            }

            mAdapter.notifyDataSetChanged();
        }
    }

}
