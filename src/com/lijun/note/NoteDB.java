package com.lijun.note;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDB extends SQLiteOpenHelper {
	public static  final String TABLE_NAME = "notes";
	public static  final String CONTENT = "content";
	public static  final String PATH = "path";
				
	public static  final String ID = "_id";
	public static  final String TIME = "time";
	public NoteDB(Context context) {
		super(context, "notes", null, 1);
		// TODO Auto-generated constructor stub
	}
  @Override
  	public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	db.execSQL(" CREATE TABLE   " +TABLE_NAME +"(" +ID
					+" INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ CONTENT+ "  ," 
					+ PATH + "  ,"
					+ TIME + " TEXT NOT NULL)");
  	}
  @Override
  	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	
  	}
 
}
