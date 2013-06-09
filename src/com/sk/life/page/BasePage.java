package com.sk.life.page;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;

import com.sk.life.MainActivity;

public class BasePage implements PageCondition
{
	protected Context mContext;
	protected MainActivity mainActivity;
	protected View mCurrentView;

	@Override
	public void onCreate(Context context)
	{
		mContext = context;

		mainActivity = (MainActivity) context;

		mCurrentView = mainActivity.getCurrentView();
	}

	protected View findViewById(int id)
	{
		return mCurrentView.findViewById(id);
	}

	public Resources getResources()
	{
		return mContext.getResources();
	}

	public String getString(int id)
	{
		return mContext.getString(id);
	}

	public String[] getStringArray(int id)
	{
		return mContext.getResources().getStringArray(id);
	}

	public int[] getIntArray(int id)
	{
		return mContext.getResources().getIntArray(id);
	}

	public LayoutInflater getLayoutInflater()
	{
		return mainActivity.getLayoutInflater();
	}

	@Override
	public void onDestroy(Context context)
	{

	}

}
