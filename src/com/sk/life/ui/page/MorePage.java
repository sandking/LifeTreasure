package com.sk.life.ui.page;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.sk.life.R;

public class MorePage extends BasePage implements OnClickListener
{
	public final static String TAG = MorePage.class.getSimpleName();

	private View item_ImageCache, item_Push, item_Clear, item_Help, item_Update, item_About, item_Suggest;


	@Override
	public void onCreate(Context context)
	{
		super.onCreate(context);
		Log.e(TAG, String.format("onCreate", context));
		initControls();
	}

	void initControls()
	{
		item_ImageCache = findViewById(R.id.img_btn_more_piccache);
		item_Push = findViewById(R.id.img_btn_more_push);
		item_Clear = findViewById(R.id.rel_btn_more_clear);
		item_Help = findViewById(R.id.rel_btn_more_help);
		item_Update = findViewById(R.id.rel_btn_more_update);
		item_About = findViewById(R.id.rel_btn_more_about);
		item_Suggest = findViewById(R.id.rel_btn_more_suggest);

		item_ImageCache.setOnClickListener(this);
		item_Push.setOnClickListener(this);
		item_Clear.setOnClickListener(this);
		item_Help.setOnClickListener(this);
		item_Update.setOnClickListener(this);
		item_About.setOnClickListener(this);
		item_Suggest.setOnClickListener(this);
	}

	@Override
	public void onDestroy(Context context)
	{
		super.onDestroy(context);
	}

	boolean flag_picCache;
	boolean flag_Push;

	@Override
	public void onClick(View v)
	{
		Log.e(TAG, String.format("onClick %d", v.getId()));
		switch (v.getId())
		{
		case R.id.img_btn_more_piccache:
			v.setBackgroundResource(flag_picCache ? R.drawable.icon_more_has_pic : R.drawable.icon_more_no_pick);
			flag_picCache = !flag_picCache;
			break;
		case R.id.img_btn_more_push:
			v.setBackgroundResource(flag_Push ? R.drawable.icon_more_on : R.drawable.icon_more_off);
			flag_Push = !flag_Push;
			break;
		case R.id.rel_btn_more_clear:
			break;
		case R.id.rel_btn_more_help:
			break;
		case R.id.rel_btn_more_update:
			break;
		case R.id.rel_btn_more_about:
			break;
		case R.id.rel_btn_more_suggest:
			break;
		}
	}
}
