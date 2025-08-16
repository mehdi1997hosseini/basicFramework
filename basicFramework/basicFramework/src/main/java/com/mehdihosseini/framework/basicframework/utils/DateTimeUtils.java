package com.mehdihosseini.framework.basicframework.utils;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import com.github.eloyzone.jalalicalendar.JalaliDateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description : این کلاس برای تاریخ میباشد که هر آنچه که به تاریخ مربوط میشود در این کلاس موجود میباشد
 */
public final class DateTimeUtils {
    private DateTimeUtils() {
    }

    private static final DateConverter jalaliCalender = new DateConverter();
    private static final String DEFAULT_PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_PATTERN_DATE = "yyyy-MM-dd";
    private static final String DEFAULT_PATTERN_TIME = "HH:mm:ss";


    /**
     * <h3>EN : Convert string-based time to Java time</h3>
     * <h3>FA : تبدیل زمانی که بر اساس رشته است به زمان جاوایی</h3>
     *
     * @param pattern convert string by patter to localDate
     * @param time    simple time by string
     * @simple "hh:mm:ss"
     */
    public static LocalTime stringParsToTime(String pattern, String time) {
        if (pattern == null || pattern.isBlank())
            pattern = "HH:mm:ss";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.parse(time, formatter);
    }

    /**
     * <h3>EN : Convert string-based date to Java LocalDate .</h3>
     * <h3>FA : تبدیل تاریخ که بر اساس رشته است به تاریخ جاوایی</h3>
     *
     * @param pattern convert string by patter to localDate
     * @param date    simple date by string
     * @return LocalDate.class
     */
    public static LocalDate stringParsToLocalDate(String pattern, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    /**
     * <h3>EN : Convert string-based date to Java date "yyyy-MM-dd".</h3>
     * <h3>FA : تبدیل تاریخ که بر اساس رشته است به تاریخ جاوایی "yyyy-MM-dd" </h3>
     *
     * @param pattern  convert string by patter to localDate
     * @param dateTime simple dateTime by string
     * @return Date.class
     */
    public static Date stringParsToDate(String pattern, String dateTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(dateTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("can not convert string to date ... ");
        }
    }

    /**
     * <h3>EN : Date and time conversion that is sent based on the type of structure specified in</h3>
     * <h3>FA : تبدیل تاریخ و زمان که بر اساس نوع ساختاری که در مشخص شده است ارسال میشود</h3>
     *
     * @param pattern convert string by patter to localDate
     * @param date    simple date by string
     * @return LocalDateTime.class
     */
    public static LocalDateTime stringParsToLocalDateTime(String pattern, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDateTime.parse(date, formatter);
    }

    /**
     * <h3>EN : Day difference between two dates based on string value.</h3>
     * <h3>FA : اختلاف روز بین دو تاریخ بر اساس مقدار رشته ای</h3>
     *
     * @param startDate the smaller date .
     * @param endDate   the bigger date .
     * @return Long : num days difference two date based on string value.
     */
    public static long differenceDayTwoDateByStr(String pattern, String startDate, String endDate) {
        long sDate = stringParsToDate(pattern, startDate).getTime();
        long eDate = stringParsToDate(pattern, endDate).getTime();
        long differenceInMillis = eDate - sDate;
        return differenceInMillis / (1000 * 60 * 60 * 24);
    }

    /**
     * <h3>EN : Day difference between two dates.</h3>
     * <h3>FA : اختلاف روز بین دو تاریخ .</h3>
     *
     * @param startDate the smaller date .
     * @param endDate   the bigger date .
     * @return Long : num days difference two date
     */
    public static long differenceTwoDate(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * <h3>EN : Convert Gregorian date to jalali date based on string</h3>
     * <h3>FA : تبدیل تاریخ میلادی بر اساس رشته به تاریخ شمسی</h3>
     *
     * @param patternGregorian convert string by patter to localDate
     * @param gregorian        simple date gregorian by string
     * @param returnPattern    convert date by patter to jalali date
     * @return String.class jalali date based on returnPattern
     */
    public static String convertToJalaliByPattern(String patternGregorian, String gregorian, String returnPattern) {
        LocalDate localDate = stringParsToLocalDate(patternGregorian, gregorian);
        JalaliDate jalaliDate = gregorianToJalali(localDate);
        return jalaliDate.format(new JalaliDateFormatter(returnPattern));
    }

    /**
     * <h3>EN : Convert Gregorian date to jalali date</h3>
     * <h3>FA : تبدیل تاریخ میلادی به تاریخ شمسی</h3>
     *
     * @param gregorian     simple date gregorian
     * @param returnPattern convert date by patter to jalali date
     * @return String.class jalali date based on string
     */
    public static String convertToJalaliByDate(Date gregorian, String returnPattern) {
        LocalDate localDate = dateToLocalDate(gregorian);
        return gregorianToJalali(localDate).format(new JalaliDateFormatter(returnPattern.replace("M", "m")));
    }

    /**
     * <h3>EN : Convert system date to jalali date</h3>
     * <h3>FA : تبدیل تاریخ سیستم به تاریخ شمسی</h3>
     *
     * @param localDateTime simple localDateTime gregorian by zone system
     * @param returnPattern convert date by patter to jalali date
     * @return String.class jalali date based on string
     */
    public static String convertToJalaliByLocalDateTime(LocalDateTime localDateTime, String returnPattern) {
        LocalDate localDate = localDateTime.toLocalDate();
        return gregorianToJalali(localDate).format(new JalaliDateFormatter(returnPattern));
    }

    /**
     * <h3>EN : Convert Jalali date to Gregorian date based on pattern "yyyy-MM-dd hh:mm:dd" or ""</h3>
     * <h3>FA : تبدیل تاریخ جلالی به تاریخ میلادی بر اساس الگوی "yyyy-MM-dd hh:mm:dd" یا "yyyy-MM-dd" </h3>
     *
     * @param jalaliDate date jalali base on string for convert to gregorian
     * @return LocalDateTime.class
     */
    public static LocalDateTime convertToGregorian(String jalaliDate) {
        String[] getDateTime = jalaliDate.split(" ");
        String time = (getDateTime.length > 2 && !getDateTime[1].isBlank()) ? getDateTime[1] : "00:00:00";
        LocalTime localTime = stringParsToTime(DEFAULT_PATTERN_TIME, time);
        if (getDateTime[0].contains("/"))
            return jalaliToLocalDate(getDateTime[0], "/").atTime(localTime);

        else if (getDateTime[0].contains("-"))
            return jalaliToLocalDate(getDateTime[0], "-").atTime(localTime);
        else
            throw new RuntimeException("error in the convert to gregorian .");
    }

    /**
     * EN : Convert Javanese date to system date
     * FA : تبدیل تاریخ جاوایی به تاریخ سیستم
     *
     * @param date simple date gregorian
     * @return LocalDate
     */
    private static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static JalaliDate gregorianToJalali(LocalDate localDate) {
        return jalaliCalender.gregorianToJalali(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    private static LocalDate jalaliToLocalDate(String dateJalali, String separator) {
        String[] getDate = dateJalali.split(separator);
        int year = Integer.parseInt(getDate[0]);
        int month = Integer.parseInt(getDate[1]);
        int day = Integer.parseInt(getDate[2]);

        return jalaliCalender.jalaliToGregorian(year, month, day);
    }

    // در ورژن های دیگر باید متدی اضافه شود که براساس یک Enum ورودی های آن باشد و مشخص کننده نوع عملیات و بر اساس آن عملیات یکی از متد ها صدا شده شود.


}
