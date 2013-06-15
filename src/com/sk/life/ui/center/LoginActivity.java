package com.sk.life.ui.center;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sk.life.R;

public class LoginActivity extends Activity implements OnClickListener
{
	final static int FLAG_EDIT_USERNAME = 0;
	final static int FLAG_EDIT_PASSWORD = 1;

	int mUserNameCount, mPassWordCount;

	// Controls
	Button btn_NewRegister, btn_Login;

	EditText edit_UserName, edit_Password;

	ImageView img_DelPassword;

	ImageButton imgButton_Bak;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		initData();
		initControls();
		setData();
	}

	void initControls()
	{
		btn_NewRegister = (Button) findViewById(R.id.btn_login_register);
		btn_Login = (Button) findViewById(R.id.btn_login);
		img_DelPassword = (ImageView) findViewById(R.id.img_login_password_delete);
		imgButton_Bak = (ImageButton) findViewById(R.id.btn_login_back);
		edit_UserName = (EditText) findViewById(R.id.edit_login_username);
		edit_Password = (EditText) findViewById(R.id.edit_login_password);
	}

	void initData()
	{
	}

	void setData()
	{
		btn_NewRegister.setOnClickListener(this);
		btn_Login.setOnClickListener(this);
		img_DelPassword.setOnClickListener(this);
		imgButton_Bak.setOnClickListener(this);
		edit_UserName.addTextChangedListener(new EditWatcher(FLAG_EDIT_USERNAME));
		edit_Password.addTextChangedListener(new EditWatcher(FLAG_EDIT_PASSWORD));
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
		Intent intent = null;

		switch (v.getId())
		{
		case R.id.btn_login:
			Log.e("", "Click Login Button");

			break;
		case R.id.img_login_password_delete:
			edit_Password.setText("");
			break;
		case R.id.btn_login_back:
			finish();
			break;
		case R.id.btn_login_register:
			Log.e("", "Click NewRegister Button");
			intent = new Intent();
			intent.setClass(this, RegisterActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.search_in, 0);
			break;
		}
	}

	class EditWatcher implements TextWatcher
	{

		final int mEditId;

		EditWatcher(int index)
		{
			mEditId = index;
		}

		@Override
		public void afterTextChanged(Editable s)
		{

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			Log.e("", String.format("String[%s]  ,  count[%d]  ,  start[%d]  ,  before[%d]", s, s.length(), start, before));
			switch (mEditId)
			{
			case FLAG_EDIT_USERNAME:
				onUserNameChanged(s, start, before, count);
				break;
			case FLAG_EDIT_PASSWORD:
				onPassWordChanged(s, start, before, count);
				break;
			}
		}

		void onUserNameChanged(CharSequence s, int start, int before, int count)
		{
			mUserNameCount = s.length();
			if (mUserNameCount == 0)
			{
				edit_Password.setText("");
				setLoginNoBak();
			}
			else
			{
				btn_Login.setBackgroundResource(mPassWordCount > 0 ? R.drawable.btn_login : R.drawable.bg_input_no_login);
			}
		}

		void onPassWordChanged(CharSequence s, int start, int before, int count)
		{
			mPassWordCount = s.length();
			img_DelPassword.setVisibility(mPassWordCount > 0 ? View.VISIBLE : View.INVISIBLE);
			if (mPassWordCount == 0)
			{
				setLoginNoBak();
			}
			else
			{
				btn_Login.setBackgroundResource(mUserNameCount > 0 ? R.drawable.btn_login : R.drawable.bg_input_no_login);
			}
		}

		void setLoginNoBak()
		{
			btn_Login.setBackgroundResource(R.drawable.bg_input_no_login);
		}
	}
}
