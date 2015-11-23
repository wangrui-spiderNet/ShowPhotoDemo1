package com.example.showphotodemo.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.showphotodemo.R;
import com.example.showphotodemo.view.adapter.BigPicViewPager;

public class ImagePagerActivity extends BaseActivity {
	private static final String STATE_POSITION = "STATE_POSITION";
	public static final String EXTRA_IMAGE_INDEX = "image_index";
	public static final String EXTRA_IMAGE_URLS = "image_urls";

	private BigPicViewPager vp_pager;
	private int pagerPosition;
	private ImageView[] indicator;
	private LinearLayout ll_indicator;
	private FrameLayout fl_parent;
	private String[] urls;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_detail_pager);

		pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
		urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
		vp_pager = (BigPicViewPager) findViewById(R.id.vp_pager);
		ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
		vp_pager.setAdapter(mAdapter);
		ll_indicator = (LinearLayout) findViewById(R.id.ll_indicator);
		fl_parent = (FrameLayout) findViewById(R.id.fl_parent);
		// 更新下标
		vp_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int arg0) {
				initIndicator(arg0);
			}

		});
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		vp_pager.setCurrentItem(pagerPosition);
		initIndicator(pagerPosition);
	}

	/**
	 * 加入下标点
	 * 
	 * @param postion
	 *            当前显示第几张
	 */
	private void initIndicator(int postion) {
		if (urls.length <= 1) {
			ll_indicator.setVisibility(View.GONE);
			return;
		} else {
			ll_indicator.setVisibility(View.VISIBLE);
		}
		ImageView imgView;
		indicator = new ImageView[urls.length];
		((ViewGroup) ll_indicator).removeAllViews();
		for (int i = 0; i < urls.length; i++) {
			imgView = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			params.setMargins(7, 10, 7, 10);
			imgView.setLayoutParams(params);

			indicator[i] = imgView;
			if (i == postion) {
				indicator[i].setBackgroundResource(R.drawable.timedian_true);
			} else {
				indicator[i].setBackgroundResource(R.drawable.timedian_false);
			}
			((ViewGroup) ll_indicator).addView(indicator[i]);
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, vp_pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends FragmentStatePagerAdapter {

		public String[] fileList;

		public ImagePagerAdapter(FragmentManager fm, String[] fileList) {
			super(fm);
			this.fileList = fileList;
		}

		@Override
		public int getCount() {
			return fileList == null ? 0 : fileList.length;
		}

		@Override
		public Fragment getItem(int position) {
			String url = fileList[position];
			return ImageDetailFragment.newInstance(url);
		}

	}
}