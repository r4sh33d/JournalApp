package com.r4sh33d.journalapp.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static final String SHORT_FULL_DATE = "dd MMM yyyy";

    public static String getRelativeSentFromMessageWithTime(long timeInMillis) {
        String relativeTime = getRelativeTime(timeInMillis, false);
        Date date = new Date(timeInMillis);
        String msg = "Sent %s at %s";

        String clockTime = formatDate(date, "HH:mm");

        switch (relativeTime) {
            case "Today":
                return String.format(msg, "today", clockTime);
            case "Yesterday":
                return String.format(msg, "yesterday", clockTime);
            default:
                return String.format(msg, "on " + relativeTime, clockTime);
        }
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new
                SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getRelativeTime(long timeInMillis, boolean showTodayWithTime) {

        Calendar today = Calendar.getInstance();
        Calendar date = Calendar.getInstance();

        date.setTimeInMillis(timeInMillis);
        if (today.get(Calendar.YEAR) == date.get(Calendar.YEAR)) {

            if (today.get(Calendar.MONTH) != date.get(Calendar.MONTH)) {
                return formatDate(date.getTime(), "dd MMM");
            } else if (today.get(Calendar.DAY_OF_MONTH) - date.get(Calendar.DAY_OF_MONTH) > 1) {
                return formatDate(date.getTime(), "dd MMM");
            } else if (today.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
                if (showTodayWithTime) {
                    return formatDate(date.getTime(), "HH:mm");
                } else {
                    return "Today";
                }
            } else {
                return "Yesterday";
            }
        } else {
            return formatDate(date.getTime(), SHORT_FULL_DATE);
        }
    }
}
