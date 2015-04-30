package com.Birdoge.Birdoge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Birdoge extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick1(View view){
        //Intent i = new Intent(this,GameActivity.class);
        startActivity(new Intent(Birdoge.this,BirdogeGame.class));
    }

    public void onClick2(View view){
        Intent x = new Intent(this,HowToPlayPage1.class);
        startActivity(x);
    }

    /*
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
    }*/
}
