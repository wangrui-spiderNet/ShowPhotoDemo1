package com.example.showphotodemo.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showphotodemo.R;
import com.example.showphotodemo.entity.WechatMoment;
import com.example.showphotodemo.util.StringUtil;
import com.example.showphotodemo.view.ImagePagerActivity;
import com.example.showphotodemo.view.MainActivity;
import com.example.showphotodemo.view.widget.MyGridView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends BaseAdapter {

    private List<WechatMoment> wechats;
	private LayoutInflater mInflater;
	private Context mContext;
	private boolean isClickable = true;// 是否可以点击展开大图
	private int availableTextWidth;

	/**
	 * 列表显示适配器的构造方法，默认点击图片显示大图
	 * 
	 * @param context
	 * @param wechats
	 *            数据集合
	 */
	public MyListAdapter(Context context, ArrayList<WechatMoment> wechats) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
//		this.mList = list;

		this.wechats = wechats;
	}

	/**
	 * 列表显示适配器的构造方法，可以控制点击是否能显示大图
	 * 
	 * @param context
	 * @param isClickable
	 *            为true时点击图片可以显示大图，否则不显示
	 */
	public MyListAdapter(Context context, ArrayList<WechatMoment> wechats,
			boolean isClickable) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
		this.wechats = wechats;
		this.isClickable = isClickable;
	}

	@Override
	public int getCount() {
		return wechats == null ? 0 : wechats.size();
	}

	@Override
	public WechatMoment getItem(int position) {
		return wechats.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder.iv_avator = (ImageView) convertView
					.findViewById(R.id.iv_avator);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_content = (TextView) convertView
					.findViewById(R.id.tv_content);
			holder.tv_location = (TextView) convertView
					.findViewById(R.id.tv_location);
			holder.tv_showorhide = (TextView) convertView
					.findViewById(R.id.tv_showorhide);
			holder.tv_identity = (TextView) convertView
					.findViewById(R.id.tv_identity);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			holder.btn_progress = (Button) convertView
					.findViewById(R.id.btn_progress);
			holder.gv_gridView = (MyGridView) convertView
					.findViewById(R.id.gv_gridView);
			// 获取屏幕的宽度减去TextView两边的间距，得到TextView的有效宽度（两行）
			availableTextWidth = (MainActivity.screenWidth - dip2px(mContext,
					6 + 6 + 50 + 10)) * 2;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final WechatMoment bean = getItem(position);

		if(!StringUtil.isEmpty(bean.getContent())){
			showContent(holder, bean.getContent(), position);
		}

		if(bean.getSender()!=null&& !StringUtil.isEmpty(bean.getSender().getAvatar())){
			ImageLoader.getInstance().displayImage(bean.getSender().getAvatar(), holder.iv_avator);
		}

		if(bean.getSender()!=null){

			if(!StringUtil.isEmpty(bean.getSender().getNick())){
				holder.tv_name.setText(bean.getSender().getNick());
			}

			if(!StringUtil.isEmpty(bean.getSender().getUsername())){
				holder.tv_identity.setText(bean.getSender().getUsername());
			}

		}

		holder.tv_content.setText(bean.getContent());

		if (bean.getImages() != null && bean.getImages().size() > 0) {
			holder.gv_gridView.setVisibility(View.VISIBLE);
			if(bean.getImages().size() == 4){
				holder.gv_gridView.setNumColumns(2);
			}else{
				holder.gv_gridView.setNumColumns(3);
			}
			holder.gv_gridView
					.setAdapter(new MyGridAdapter(bean.getImages(), mContext));
			if (isClickable) {
				holder.gv_gridView
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								imageBrower(position, bean.getImages());
							}
						});
			}
		} else {
			holder.gv_gridView.setVisibility(View.GONE);
		}

		return convertView;
	}

	/** 关于展示内容的逻辑 */
	private void showContent(final ViewHolder hv, final String content,
			final int position) {
		hv.tv_content.setText(content);
		if (availableTextWidth < getTextViewLength(hv.tv_content,
				content)) {
			hv.tv_showorhide.setVisibility(View.VISIBLE);
			if (!getItem(position).isShow()) {
				hv.tv_showorhide.setText("全文");
				hv.tv_showorhide.setVisibility(View.VISIBLE);
				hv.tv_content.setEllipsize(TruncateAt.END);
				hv.tv_content.setMaxLines(2);
			} else {
				hv.tv_content.setMaxLines(1024);
				hv.tv_showorhide.setText("收起");
			}

			hv.tv_showorhide.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (hv.tv_showorhide.getText().equals("全文")) {
						hv.tv_content.setMaxLines(1024);
						hv.tv_showorhide.setText("收起");
						getItem(position).setIsShow(true);
					} else {
						hv.tv_showorhide.setText("全文");
						hv.tv_content.setMaxLines(2);
						hv.tv_showorhide.setVisibility(View.VISIBLE);
						getItem(position).setIsShow(false);
					}
				}
			});
		} else {
			hv.tv_showorhide.setVisibility(View.GONE);
		}
	}


	/**
	 * 点击图片跳转到显示大图界面
	 * 
	 * @param position
	 *            位置
	 * @param urls
	 *            图片urls
	 */
	private void imageBrower(int position, List<WechatMoment.ImagesEntity> urls) {

		String [] urlarray=new String[urls.size()];
		for(int i=0;i<urls.size();i++){
			urlarray[i]=urls.get(i).getUrl();
		}

		Intent intent = new Intent(mContext, ImagePagerActivity.class);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urlarray);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		mContext.startActivity(intent);
	}

	private static class ViewHolder {

		public TextView tv_name;
		public ImageView iv_avator;
		TextView tv_content;
		TextView tv_content_long;
		TextView tv_time;
		TextView tv_location;
		TextView tv_identity;
		Button btn_progress;
		TextView tv_showorhide;
		MyGridView gv_gridView;
	}
	/**
	 * 计算出该TextView中文字的长度(像素)
	 * 
	 * @param textView
	 * @param text
	 * @return
	 */
	public static int getTextViewLength(TextView textView, String text) {
		TextPaint paint = textView.getPaint();
		// 得到使用该paint写上text的时候,像素为多少
		int textLength = (int) paint.measureText(text);
		return textLength;
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
