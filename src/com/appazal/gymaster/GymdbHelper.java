package com.appazal.gymaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.appazal.gymaster.Gymdb.Group;
import com.appazal.gymaster.Gymdb.Muscle;
import com.appazal.gymaster.Gymdb.Set;

public class GymdbHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "gymaster_db";
	private static final int DATABASE_VERSION = 1;

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_MUSCLES= 
			"CREATE TABLE " + Muscle.TABLE_NAME + " (" + 
	    	Muscle._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
	    	Muscle.COLUMN_NAME_NAME+ TEXT_TYPE + ")";

	private static final String SQL_DELETE_MUSCLES =
	    "DROP TABLE IF EXISTS " + Muscle.TABLE_NAME;

	private static final String SQL_CREATE_GROUPS = 
			"CREATE TABLE " + Group.TABLE_NAME + " (" + 
			Group._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			Group.COLUMN_NAME_LAST_DATE + " DATETIME," +
	    	Group.COLUMN_NAME_NAME+ TEXT_TYPE + ")";

	private static final String SQL_DELETE_GROUPS =
	    "DROP TABLE IF EXISTS " + Group.TABLE_NAME;

	private static final String SQL_CREATE_SETS = 
			"CREATE TABLE " + Set.TABLE_NAME + " (" + 
	    	Set.COLUMN_NAME_MUSCLE+ TEXT_TYPE + COMMA_SEP + 
	    	Set.COLUMN_NAME_GROUP+ TEXT_TYPE + ")";
	
	static final String SQL_SEED_MUSCLES = 
			"INSERT INTO " + Muscle.TABLE_NAME + "(" + Muscle.COLUMN_NAME_NAME + ")" + 
			"VALUES (\"" + TextUtils.join("\"),(\"",SeedData.Muscles) + "\")"; 

	static final String SQL_SEED_GROUPS = 
			"INSERT INTO " + Group.TABLE_NAME + "(" + Group.COLUMN_NAME_NAME + ")" + 
			"VALUES (\"" + TextUtils.join("\"),(\"",SeedData.Groups) + "\")";
	
	static final String SQL_SEED_SETS = 
			"INSERT INTO " + Set.TABLE_NAME + "(" + Set.COLUMN_NAME_GROUP  + COMMA_SEP  + Set.COLUMN_NAME_MUSCLE + ")" + 
			"VALUES " + SeedData.Sets;
	
	private static final String SQL_DELETE_SETS =
	    "DROP TABLE IF EXISTS " + Set.TABLE_NAME;

	public GymdbHelper(Context context) {
		super(context, DATABASE_NAME, null , DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MUSCLES);
		db.execSQL(SQL_CREATE_GROUPS);
		db.execSQL(SQL_CREATE_SETS);
		db.execSQL(SQL_SEED_MUSCLES);
		db.execSQL(SQL_SEED_GROUPS);
		db.execSQL(SQL_SEED_SETS);
		db.execSQL(CustQuery.RESET_GROUP_TIME);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_MUSCLES);
		db.execSQL(SQL_DELETE_GROUPS);
		db.execSQL(SQL_DELETE_SETS);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       onUpgrade(db, oldVersion, newVersion);
    }
	
	private void insertMuscle(Context context, String name){
		GymdbHelper mDbHelper = new GymdbHelper(context);
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Muscle.COLUMN_NAME_NAME, name);
		db.insert(Muscle.TABLE_NAME, null, values);
	}
	
	private void insertGroup(Context context, String name, String date){
		GymdbHelper mDbHelper = new GymdbHelper(context);
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Group.COLUMN_NAME_NAME, name);
		values.put(Group.COLUMN_NAME_LAST_DATE, date);
		db.insert(Group.TABLE_NAME, null, values);
	}

	public static void insertSet(Context context, String muscle_id, String group_id){
		GymdbHelper mDbHelper = new GymdbHelper(context);
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Set.COLUMN_NAME_GROUP, group_id);
		values.put(Set.COLUMN_NAME_MUSCLE, muscle_id);
		db.insert(Set.TABLE_NAME, null, values);
	}	
	
	public static Cursor readData(Context context, String sql_query){
		return readData(context, sql_query, null);
	}
	public static Cursor readData(Context context, String sql_query, String[] selectionArgs){
		GymdbHelper mDbHelper = new GymdbHelper(context);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

//		Cursor cursor = db.query(
//		    table_name,  // The table to query
//		    null,                               // The columns to return
//		    null,                                // The columns for the WHERE clause
//		    null,                            // The values for the WHERE clause
//		    null,                                     // don't group the rows
//		    null,                                     // don't filter by row groups
//		    null                                 // The sort order
//		    );
		Cursor cursor = db.rawQuery(sql_query, selectionArgs);
		cursor.moveToFirst();
		return cursor;
	}
}
