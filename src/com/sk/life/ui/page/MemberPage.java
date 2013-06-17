package com.sk.life.ui.page;

import java.util.Arrays;
import java.util.LinkedList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sk.life.R;
import com.sk.life.view.PullToRefreshListView;
import com.sk.life.view.PullToRefreshListView.OnRefreshListener;

public class MemberPage extends BasePage
{

	PullToRefreshListView mPullToRefreshListView;

	private LinkedList<String> mListItems;

	@Override
	public void onCreate(Context context)
	{
		super.onCreate(context);
		initControls();
		// Set a listener to be invoked when the list should be refreshed.
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener()
		{
			@Override
			public void onRefresh()
			{
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, mListItems);

		// setListAdapter(adapter)
		mPullToRefreshListView.setAdapter(new CommentAdapter());
	}

	void initControls()
	{

		mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listView_shop_comment);
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]>
	{

		@Override
		protected String[] doInBackground(Void... params)
		{
			// Simulates a background job.
			try
			{
				Thread.sleep(2000);
			}
			catch (InterruptedException e)
			{
				;
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result)
		{
			mListItems.addFirst("Added after refresh...");

			// Call onRefreshComplete when the list has been refreshed.
			mPullToRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	class CommentAdapter extends BaseAdapter
	{

		class ViewHolder
		{
			ImageView imgCommentPic;
			ImageView imgIsUnion;

			TextView txtShopName;
			TextView txtCardAbs;
			TextView txtCardCount;
		}

		ViewHolder mHolder;

		@Override
		public int getCount()
		{
			return 10;
		}

		@Override
		public Object getItem(int position)
		{
			return null;
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
				convertView = getLayoutInflater().inflate(R.layout.item_member_card, null);
				mHolder.imgCommentPic = (ImageView) convertView.findViewById(R.id.img_comment_card_pic);
				mHolder.imgIsUnion = (ImageView) convertView.findViewById(R.id.img_is_union_member);
				mHolder.txtShopName = (TextView) convertView.findViewById(R.id.txt_comment_shop_name);
				mHolder.txtCardAbs = (TextView) convertView.findViewById(R.id.txt_comment_card_abstract);
				mHolder.txtCardCount = (TextView) convertView.findViewById(R.id.txt_comment_card_num);
				convertView.setTag(mHolder);
			}
			else mHolder = (ViewHolder) convertView.getTag();
			return convertView;
		}
	}

	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler" };
}
