package com.sk.life.page;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.life.R;

public class SearchPage extends BasePage
{

	GridView mGridView;

	String[] arrays_separates;
	String[] arrays_business;

	@Override
	public void onCreate(Context context)
	{
		super.onCreate(context);

		arrays_separates = getStringArray(R.array.arrays_search_separates);
		arrays_business = getStringArray(R.array.arrays_search_business);
	}

	void initControls()
	{
		mGridView = (GridView) findViewById(R.id.grid_search_content);
	}

	@Override
	public void onDestroy(Context context)
	{
		super.onDestroy(context);
	}

	class GridAdapter extends BaseAdapter
	{

		private int index;

		public GridAdapter(int index)
		{
			this.index = index;
		}

		@Override
		public int getCount()
		{
			return index == 0 ? arrays_separates.length : arrays_business.length;
		}

		@Override
		public Object getItem(int position)
		{
			return index == 0 ? arrays_separates[position] : arrays_business[position];
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			
			return null;
		}

		class ViewHolder
		{
			public ImageView img;
			public TextView text;
		}

	}

}
