package com.appazal.gymaster;


import java.util.Random;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static String current_group_id = null;
	public static int skip_count = 0;

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
		skip_count = 0;
		
		TextView skip_button = (TextView) findViewById(R.id.skip_count_btn);
		skip_button.setOnLongClickListener(new OnLongClickListener() {			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				skip_count = -1;
				return false;
			}
		});
		
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
	
	@Override
	public void finish(){
		super.finish();
	}

	public void l(String str){
		System.out.println(str);
	}
	
	public void markGroupDoneToday(String group_id){
		GymdbHelper.readData(getApplicationContext(), CustQuery.UPDATE_GROUP_TIME, new String[]{group_id});
	}
	
	public Cursor getNextGroup(int offset){
		String NEXT_GROUP_QUERY = String.format(CustQuery.NEXT_GROUP_AND_MUSCLES, offset);
		return GymdbHelper.readData(getApplicationContext(), NEXT_GROUP_QUERY);
	}
	
	public Cursor getNextGroup(){
		return getNextGroup(0);
	}

	public void updateTodayButtonClick(View view){
		if(current_group_id == null) {
			sendToast("Nothing to update");
			return;
		}
		markGroupDoneToday(current_group_id);
		skip_count = 0;
		setSkipTextClick();
		Random rand = new  Random();
		sendToast(SeedData.UpdateMessage[rand.nextInt(SeedData.UpdateMessage.length)]);
	}
	
	public void skipButtonClick(View view){
		skip_count++;
		if(skip_count >= SeedData.Groups.length) skip_count = 0;
		setSkipTextClick();
	}

	public void setSkipTextClick(){
		TextView view = (TextView)findViewById(R.id.skip_count_btn);
		view.setText(getString(R.string.skip_text) + (skip_count != 0 ? "("+skip_count+")" : ""));
	}
	
	public void getNextGroupButtonClick(View view){
		Cursor c = getNextGroup(skip_count);
		current_group_id = c.getString(0);

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
		
		c.close();
	}
	
	public void sendToast(String message){
		Toast t = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		t.setGravity(Gravity.CENTER,0,0);
		t.show();
	}
	
	public void exit_application(View v){
		finish();
	}
}
