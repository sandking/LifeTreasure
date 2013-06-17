package com.sk.life.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.sk.life.R;

public class Welcome extends Activity
{
	final static int Delay_Time = 2000;

	// Data
	boolean isFirst;

	// Handler
	Handler mHandler = new Handler();

	SharedPreferences sp;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		isFirst = checkFirst();
		setContentView(R.layout.welcome);
		mHandler.postDelayed(new DoNext(), Delay_Time);
	}

	boolean checkFirst()
	{
		if (sp == null) sp = getSharedPreferences("Data", MODE_PRIVATE);
		return sp.getBoolean("isFirst", true);
	}

	void commitFirst()
	{
		if (sp == null) sp = getSharedPreferences("Data", MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putBoolean("isFirst", false);
		edit.commit();
	}

	class DoNext implements Runnable, OnClickListener
	{

		ViewPager mViewPager;
		View[] guide_views;

		@Override
		public void run()
		{
			if (isFirst)
			{
				commitFirst();
				setContentView(R.layout.guide_layout);
				initGuideData();
				mViewPager = (ViewPager) findViewById(R.id.viewpager_guide);
				mViewPager.setAdapter(new TabPageAdapter(guide_views));
			}
			else toMainActivity(false);
		}

		void initGuideData()
		{
			guide_views = new View[3];

			ImageView guide_1 = new ImageView(Welcome.this);
			ImageView guide_2 = new ImageView(Welcome.this);
			ImageView guide_3 = new ImageView(Welcome.this);

			guide_1.setBackgroundResource(R.drawable.guide_2);
			guide_2.setBackgroundResource(R.drawable.guide_3);
			guide_3.setBackgroundResource(R.drawable.guide_4);

			guide_3.setOnClickListener(this);

			guide_views[0] = guide_1;
			guide_views[1] = guide_2;
			guide_views[2] = guide_3;
		}

		void toMainActivity(boolean animFlag)
		{
			Intent intent = new Intent();
			intent.setClass(Welcome.this, MainActivity.class);
			startActivity(intent);
			if (animFlag)
			{
				overridePendingTransition(R.anim.search_in, 0);
				mHandler.postDelayed(new Runnable()
				{
					@Override
					public void run()
					{
						finish();
					}
				}, 1000);
			}
			else finish();
		}

		@Override
		public void onClick(View v)
		{
			toMainActivity(true);
		}
	}

	class TabPageAdapter extends PagerAdapter
	{

		View[] mViews;

		TabPageAdapter(View[] views)
		{
			mViews = views;
		}

		@Override
		public int getCount()
		{
			return mViews.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object)
		{
			((ViewPager) container).removeView(mViews[position]);
		}

		@Override
		public Object instantiateItem(View container, int position)
		{
			View view = mViews[position];
			((ViewPager) container).addView(view);
			return view;
		}
	}

	class TabPageChangedListener implements OnPageChangeListener
	{

		@Override
		public void onPageSelected(int arg0)
		{
		}

		@Override
		public void onPageScrollStateChanged(int arg0)
		{
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2)
		{
		}
	}
}