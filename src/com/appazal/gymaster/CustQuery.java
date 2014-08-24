package com.appazal.gymaster;

import com.appazal.gymaster.Gymdb.Group;

public class CustQuery {
	//This should be a join query that returns everything.
	public static final String NEXT_SET = "SELECT * FROM \"" + Group.TABLE_NAME 
				+ "\" ORDER BY \"" + Group.COLUMN_NAME_LAST_DATE + "\" ASC LIMIT 1;";
	
	public static final String GROUP_NAME = "SELECT * FROM \"" + Group.TABLE_NAME
				+ "\" WHERE " + Group._ID + "= ? ";
	
	public static final String RESET_GROUP_TIME = "UPDATE \"" + Group.TABLE_NAME + "\""
			+ " SET " + Group.COLUMN_NAME_LAST_DATE + "= datetime()";
	

	public static final String UPDATE_GROUP_TIME = RESET_GROUP_TIME + " WHERE " + Group._ID + " = ? ";
	
	public static final String NEXT_GROUP_AND_MUSCLES = "select P._id as group_id, P.name as group_name,"
			+ "	muscles.name as muscle_name, last_date from muscles "
			+ "	join (select G._id, last_date, name, muscle_id from sets "
			+ "			join (select * from groups order by last_date asc limit %s,1) G on (G._id = sets.group_id) "
			+ "	) AS P on (muscles._id = P.muscle_id);";
}
