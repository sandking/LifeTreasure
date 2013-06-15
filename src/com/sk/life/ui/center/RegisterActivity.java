package com.sk.life.ui.center;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.sk.life.R;

public class RegisterActivity extends Activity implements OnClickListener
{
	ImageButton imgButton_Bak;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		initData();
		initControls();
		setData();
	}

	void initControls()
	{
		imgButton_Bak = (ImageButton) findViewById(R.id.btn_login_back);
	}

	void initData()
	{
	}

	void setData()
	{
		imgButton_Bak.setOnClickListener(this);
	}

	@Override
	public void finish()
	{
		super.finish();
		overridePendingTransition(0, R.anim.search_out);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.btn_login_back:
			finish();
			break;
		}
	}
}
