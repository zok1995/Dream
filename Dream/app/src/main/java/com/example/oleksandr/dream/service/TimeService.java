package com.example.oleksandr.dream.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    public TimeService() {
    }
    // constant
    public static final long NOTIFY_INTERVAL = 10 * 1000; // 10 seconds


    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
        simpleDateFormat.format(new Date());
        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    class TimeDisplayTimerTask extends TimerTask {
        Intent intent = new Intent(getApplicationContext(), TimeDisplayTimerTask.class);

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    final int time = getDateTime();
                    Calendar c = Calendar.getInstance();
//                        int hour = String.valueOf(c.get(Calendar.HOUR));
                    Log.i("TAG", String.valueOf(time));
//                        Log.i("TAGGG",hour);
//                        intent.putExtra("TIME", time);
                    if (time == 3){
//                            Toast.makeText(getApplicationContext(), getDateTime(),
//                                    Toast.LENGTH_SHORT).show();
                        Log.i("TAGGG","it works");
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            });
        }

        private int getDateTime() {

            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR); //String.valueOf(c.get(Calendar.HOUR));
            return hour;
        }

    }

}
