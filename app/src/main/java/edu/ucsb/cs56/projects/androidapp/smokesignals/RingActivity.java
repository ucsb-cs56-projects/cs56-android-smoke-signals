package edu.ucsb.cs56.projects.androidapp.smokesignals;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by ankushrayabhari on 11/9/17.
 */

public class RingActivity extends Activity {
    private Button stopButton;
    private ProgressBar progressBar;
    private Runnable stopRinging;
    private CountDownTimer timer;
    static boolean active = false;

    private Ringtone getRingtone() {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        AudioManager manager = (AudioManager) this.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        manager.setStreamVolume(AudioManager.STREAM_ALARM, maxVolume, 0);

        manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        return RingtoneManager.getRingtone(this.getApplicationContext(), alert);
    }

    private void turnOnDisplay() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        RingWakeLock.acquireLock(this.getApplicationContext());
    }

    private void initializeButton() {
        stopButton = (Button) findViewById(R.id.ringstop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timer.cancel();
                stopRinging.run();
            }
        });
    }

    private void initializeTimer() {
        timer = new CountDownTimer(2 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) (1 - millisUntilFinished / (2.0 * 60 * 1000)));
            }

            @Override
            public void onFinish() {
                stopRinging.run();
            }
        };
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (active) {
            this.finishAndRemoveTask();
            return;
        }
        setContentView(R.layout.activity_ringstop);
        this.turnOnDisplay();

        final Ringtone r = this.getRingtone();
        r.play();

        stopRinging = new Runnable() {
            @Override
            public void run() {
                r.stop();
                RingActivity.this.finishAndRemoveTask();
            }
        };

        progressBar = (ProgressBar) findViewById(R.id.ringstop_progressbar);
        this.initializeButton();
        this.initializeTimer();

        active = true;
    }

    @Override
    public void finishAndRemoveTask() {
        active = false;
        RingWakeLock.releaseLock(RingActivity.this.getApplicationContext());
        super.finishAndRemoveTask();
    }
}
