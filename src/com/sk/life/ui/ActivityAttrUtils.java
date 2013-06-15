package com.sk.life.ui;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityAttrUtils
{

	public static void hideStatusBar(Activity activity)
	{
		WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
		attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		activity.getWindow().setAttributes(attrs);
		activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}

	public static void showStatusBar(Activity activity)
	{
		WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
		attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		activity.getWindow().setAttributes(attrs);
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}
}
