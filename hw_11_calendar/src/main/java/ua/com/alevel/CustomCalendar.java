package ua.com.alevel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class CustomCalendar {

    private long timeMillis;

    CustomCalendar(){
        this.timeMillis = System.currentTimeMillis();
    }
    CustomCalendar(String format){this.timeMillis = parseDateTime(format);}
    CustomCalendar(long time){this.timeMillis = time;}

    public static  CustomCalendar now(){
        return new CustomCalendar();
    }

    public long getTimeMillis(){
        return this.timeMillis;
    }

    public void set(int year, int month){
        set(year, month, 1,0,0,0,0);
    }

    public void set(int year, int month, int day, int hour, int minutes){
        set(year,month,day,hour,minutes, 0,0);
    }

    public void set(int year, int month, int day, int hour, int minutes, int seconds, int milliseconds){
        LocalDate date = LocalDate.of(year,month,day);
        LocalDateTime localDateTime = date.atTime(hour,minutes,seconds,milliseconds * 1000000);
        this.timeMillis = localDateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void set(String format){
        this.timeMillis = parseDateTime(format);
    }

    private long parseDateTime(String format) {
        LocalDateTime dateTime = null;
        if (format.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2} \\d{3}$")){
            dateTime = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
        } else if (format.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")) {
            dateTime = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } else if (format.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")) {
            dateTime = LocalDateTime.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (format.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            LocalDate date = LocalDate.parse(format, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateTime = date.atStartOfDay();
        }
        assert dateTime != null;
        return dateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void addDate(CustomCalendar date){
        this.timeMillis += date.getTimeMillis();
    }

    public void addDate(String format){
        CustomCalendar date = new CustomCalendar(format);
        this.timeMillis += date.getTimeMillis();
    }

    public void addYears(int years){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis),java.time.ZoneId.of("UTC"));
        localDateTime = localDateTime.plusYears(years);
        this.timeMillis = localDateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void addMonths(int months){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis),java.time.ZoneId.of("UTC"));
        localDateTime = localDateTime.plusMonths(months);
        this.timeMillis = localDateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void addDays(int days){
        this.timeMillis += TimeUnit.DAYS.toMillis(days);
    }

    public void addHours(int hours){
        this.timeMillis += TimeUnit.HOURS.toMillis(hours);
    }

    public void addMinutes(int minutes){
        this.timeMillis += TimeUnit.MINUTES.toMillis(minutes);
    }

    public void addSeconds(int seconds){
        this.timeMillis += TimeUnit.SECONDS.toMillis(seconds);
    }

    public void addMilliseconds(int milliseconds){
        this.timeMillis += TimeUnit.MILLISECONDS.toMillis(milliseconds);
    }

    public void minusDate(CustomCalendar date){
        this.timeMillis -= date.getTimeMillis();
    }

    public void minusDate(String format){
        CustomCalendar date = new CustomCalendar(format);
        this.timeMillis -= date.getTimeMillis();
    }

    public void minusYears(int years){
        LocalDateTime dateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis), java.time.ZoneId.of("UTC"));
        dateTime = dateTime.minusYears(years);
        this.timeMillis = dateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void minusMonths(int months){
        LocalDateTime dateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis), java.time.ZoneId.of("UTC"));
        dateTime = dateTime.minusMonths(months);
        this.timeMillis = dateTime.atZone(java.time.ZoneId.of("UTC")).toInstant().toEpochMilli();
    }

    public void minusDays(int days){
        this.timeMillis -= TimeUnit.DAYS.toMillis(days);
    }

    public void minusHours(int hours){
        this.timeMillis -= TimeUnit.HOURS.toMillis(hours);
    }

    public void minusMinutes(int minutes){
        this.timeMillis -= TimeUnit.MINUTES.toMillis(minutes);
    }

    public void minusSeconds(int seconds){
        this.timeMillis -= TimeUnit.SECONDS.toMillis(seconds);
    }

    public void minusMilliseconds(int milliseconds){
        this.timeMillis -= TimeUnit.MILLISECONDS.toMillis(milliseconds);
    }

    public long getYear() {
        LocalDate date = LocalDate.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis), java.time.ZoneId.of("UTC"));
        return date.getYear();
    }

    public long getMonth() {
        LocalDate date = LocalDate.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis), java.time.ZoneId.of("UTC"));
        return date.getMonthValue();
    }

    public long differenceInYears(CustomCalendar first, CustomCalendar second){
        long firstYear = first.getYear();
        long secondYear = second.getYear();

        return Math.abs(secondYear - firstYear);
    }
    public long differenceInMonths(CustomCalendar first, CustomCalendar second){
        long diff = 0;
        long firstMonth = first.getMonth();
        long secondMonth = second.getMonth();
        long firstYear = first.getYear();
        long secondYear = second.getYear();
        if(firstYear > secondYear || (firstYear == secondYear && firstMonth > secondMonth))
        {
            CustomCalendar temp = first;
            first = second;
            second = temp;
            firstMonth = first.getMonth();
            secondMonth = second.getMonth();
            firstYear = first.getYear();
            secondYear = second.getYear();
        }
        while (firstYear < secondYear || (firstYear == secondYear && firstMonth < secondMonth)){
            diff++;
            firstMonth++;
            if(firstMonth>12){
                firstMonth = 1;
                firstYear++;
            }
        }
        return diff;
    }
    public long differenceInDays(CustomCalendar first, CustomCalendar second){
        long diff = differenceInMilliseconds(first,second);
        return diff / (24 * 60 * 60 * 1000);
    }
    public long differenceInHours(CustomCalendar first, CustomCalendar second){
        long diff = differenceInMilliseconds(first,second);
        return diff / (60 * 60 * 1000);
    }
    public long differenceInMinutes(CustomCalendar first, CustomCalendar second){
        long diff = differenceInMilliseconds(first,second);
        return diff / (60 * 1000);
    }
    public long differenceInSeconds(CustomCalendar first, CustomCalendar second){
        long diff = differenceInMilliseconds(first,second);
        return diff / 1000;
    }
    public long differenceInMilliseconds(CustomCalendar first, CustomCalendar second){
        long firstTime = first.getTimeMillis();
        long secondTine = second.getTimeMillis();
        return Math.abs(secondTine - firstTime);
    }

    public String format(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this.timeMillis), java.time.ZoneId.of("UTC"));
        return dateTime.format(formatter);
    }
}