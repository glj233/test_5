package com.lijun.note;




import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdpter extends BaseAdapter {

	private Context context;
	private Cursor cursor;
	private LinearLayout layout;
	
	public MyAdpter(Context context,Cursor cursor){
		this.context = context;
		this.cursor = cursor;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cursor.getPosition();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater  =  LayoutInflater.from(context);
		layout = (LinearLayout)inflater.inflate(R.layout.ceil, null);
		TextView contentv = (TextView) layout.findViewById(R.id.list_text);
		TextView timev = (TextView) layout.findViewById(R.id.list_time);
		ImageView imgiv = (ImageView) layout.findViewById(R.id.list_img);
		ImageView videoiv = (ImageView) layout.findViewById(R.id.list_video);
		cursor.moveToPosition(position);
		String content = cursor.getString(cursor.getColumnIndex("content"));
		String time = cursor.getString(cursor.getColumnIndex("time"));
		String url = cursor.getString(cursor.getColumnIndex("path"));
		String urlvedio = cursor.getString(cursor.getColumnIndex("vedio"));
		contentv.setText(content);
		timev.setText(time);
		imgiv.setImageBitmap(getImageThumbnail(url, 200, 200));
		videoiv.setImageBitmap(getVideoThumbnail(urlvedio, 200, 200,
				MediaStore.Images.Thumbnails.MICRO_KIND));
		return layout;
	}
	public static Bitmap getImageThumbnail(String url,int width,int height) {
		
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(url, options);
		options.inJustDecodeBounds = false;
		int beWidth = options.outWidth/width;
		int beHeight = options.outHeight/height;
		int be = 1;
		if(beWidth<beHeight){
			be = beWidth;
		}else {
			be = beHeight;
		}
		if (be <= 0) {
			be =1;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(url, options);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		
		return bitmap;
	}
	
	public Bitmap getVideoThumbnail(String url,int width,int height,int kind ) {
		Bitmap bitmap = null;
		bitmap = ThumbnailUtils.createVideoThumbnail(url, kind);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		
		
		return bitmap;
	}
}
