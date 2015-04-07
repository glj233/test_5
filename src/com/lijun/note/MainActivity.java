package com.lijun.note;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity implements OnClickListener {
	private Button textBtn,imgBtn,videoBtn;
	private ListView lv;
	private Intent i;
	private MyAdpter adpter;
	private NoteDB noteDB;
	private SQLiteDatabase dbReader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}
	private void init() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.list1);
		textBtn = (Button) findViewById(R.id.butt1);
		imgBtn = (Button) findViewById(R.id.butt2);
		videoBtn = (Button) findViewById(R.id.butt3);
		textBtn.setOnClickListener(this);
		imgBtn.setOnClickListener(this);
		videoBtn.setOnClickListener(this);
		noteDB = new NoteDB(this);
		dbReader = noteDB.getReadableDatabase();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		i = new Intent(this,AddContent.class);
		switch(v.getId()){
		case R.id.butt1:
			i.putExtra("flag", "1");
			startActivity(i);
			break;
		case R.id.butt2:
			i.putExtra("flag", "2");
			startActivity(i);
			break;
		case R.id.butt3:
			i.putExtra("flag","3");
			startActivity(i);
			break;
	
		}
	}
	public void selectDB(){
		
		Cursor cursor = dbReader.query( NoteDB.TABLE_NAME,null,null,null, null, null, null);
		adpter = new MyAdpter(this,cursor);
		lv.setAdapter(adpter);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		selectDB();
	}
	
}
