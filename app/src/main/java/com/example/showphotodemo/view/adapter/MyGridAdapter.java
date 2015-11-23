package com.example.showphotodemo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.showphotodemo.R;
import com.example.showphotodemo.entity.WechatMoment;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyGridAdapter extends BaseAdapter {
	private List<WechatMoment.ImagesEntity> files;

	private LayoutInflater mLayoutInflater;

	public MyGridAdapter(List<WechatMoment.ImagesEntity> files, Context context) {
		this.files = files;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return files == null ? 0 : files.size();
	}

	@Override
	public String getItem(int position) {
		return files.get(position).getUrl();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyGridViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new MyGridViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.gridview_item, parent, false);
			viewHolder.iv_album_image = (ImageView) convertView.findViewById(R.id.iv_album_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyGridViewHolder) convertView.getTag();
		}
		String url = getItem(position);

		ImageLoader.getInstance().displayImage(url, viewHolder.iv_album_image);

		return convertView;
	}

	private static class MyGridViewHolder {
		ImageView iv_album_image;
	}
}
