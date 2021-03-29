package com.example.timeflow;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.BlockingDeque;

public class Tool {
    public static String dateToString(long ts, String pattern) {
        if (0 == ts) {
            return "";
        }
        Date date = new Date(ts);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
    public static boolean isSameDay(long ts1, long ts2) {
        if (ts1 == 0 || ts2 == 0) {
            return true;
        }
        Date date1 = new Date(ts1);
        Date date2 = new Date(ts2);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        if (day1 != day2) {
            return false;
        }
        return true;
    }
    public static Date getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    public static Date getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
