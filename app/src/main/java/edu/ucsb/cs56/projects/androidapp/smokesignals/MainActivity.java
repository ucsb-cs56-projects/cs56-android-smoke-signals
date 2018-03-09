package edu.ucsb.cs56.projects.androidapp.smokesignals;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {

    Button click;
    Button clickWhiteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //start the service from here //MainService is your service class name
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
