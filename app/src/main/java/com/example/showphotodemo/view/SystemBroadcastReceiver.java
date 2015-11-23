package com.example.showphotodemo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.showphotodemo.Constants;

public class SystemBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Constants.BROADCASTKEY.EXIT_USER.equals(action)) {
			//其他用户登录本帐号 操作处理

		}
	}
}
