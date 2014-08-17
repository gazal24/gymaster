package com.appazal.gymaster;

import android.provider.BaseColumns;

public final class Gymdb implements BaseColumns {
	
	public Gymdb(){
		
	}
	
	public static abstract class Muscle implements BaseColumns{
		public static final String TABLE_NAME = "muscles";
		public static final String COLUMN_NAME_NAME = "name";
	}
	
	public static abstract class Group implements BaseColumns{
		public static final String TABLE_NAME = "groups";
		public static final String COLUMN_NAME_NAME = "name";
		public static final String COLUMN_NAME_LAST_DATE = "last_date";
	}
	
	public static abstract class Set implements BaseColumns {
		public static final String TABLE_NAME = "sets";
		public static final String COLUMN_NAME_MUSCLE = "muscle_id";
		public static final String COLUMN_NAME_GROUP = "group_id";
	}
}

