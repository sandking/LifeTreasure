package com.sk.life;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity implements OnClickListener
{
	final static String TAG = SearchActivity.class.getSimpleName();

	// Controls
	Button mBtn_Clear_Input, mBtn_Cancel, mBtn_Clear_Histroy;
	EditText mEdti_Input;
	ListView mList_Histroy;

	// Data

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{

		}
	}

}
