package com.example.showphotodemo.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.showphotodemo.Constants;
import com.example.showphotodemo.R;
import com.example.showphotodemo.net.core.AppAction;
import com.example.showphotodemo.net.core.AppActionImpl;
import com.example.showphotodemo.util.StringUtil;

/**
 * Created by 94540 on 2015/11/22.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    public AppAction appAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (StringUtil.isEmpty(Constants.SERVER_HOST_ADDRESS)) {
            Constants.SERVER_HOST_ADDRESS = getResources().getString(R.string.server_host);
        }

        appAction = AppActionImpl.getInstance(this);
    }

    @Override
    public void onClick(View v) {

    }
}
