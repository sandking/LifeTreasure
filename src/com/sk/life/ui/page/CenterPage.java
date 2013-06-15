package com.sk.life.ui.page;

import android.content.Context;
import android.content.Intent;

import com.sk.life.R;
import com.sk.life.ui.center.LoginActivity;

public class CenterPage extends BasePage
{
	@Override
	public void onCreate(Context context)
	{
		super.onCreate(context);
		Intent intent = new Intent();
		intent.setClass(context, LoginActivity.class);
		startActivity(intent, R.anim.search_in, 0);
	}
}
