package com.konukoii.smokesignals;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {

    Button click;
    Button clickWhiteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start the service from here //MainService is your service class name
        startService(new Intent(this, MainService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = (Button)findViewById(R.id.action_settings);
        click.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });
        clickWhiteList = (Button)findViewById(R.id.action_whiteList);
        clickWhiteList.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,WhiteList.class);
                startActivity(intent);
            }

        });
    }


}
