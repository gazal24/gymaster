package com.appazal.gymaster;


import com.appazal.gymaster.R;
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
		l("Sql seed data : " + GymdbHelper.SQL_SEED_DATA);
		System.out.println("Muscle Value " + GymdbHelper.readMuscle(getApplicationContext()));
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
}


