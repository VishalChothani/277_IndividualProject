package com.vishal.project7up7down;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHandler1 {

	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String REPASSWORD = "rePassword";
	public static final String SCORE = "score";
	
	public static final String TABLE_NAME = "user_information";
	public static final String DATABASE_NAME = "277Game";
	
	public static final int DATABASE_VERSION = 3;
	
	public static final String TABLE_CREATE = "create table " +TABLE_NAME+"("+USERNAME+ " text not null," +EMAIL +" text not null," + PASSWORD + " text not null," + REPASSWORD + " text not null," + SCORE + " text not null );";

	DataBaseHelper dbhelper;	
	Context context;
	
	SQLiteDatabase sqlDb;
	
	public DataHandler1(Context context)
	{
		this.context = context;
		dbhelper = new DataBaseHelper(context);
	}
	
	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context context) 
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try
			{
				db.execSQL(TABLE_CREATE);
			}
			catch(Exception e)
			{
				
			}
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.i("SQL", "Inside");
			db.execSQL("DROP TABLE IF EXISTS user_information");
			onCreate(db);
		}
		
	}
	
	public DataHandler1 open()
	{
		sqlDb = dbhelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbhelper.close();
	}
	
	public long insertData(String username, String email, String password, String rePassword)
	{
		ContentValues contentValues = new ContentValues();		
		contentValues.put(USERNAME, username);
		contentValues.put(EMAIL, email);
		contentValues.put(PASSWORD, password);
		contentValues.put(REPASSWORD, rePassword);
		contentValues.put(SCORE, "5"); 
		return sqlDb.insert(TABLE_NAME,null,contentValues);
	}
	
	public long updateData(String email, String score)
	{
		ContentValues contentValues = new ContentValues();		
		
		contentValues.put(SCORE, "score");
		return sqlDb.update(TABLE_NAME, contentValues, email, null);
	}
	
	public Cursor getData()
	{
		return sqlDb.query(TABLE_NAME, new String[] {USERNAME,EMAIL,PASSWORD,REPASSWORD,SCORE}, null, null, null, null, null);
	}
	
}
