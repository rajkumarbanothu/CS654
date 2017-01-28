package com.mobile.tracker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class DataManipulator {

	private static final String DATABASE_NAME="mobiledatabase.db";
	private static final int DATABASE_VERSION=1;
	static final String TABLE_NAME="mobiletable";
	private static Context context;
	static SQLiteDatabase db;
	private SQLiteStatement insertstmt;
	public String[] columns={"number1","number2","number3","ssno","isinstalled"};
	private static final String INSER="insert into "+TABLE_NAME+" values(?,?,?,?,?)";
	

	public DataManipulator(Context context) {
		// TODO Auto-generated constructor stub
		DataManipulator.context=context;
		OpenHelper openHelper=new OpenHelper(DataManipulator.context);
		DataManipulator.db=openHelper.getWritableDatabase();
		this.insertstmt=DataManipulator.db.compileStatement(INSER);
		
	}

	public long insert(String num1, String num2 ,String num3, String sno, String isinstall) {
		// TODO Auto-generated method stub
		this.insertstmt.bindString(1, num1);
		this.insertstmt.bindString(2,num2);
		this.insertstmt.bindString(3,num3);
		this.insertstmt.bindString(4,sno);
		this.insertstmt.bindString(5,isinstall);
		return this.insertstmt.executeInsert();
		
		}
	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}
	

/*	public List<String[]> selectAll() {
		// TODO Auto-generated method stub
		List<String[]> list=new ArrayList<String[]>();
		Cursor cursor=db.query(TABLE_NAME, columns, null, null, null, null, null);
		int x=0;
		if(cursor.moveToFirst()) {
			do {
				String[] b1=new String[] {cursor.getString(0),cursor.getString(1),cursor.getString(2)};
				list.add(b1);
				x=x+1;
				
			} while(cursor.moveToNext());
		}
		if(cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		return list;
	}
	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null);
		
	} */
	
	public String getRegisteredData() {
		String result="";
		Cursor c=db.query(TABLE_NAME, columns, null, null, null, null, null, null);
		if(c.moveToFirst()) {
				String[] nums=new String[] {c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)};
				String fnum=nums[0];
				String snum=nums[1];
				String tnum=nums[2];
				String simnum=nums[3];
				String isinstal=nums[4];
				result=fnum+"-"+snum+"-"+tnum+"-"+simnum+"-"+isinstal;
				
		}
		return result;
		
	}
	
	private static class OpenHelper extends SQLiteOpenHelper {
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+TABLE_NAME+"(number1 Text,number2 Text,number3 Text,ssno Text,isinstalled Text)");
		}
 		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}
	
	
}
