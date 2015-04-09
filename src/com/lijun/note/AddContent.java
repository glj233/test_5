package com.lijun.note;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;





import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.VideoView;


public class AddContent extends Activity implements OnClickListener{
	private String val;
	private Button saveBtn,deleteBtn;
	private EditText edit;
	private ImageView c_img;
	private VideoView  v_video;
	private NoteDB noteDB;
	private SQLiteDatabase dbWriter;
	private File phoneFile,vedioFile;
	private static final String TAG = "MyService"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_content);
		val = getIntent().getStringExtra("flag");
		 
		Log.v(TAG,val);
		saveBtn = (Button) findViewById(R.id.save);
		deleteBtn =(Button) findViewById(R.id.delete);
		edit =(EditText) findViewById(R.id.ettext);
		c_img =(ImageView) findViewById(R.id.c_img);
		v_video =(VideoView) findViewById(R.id.c_video);
		saveBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
		noteDB = new NoteDB(this);
		dbWriter = noteDB.getWritableDatabase();
		initView();
		}
	public void initView() {
		if (val.equals("1")){
			c_img.setVisibility(View.GONE);
			v_video.setVisibility(View.GONE);
		}
		
		if (val.equals("2")){
			c_img.setVisibility(View.VISIBLE);
			v_video.setVisibility(View.GONE);

			Intent iimg = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			phoneFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".jpg");
			iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
			startActivityForResult(iimg, 1);
		}
	
		if (val.equals("3")){
			c_img.setVisibility(View.GONE);
			v_video.setVisibility(View.VISIBLE);
			Intent vedio = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			vedioFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/" + getTime() + ".mp4");
			vedio.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(vedioFile));
			startActivityForResult(vedio, 2);
		}
	}
	@Override
	public void onClick(View v){
		switch (v.getId()){
		case R.id.save:
			addDB();
			finish();
			break;
			
		case R.id.delete:
			finish();
			break;
		}
		
	}
	public void addDB(){
	ContentValues cv = new ContentValues();
	cv.put(NoteDB.CONTENT, edit.getText().toString());
	cv.put(NoteDB.TIME, getTime());
	cv.put(NoteDB.PATH, phoneFile+"");
	cv.put(NoteDB.VEDIO, vedioFile+"");
	dbWriter.insert(NoteDB.TABLE_NAME, null,cv);
	}
	private String getTime() {
		// TODO Auto-generated method stub
		SimpleDateFormat format_1 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date date = new Date();
		String str = format_1.format(date);
		return str;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
		c_img.setImageBitmap( MyAdpter.getImageThumbnail( phoneFile+"",400, 400) );
		}
		if (requestCode == 2) {
			v_video.setVideoURI(Uri.fromFile(vedioFile));
			v_video.start();
		}
	}
	
	

}
