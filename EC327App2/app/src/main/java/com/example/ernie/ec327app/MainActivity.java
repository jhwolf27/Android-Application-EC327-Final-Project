package com.example.ernie.ec327app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    MediaPlayer mySound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setWindowAnimations(1);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this,R.raw.mercy);
        mySound.start();
    }

    public void onClick1(View view){
        //Intent i = new Intent(this,GameActivity.class);
        startActivity(new Intent(MainActivity.this,GameActivity.class));
        mySound.pause();
    }

    public void onClick2(View view){
        Intent x = new Intent(this,HowToPlay.class);
        startActivity(x);
        mySound.pause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
