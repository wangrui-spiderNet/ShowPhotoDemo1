package com.soufun.test.entity;

import java.io.Serializable;

import android.net.Uri;

public class PictureImageInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8416297091453405866L;

	public String ImagePath;//图片路径
	public int Type;//是照相还是图库选择的1:照相，2：图库，3：网络
	public Uri uri;//如果从照相的从uri里面存路径
}
