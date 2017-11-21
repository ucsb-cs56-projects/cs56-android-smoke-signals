package com.konukoii.smokesignals;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

/**
 * Created by ankushrayabhari on 11/9/17.
 */

public class RingWakeLock {
    private static PowerManager.WakeLock wakeLock;

    public static void acquireLock(Context context) {
        if(wakeLock != null) return;

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK |
                                  PowerManager.ACQUIRE_CAUSES_WAKEUP |
                                  PowerManager.ON_AFTER_RELEASE, "RingLock");
        wakeLock.acquire();
    }

    public static void releaseLock(Context context) {
        if(wakeLock == null) return;

        wakeLock.release();
        wakeLock = null;
    }
}
