package com.sk.life.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.sk.life.R;
import com.sk.life.ui.page.PageFactory;

public class MainActivity extends Activity
{
	public final static String TAG = MainActivity.class.getSimpleName();

	// the time ab move aniamtion.
	final static long DURATION_TAB_ANIAMTION = 150;

	// Controls
	Button mTab_Search, mTab_Member, mTab_Center, mTab_More;
	ImageView mTab_Selected;
	ViewPager mTab_Page;

	// data
	int mCurrIndex;

	int[] imgs_tab_normal = new int[] { R.drawable.tab_search_normal, R.drawable.tab_member_card_normal, R.drawable.tab_mycenter_normal, R.drawable.tab_more_normal };
	int[] imgs_tab_selected = new int[] { R.drawable.tab_search_selected, R.drawable.tab_member_card_selected, R.drawable.tab_mycenter_selected, R.drawable.tab_more_selected };
	int[] data_position = null;
	Button[] btns_tab = null;
	View[] views_page = null;

	PageFactory mPageFactory;

	public View getCurrentView()
	{
		return views_page[mCurrIndex];
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_life);

		mPageFactory = PageFactory.getInstance(this);

		initControls();
		initData();
		setAttribute();

		mPageFactory.createPage(mCurrIndex);
	}

	void initControls()
	{
		mTab_Page = (ViewPager) findViewById(R.id.tab_main_page);

		mTab_Selected = (ImageView) findViewById(R.id.img_main_tab_onselected);

		mTab_Search = (Button) findViewById(R.id.btn_main_search);
		mTab_Member = (Button) findViewById(R.id.btn_main_member);
		mTab_Center = (Button) findViewById(R.id.btn_main_mycenter);
		mTab_More = (Button) findViewById(R.id.btn_main_more);

		btns_tab = new Button[4];
		btns_tab[0] = mTab_Search;
		btns_tab[1] = mTab_Member;
		btns_tab[2] = mTab_Center;
		btns_tab[3] = mTab_More;
	}

	void initData()
	{
		Display mDisplay = getWindowManager().getDefaultDisplay();
		LayoutInflater inflater = LayoutInflater.from(this);
		int displayWidth = mDisplay.getWidth();

		data_position = new int[4];
		data_position[0] = 0;
		data_position[1] = displayWidth / 4;
		data_position[2] = data_position[1] * 2;
		data_position[3] = data_position[1] * 3;

		views_page = new View[4];
		views_page[0] = inflater.inflate(R.layout.main_tab_search, null);
		views_page[1] = inflater.inflate(R.layout.main_tab_member, null);
		views_page[2] = inflater.inflate(R.layout.main_tab_center, null);
		views_page[3] = inflater.inflate(R.layout.main_tab_more, null);

	}

	void setAttribute()
	{
		for (int i = 0; i < 4; i++)
		{
			btns_tab[i].setOnClickListener(new TabClickListener(i));
		}

		mTab_Page.setAdapter(new TabPageAdapter());
		mTab_Page.setOnPageChangeListener(new TabPageChangedListener());
	}

	class TabClickListener implements OnClickListener
	{
		private int index = 0;

		public TabClickListener(int i)
		{
			this.index = i;
		}

		@Override
		public void onClick(View v)
		{
			mTab_Page.setCurrentItem(index);
		}
	}

	class TabPageAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			return views_page.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object)
		{
			Log.e(TAG, String.format("destroyItem -->  position : %d ", position));
			((ViewPager) container).removeView(views_page[position]);
			mPageFactory.destroyPage(position);
		}

		@Override
		public Object instantiateItem(View container, int position)
		{
			View view = views_page[position];
			((ViewPager) container).addView(view);
			return view;
		}
	}

	class TabPageChangedListener implements OnPageChangeListener
	{

		void onSelected(int index)
		{
			int count = btns_tab.length;
			for (int i = 0; i < count; i++)
			{
				btns_tab[i].setBackgroundResource(index == i ? imgs_tab_selected[i] : imgs_tab_normal[i]);
			}
		}

		Animation obtainAnimation(int index)
		{
			return new TranslateAnimation(data_position[mCurrIndex], data_position[index], 0, 0);
		}

		@Override
		public void onPageSelected(int arg0)
		{
			Log.e(TAG, String.format("onPageSelected -->  arg0 : %d ", arg0));
			Animation moveAnimation = obtainAnimation(arg0);
			onSelected(arg0);
			mCurrIndex = arg0;
			moveAnimation.setFillAfter(true);
			moveAnimation.setDuration(DURATION_TAB_ANIAMTION);
			mTab_Selected.startAnimation(moveAnimation);
			mPageFactory.createPage(arg0);
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
