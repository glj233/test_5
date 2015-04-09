package com.lijun.note;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


public class SelectAct extends Activity implements OnClickListener{
	private Button butt1,butt2;
	private TextView s_textView;
	private ImageView s_img;
	private VideoView s_video;
	private NoteDB noteDB2;
	private SQLiteDatabase  dbWriter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		
		butt1 = (Button) findViewById(R.id.s_delete);
		butt2 = (Button) findViewById(R.id.s_back);
		s_textView =(TextView) findViewById(R.id.s_tv);
		s_img = (ImageView) findViewById(R.id.s_img);
		s_video = (VideoView) findViewById(R.id.s_video);
		
		noteDB2 = new NoteDB(this);
		dbWriter = noteDB2.getWritableDatabase();
		
		butt1.setOnClickListener(this);
		butt2.setOnClickListener(this);
		
		if (getIntent().getStringExtra(NoteDB.PATH).equals("null")) {
			s_img.setVisibility(View.GONE);
		} else {
			s_img.setVisibility(View.VISIBLE);
		}
		
		if (getIntent().getStringExtra(NoteDB.VEDIO).equals("null")) {
			s_video.setVisibility(View.GONE);
		} else {
			s_video.setVisibility(View.VISIBLE);
		}
		s_textView.setText(getIntent().getStringExtra(NoteDB.CONTENT));
		s_img.setImageBitmap( MyAdpter.getImageThumbnail( getIntent().getStringExtra(NoteDB.PATH),500, 500) );;
		
		s_video.setVideoURI(Uri.parse(getIntent().getStringExtra(NoteDB.VEDIO)));
		s_video.start();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.s_delete:
			 deleteData() ;
			 finish();
			break;
		case  R.id.s_back:
			finish();
		break;
		default:
			break;
		}
	}
	
	public void deleteData() {
		dbWriter.delete(NoteDB.TABLE_NAME,
				"_id=" + getIntent().getIntExtra(NoteDB.ID, 0), null);
		
	}

	
}
