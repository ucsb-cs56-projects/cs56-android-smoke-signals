package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.konukoii.smokesignals.api.Command;
import com.konukoii.smokesignals.api.commands.validators.NArgValidator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class RingCommand extends NArgValidator implements Command {
    private class RingStopTask {
        private TimerTask stopTask;
        private Timer timer;

        public RingStopTask(final Ringtone r) {
            stopTask = new TimerTask() {
                @Override
                public void run() {
                    r.stop();
                }
            };

            timer = new Timer();
        }

        public void scheduleStop(int delayInMin, Runnable runnable) {
            timer.schedule(stopTask, delayInMin * 60 * 1000);
            if(runnable != null) runnable.run();
        }

        public void scheduleStop(int delayInMin) {
            scheduleStop(delayInMin, null);
        }

        public void immediateStop() {
            timer.purge();
            stopTask.run();
        }
    }

    private RingStopTask task;

    public RingCommand() {
        super(0);
        task = null;
    }

    @Override
    public String getUsage() {
        return "//ring";
    }

    public String execute(Context context, String[] args) {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }

        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        if(alert == null) return "Ring unavailable.";

        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = manager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        manager.setStreamVolume(AudioManager.STREAM_ALARM, maxVolume, 0);

        manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        final Ringtone r = RingtoneManager.getRingtone(context, alert);
        r.play();

        task = new RingStopTask(r);
        task.scheduleStop(2, new Runnable() {
            @Override
            public void run() {
                task = null;
            }
        });

        return "Phone will now ring for the next 2 minutes!";
    }

    public void stopRinging() {
        if(task != null) {
            task.immediateStop();
        }
    }
}
