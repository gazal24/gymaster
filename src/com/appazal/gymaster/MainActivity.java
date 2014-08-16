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
		l("Sql seed data : " + GymdbHelper.SQL_SEED_GROUP);
	
		l("Group Name " + GymdbHelper.readData(getApplicationContext(), CustQuery.GRP_NAME, new String[]{"2"}).getString(2));
		
		System.out.println("Muscle Value " + GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_SET).getString(2));
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
		GymdbHelper.readData(getApplicationContext(), CustQuery.UPDATE_GROUP, new String[]{group_id});
	}
	
	public void getNextGroup(){
		GymdbHelper.readData(getApplicationContext(), CustQuery.NEXT_SET);
	}
}
