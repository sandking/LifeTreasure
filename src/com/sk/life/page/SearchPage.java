package com.sk.life.page;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.sk.life.R;

public class SearchPage extends BasePage implements OnCheckedChangeListener
{

	final static int FLAG_SEPARATE = 0;
	final static int FLAG_BUSINESS = 1;

	GridView mGridView;

	String[] arrays_separate_txt, arrays_business_txt;
	int[] arrays_separate_drawable, arrays_business_drawable;

	GridAdapter mSeparateAdapter, mBusinessAdapter;

	RadioButton mSeparateRadio, mBusinessRadio;

	OnItemClickListener mSeparateListener, mBusinessListener;

	@Override
	public void onCreate(Context context)
	{
		super.onCreate(context);
		arrays_separate_txt = getStringArray(R.array.arrays_search_separate_item_txt);
		arrays_business_txt = getStringArray(R.array.arrays_search_business_item_txt);
		arrays_separate_drawable = new int[] { R.drawable.icon_search_grid_se_item_1, R.drawable.icon_search_grid_se_item_2, R.drawable.icon_search_grid_se_item_3, R.drawable.icon_search_grid_se_item_4, R.drawable.icon_search_grid_se_item_5, R.drawable.icon_search_grid_se_item_6, R.drawable.icon_search_grid_se_item_7, R.drawable.icon_search_grid_se_item_8, R.drawable.icon_more, };
		arrays_business_drawable = new int[] { R.drawable.icon_search_grid_bu_item_1, R.drawable.icon_search_grid_bu_item_2, R.drawable.icon_search_grid_bu_item_3, R.drawable.icon_search_grid_bu_item_4, R.drawable.icon_search_grid_bu_item_5, R.drawable.icon_search_grid_bu_item_6, R.drawable.icon_search_grid_bu_item_7, R.drawable.icon_search_grid_bu_item_8, R.drawable.icon_more, };
		initControls();
	}

	void initControls()
	{
		mGridView = (GridView) findViewById(R.id.grid_search_content);
		mSeparateRadio = (RadioButton) findViewById(R.id.radiobtn_search_separate);
		mBusinessRadio = (RadioButton) findViewById(R.id.radiobtn_search_business);
		mSeparateRadio.setOnCheckedChangeListener(this);
		mBusinessRadio.setOnCheckedChangeListener(this);

		mSeparateAdapter = new GridAdapter(FLAG_SEPARATE);
		mBusinessAdapter = new GridAdapter(FLAG_BUSINESS);

		mSeparateListener = new GridOnItemClickListener(FLAG_SEPARATE);
		mBusinessListener = new GridOnItemClickListener(FLAG_BUSINESS);

		mGridView.setAdapter(mSeparateAdapter);
		mGridView.setOnItemClickListener(mSeparateListener);
		mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

	}

	@Override
	public void onDestroy(Context context)
	{
		super.onDestroy(context);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{

		switch (buttonView.getId())
		{
		case R.id.radiobtn_search_separate:
			mSeparateRadio.setBackgroundResource(isChecked ? R.drawable.btn_search_separate_selected : R.drawable.btn_search_separate_normal);
			mGridView.setAdapter(isChecked ? mSeparateAdapter : mBusinessAdapter);
			mGridView.setOnItemClickListener(isChecked ? mSeparateListener : mBusinessListener);
			break;
		case R.id.radiobtn_search_business:
			mBusinessRadio.setBackgroundResource(isChecked ? R.drawable.btn_search_business_selected : R.drawable.btn_search_business_normal);
			mGridView.setAdapter(isChecked ? mBusinessAdapter : mSeparateAdapter);
			mGridView.setOnItemClickListener(isChecked ? mBusinessListener : mSeparateListener);
			break;
		}
	}

	class GridAdapter extends BaseAdapter
	{

		private int index;
		private ViewHolder mHolder;
		private String[] string_arrays;
		private int[] drawable_arrays;

		public GridAdapter(int index)
		{
			this.index = index;
			this.string_arrays = index == FLAG_SEPARATE ? arrays_separate_txt : arrays_business_txt;
			this.drawable_arrays = index == FLAG_BUSINESS ? arrays_separate_drawable : arrays_business_drawable;
		}

		public int getCurrentIndex()
		{
			return index;
		}

		@Override
		public int getCount()
		{
			return string_arrays.length;
		}

		@Override
		public Object getItem(int position)
		{
			return string_arrays[position];
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
			{
				convertView = getLayoutInflater().inflate(R.layout.item_search_grid, null);
				mHolder = new ViewHolder();
				mHolder.text = (TextView) convertView.findViewById(R.id.txt_search_grid_item);
				mHolder.img = (ImageView) convertView.findViewById(R.id.img_search_grid_item);
				convertView.setTag(mHolder);
			}
			else mHolder = (ViewHolder) convertView.getTag();
			mHolder.img.setImageResource(drawable_arrays[position]);
			mHolder.text.setText(string_arrays[position]);
			return convertView;
		}

		class ViewHolder
		{
			public ImageView img;
			public TextView text;
		}
	}

	class GridOnItemClickListener implements OnItemClickListener
	{

		private int mGridId;

		public GridOnItemClickListener(int id)
		{
			mGridId = id;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			switch (mGridId)
			{
			case FLAG_SEPARATE:
				onSeparateItemClick(parent, view, position, id);
				break;
			case FLAG_BUSINESS:
				onBusinessItemClick(parent, view, position, id);
				break;
			}
		}

		public void onSeparateItemClick(AdapterView<?> parent, View view, int position, long id)
		{

		}

		public void onBusinessItemClick(AdapterView<?> parent, View view, int position, long id)
		{

		}
	}
}
