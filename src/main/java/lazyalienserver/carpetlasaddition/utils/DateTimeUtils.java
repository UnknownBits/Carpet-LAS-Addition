package lazyalienserver.carpetlasaddition.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTimeUtils {
    private static final SimpleDateFormat fullTimeFormatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private static final DateTimeFormatter nowDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter nowTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final Timer timer=new Timer();
    private static final ArrayList<Runnable> DayScheduleEventList=new ArrayList<>();

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

    public static void initDayScheduleEvent(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Date date=calendar.getTime();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getNowTime().equals(DateFormatUtils.format(new Date(), "yy-MM-dd") + " 00:00:00")){
                    for (Runnable run:DayScheduleEventList){
                        run.run();
                    }
                }
            }
        },date,1000 * 60 * 60 * 24);
    }

    public static void addDayScheduleEvent(Runnable run){
        for (Runnable runnable:DayScheduleEventList){
            if (run.equals(runnable)){
                return;
            }
        }
        DayScheduleEventList.add(run);
    }
}
