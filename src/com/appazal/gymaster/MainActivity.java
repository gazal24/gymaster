package com.appazal.gymaster;


import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

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
	
	public String getNextGroup(){
		return GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_SET).getString(2);
	}
}
