package com.example.oleksandr.dream.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

/**
 * Created by Oleksandr on 25.02.2016.
 */
public class ShowAlarm {
    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
}
