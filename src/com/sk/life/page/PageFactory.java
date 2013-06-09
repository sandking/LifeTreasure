package com.sk.life.page;

import java.util.HashMap;

import android.content.Context;

public class PageFactory
{

	private static PageFactory mInstance;

	private Context mContext;

	private PageFactory(Context context)
	{
		mContext = context;
	}

	public static PageFactory getInstance(Context context)
	{
		return mInstance == null ? new PageFactory(context) : mInstance;
	}

	private HashMap<Integer, BasePage> page_cache = new HashMap<Integer, BasePage>();

	public BasePage create(int id)
	{
		BasePage basePage = null;
		if (!page_cache.containsKey(id))
		{
			switch (id)
			{
			case 0:
				basePage = new SearchPage();
				break;
			case 1:
				basePage = new MemberPage();
				break;
			case 2:
				basePage = new CenterPage();
				break;
			case 3:
				basePage = new MorePage();
				break;
			}
		}
		else basePage = page_cache.get(id);

		return basePage;
	}

	public void createPage(int id)
	{
		if (page_cache.containsKey(id)) return;

		create(id).onCreate(mContext);
	}

	public void destroyPage(int id)
	{
		if (!page_cache.containsKey(id)) return;

		page_cache.remove(id).onDestroy(mContext);
	}

}
