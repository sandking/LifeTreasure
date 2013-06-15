package com.sk.life.ui.search;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.sk.life.R;

public class SearchActivity extends Activity implements OnClickListener
{
	final static String TAG = SearchActivity.class.getSimpleName();

	// Controls
	Button mBtn_Clear_Input, mBtn_Cancel, mBtn_Clear_Histroy;
	EditText mEdit_Input;
	ListView mList_Histroy;

	// Adapter
	SearchHistroyAdapter mHistroyAdapter;

	// Data
	List<String> mSearch_Histroy_Data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		initData();
		findControls();
		setData();
	}

	void findControls()
	{
		mEdit_Input = (EditText) findViewById(R.id.edit_search_input);
		mList_Histroy = (ListView) findViewById(R.id.list_search_histroy);
		mBtn_Clear_Input = (Button) findViewById(R.id.btn_clear_input);
		mBtn_Clear_Histroy = (Button) findViewById(R.id.btn_clear_search_histroy);
		mBtn_Cancel = (Button) findViewById(R.id.btn_search_cancel);
	}

	void initData()
	{
		mSearch_Histroy_Data = new ArrayList<String>();
		mHistroyAdapter = new SearchHistroyAdapter();
	}

	void setData()
	{
		mBtn_Clear_Input.setOnClickListener(this);
		mBtn_Clear_Histroy.setOnClickListener(this);
		mBtn_Cancel.setOnClickListener(this);

		mList_Histroy.setAdapter(mHistroyAdapter);
		mList_Histroy.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		int visible = (mSearch_Histroy_Data == null | mSearch_Histroy_Data.isEmpty()) ? View.INVISIBLE : View.VISIBLE;
		mBtn_Clear_Histroy.setVisibility(visible);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_clear_input:
			mEdit_Input.setText("");
			break;
		case R.id.btn_clear_search_histroy:

			break;
		case R.id.btn_search_cancel:
			finish();
			break;
		}
	}
	
	@Override
	public void finish()
	{
		super.finish();
		overridePendingTransition(0, R.anim.search_out);
	}
	

	class SearchHistroyAdapter extends BaseAdapter
	{

		class ViewHolder
		{
			public TextView txt;
		}

		ViewHolder mHolder;

		@Override
		public int getCount()
		{
			return mSearch_Histroy_Data.size();
		}

		@Override
		public Object getItem(int position)
		{
			return mSearch_Histroy_Data.get(position);
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
				mHolder = new ViewHolder();
				convertView = getLayoutInflater().inflate(R.layout.item_search_histroy_list, null);
				mHolder.txt = (TextView) findViewById(R.id.txt_search_item_histroy_list);
				convertView.setTag(mHolder);
			}
			else mHolder = (ViewHolder) convertView.getTag();
			mHolder.txt.setText(mSearch_Histroy_Data.get(position));
			return convertView;
		}
	}

}
