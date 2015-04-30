package com.Birdoge.Birdoge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager.LayoutParams;

import java.util.Timer;
import java.util.TimerTask;

public class BirdogeGame extends Activity {

	private BirdogeView birdogeView;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	public static Timer tmr;
	public static TimerTask tsk = null;
	public static Handler handler = new Handler();
	public static Runnable runnable;
	
	int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos, mBallSpd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		getWindow().setFlags(0xFFFFFFFF, LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);
		
		birdogeView = (BirdogeView) findViewById(R.id.avoiderView);
		
		// Add sensor listener 
		// Set the screen always portrait
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		 //get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();  
        mScrWidth = display.getWidth(); 
        mScrHeight = display.getHeight();
    	mBallPos = new android.graphics.PointF();
    	mBallSpd = new android.graphics.PointF();
        
        //create variables for ball position and speed
        mBallPos.x = mScrWidth/2; 
        mBallPos.y = mScrHeight/2; 
        mBallSpd.x = 0;
        mBallSpd.y = 0; 
        		
        //listener for accelerometer, use anonymous class for simplicity
        ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
    		new SensorEventListener() {    
    			@Override  
    			public void onSensorChanged(SensorEvent event) {  
    			    //set ball speed based on phone tilt (ignore Z axis)
    				mBallSpd.x = -event.values[0];
    				mBallSpd.y = event.values[1];
    				//timer event will redraw ball
    			}
        		@Override  
        		public void onAccuracyChanged(Sensor sensor, int accuracy) {} //ignore this event
        	},
        	((SensorManager)getSystemService(Context.SENSOR_SERVICE))
        	.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	   //listener for menu button on phone
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Main Menu"); //only one menu item
        return super.onCreateOptionsMenu(menu);
    }
    
    //listener for menu item clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle item selection    
    	if (item.getTitle() == "Main Menu") //user clicked Exit
    		finish(); //will call onPause
   		return super.onOptionsItemSelected(item);    
    }
	

	@Override
	public void onPause() 
	{
		super.onPause();
	//	tmr.cancel();
	//	tmr = null;
		BirdogeView.stopGame();
        Intent j = new Intent(this,Birdoge.class);
        startActivity(j);
	}
	
    @Override
    public void onResume() //App moved to foreground (also occurs at app startup)
    {
        //create timer task to move ball to new position
//        tmr = new Timer(); 
//    	tsk = new TimerTask() {
    	runnable = new Runnable() {
    		@Override
			public void run() {
				//if debugging with external device, 
				//  a cat log viewer will be needed on the device
				android.util.Log.d(
				    "TiltBall","Timer Hit - " + mBallPos.x + ":" + mBallPos.y);
			    //move ball based on current speed
				mBallPos.x += mBallSpd.x;
				mBallPos.y += mBallSpd.y;
				//if ball goes off screen, reposition to opposite side of screen
				if (mBallPos.x > mScrWidth) mBallPos.x=0;
				if (mBallPos.y > mScrHeight) mBallPos.y=0;
				if (mBallPos.x < 0) mBallPos.x=mScrWidth;
				if (mBallPos.y < 0) mBallPos.y=mScrHeight;
				//update ball class instance
				BirdogeView.ball.x = mBallPos.x;
				BirdogeView.ball.y = mBallPos.y;
				
				BirdogeView.updatePositions();
				//redraw ball. Must run in background thread to prevent thread lock.
				handler.post(new Runnable() {
				    public void run() {	
					   birdogeView.invalidate();
				  }});
				handler.postDelayed(this, 10);
			}
		}; // TimerTask
		
//        tmr.schedule(tsk,10,10); //start timer
		
        super.onResume();
    } // onResume
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		birdogeView.releaseResources();
		System.runFinalizersOnExit(true); //wait for threads to exit before clearing app
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	
    //listener for config change. 
    //This is called when user tilts phone enough to trigger landscape view
    //we want our app to stay in portrait view, so bypass event 
    @Override 
    public void onConfigurationChanged(Configuration newConfig)
	{
       super.onConfigurationChanged(newConfig);
	}


}
