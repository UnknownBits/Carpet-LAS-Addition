package lazyalienserver.carpetlasaddition.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class DateTimeUtils {
    private static final SimpleDateFormat fullTimeFormatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private static final DateTimeFormatter nowDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter nowTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String getFullNowTime(){
        Date date=new Date(System.currentTimeMillis());
        return fullTimeFormatter.format(date);
    }
    public static String getNowDate(){
        return nowDateFormatter.format(LocalDate.now());
    }
    public static String getNowTime(){
        return nowTimeFormatter.format(LocalTime.now());
    }
}
