package com.konukoii.smokesignals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.konukoii.smokesignals.storage.DaoManager;
import com.konukoii.smokesignals.storage.PhoneNumber;
import com.konukoii.smokesignals.storage.PhoneNumberDao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by bioburn on 2016/03/09.
 */
public class WhiteList extends Activity {
    //this is one way you can save and open a text file
    private final static String storeText ="storeText.txt";
    private EditText appendText;
    private TextView phoneNumbers;
    private DaoManager daoManager;
    private PhoneNumberDao phoneNumberDao;
    List<PhoneNumber> whiteListed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitelist_main);

        appendText=(EditText)findViewById(R.id.editText4);
        phoneNumbers=(TextView)findViewById(R.id.textView);
        daoManager = new DaoManager(getApplicationContext());
        phoneNumberDao = daoManager.getPhoneNumberDao();

        //puts up the list
        populateNumbers();
    }


    public void addNumber(View v) {
        String input = appendText.getText().toString();

        final PhoneNumber phoneNumber = new PhoneNumber(input);

        new Thread(new Runnable() {
            @Override
            public void run() {
                phoneNumberDao.addPhoneNumber(phoneNumber);
                whiteListed.add(phoneNumber);
                setNumbers(whiteListed);
            }
        }).start();

        Toast.makeText(WhiteList.this, appendText.getText(), Toast.LENGTH_LONG).show();
    }

    public void append(View v){
    }

    private void setNumbers(final List<PhoneNumber> numbers) {
        // Doing UI stuff has to be done on the main Thread

        Handler mainHandler = new Handler(getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                for (PhoneNumber phoneNumber : numbers) {
                    stringBuilder.append(phoneNumber);
                    stringBuilder.append('\n');
                }
                phoneNumbers.setText(stringBuilder.toString());
            }
        };

        mainHandler.post(myRunnable);
    }

    public void populateNumbers() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // DB queries have to be run on a separate thread
                whiteListed = phoneNumberDao.getAll();
                setNumbers(whiteListed);
            }
        }).start();
    }
}
