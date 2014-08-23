package com.appazal.gymaster;


import org.json.JSONObject;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static String current_group_id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public void onStart(){
		super.onStart();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		l("Sql seed data : " + GymdbHelper.SQL_SEED_GROUPS);
		l("sql seed muscles : "   + GymdbHelper.SQL_SEED_MUSCLES);
		l("create muscle " + GymdbHelper.SQL_CREATE_MUSCLES);
		l("create muscle " + GymdbHelper.SQL_CREATE_GROUPS);
		l("create muscle " + GymdbHelper.SQL_CREATE_SETS);
		l("Group Name " + GymdbHelper.readData(getApplicationContext(), CustQuery.GROUP_NAME, new String[]{"2"}).getString(2));
		
		l ("Get next 0" + GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_GROUP_AND_MUSCLES).getString(0));
		l ("Get next 1" + GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_GROUP_AND_MUSCLES).getString(1));
		l ("Get next 2" + GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_GROUP_AND_MUSCLES).getString(2));
	}
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	@Override
	public void onStop(){
		super.onStop();
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	public void l(String str){
		System.out.println(str);
	}
	
	public void markGroupDoneToday(String group_id){
		GymdbHelper.readData(getApplicationContext(), CustQuery.UPDATE_GROUP_TIME, new String[]{group_id});
	}
	
	public Cursor getNextGroup(){
		return GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_GROUP_AND_MUSCLES);
	}
	
	public void updateToday(View view){
		l("BUTTON CLICKED");
	}
	
	public void getTodayGroup(View view){
		Cursor c = getNextGroup();

		String group_name = c.getString(1);
		TextView v = (TextView)findViewById(R.id.group_name);
		v.setText(group_name);
		
		String date = c.getString(3);
		v = (TextView)findViewById(R.id.group_time);
		v.setText(date);
		
		String muscle_name = c.getString(2);
		while(c.moveToNext()){
			muscle_name += "\n" + c.getString(2);
		}
		
		v = (TextView)findViewById(R.id.muscle_name);
		v.setText(muscle_name);
	}
}
