package com.yinuo.socket.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class DateUtil {
    public static final long ONE_DAY_TIMEMILLIS = 24 * 60 * 60 * 1000L;

    public static final long ONE_HOUR_TIMEMILLIS = 60 * 60 * 1000L;

    public static final long ONE_MINUTE_TIMEMILLIS = 60 * 1000L;

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    public static final String DEFAULT_TIME_PATTERN2 = "HH:mm";

    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATETIME_PATTERN1 = "yyyy-MM-dd HH:mm";

    public static final String DEFAULT_DATETIME_PATTERN2 = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DEFAULT_TIMESTAMP_TIMEZONE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS Z";

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    private DateUtil() {
    }

    public static String getDate() {
        return getDate(new Date());
    }

    public static String getTime() {
        return getTime(new Date());
    }

    public static String getDateTime() {
        return getDateTime(new Date());
    }

    public static String getDate(Date date) {
        return getDate(date, DEFAULT_DATE_PATTERN);
    }

    public static String getTime(Date date) {
        return getDate(date, DEFAULT_TIME_PATTERN);
    }

    public static boolean isValidTimezone(String timezone) {
        if (StringUtils.isBlank(timezone)) {
            return false;
        }
        try {
            Double.parseDouble(timezone);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDatetime(String datetimeString) {
        if (StringUtils.isBlank(datetimeString)) {
            return false;
        }
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetimeString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String sendDate(String timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 获得Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 把要进行运算的时间放到calendar实例中
        calendar.setTime(date);
        double zone = new BigDecimal(timezone).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() - 8;
        // 进行运算
        calendar.add(Calendar.HOUR, (int) zone);
        calendar.add(Calendar.MINUTE, (int) ((zone - (int) zone) * 60));
        System.out.println(sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime());
    }

    // 将各时区的时间
    public static String uploadDate(String time, String timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获得Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 把要进行运算的时间放到calendar实例中
        calendar.setTime(date);
        double zone = 8 - new BigDecimal(timezone).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        // 进行运算
        calendar.add(Calendar.HOUR, (int) zone);
        calendar.add(Calendar.MINUTE, (int) ((zone - (int) zone) * 60));
        System.out.println(sdf.format(calendar.getTime()));
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) {
        //uploadDate("2016-02-19 11:51:12", "5.75");
        sendDate("9");
    }

    //获取时间格式为HH:mm
    public static String getDTimeMin(String dTime) {
        try {
            if (dTime.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                sdf.applyPattern(DEFAULT_TIME_PATTERN);
                return getDate(sdf.parse(dTime), DEFAULT_TIME_PATTERN2);
            } else if (dTime.matches("\\d{1,2}:\\d{2}")) {
                sdf.applyPattern(DEFAULT_TIME_PATTERN2);
                return getDate(sdf.parse(dTime), DEFAULT_TIME_PATTERN2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dTime;
    }

    //获取时间格式为HH:mm:ss
    public static String getDTimeSec(String dTime) {
        try {
            if (dTime.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                sdf.applyPattern(DEFAULT_TIME_PATTERN);
                return getDate(sdf.parse(dTime), DEFAULT_TIME_PATTERN);
            } else if (dTime.matches("\\d{1,2}:\\d{2}")) {
                sdf.applyPattern(DEFAULT_TIME_PATTERN2);
                return getDate(sdf.parse(dTime), DEFAULT_TIME_PATTERN);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dTime;
    }

    public static String getDateTime(Date date) {
        return getDate(date, DEFAULT_DATETIME_PATTERN);
    }

    public static String getDate(String pattern) {
        return getDate(new Date(), pattern);
    }

    public static String getDate(long date, String pattern) {
        return getDate(new Date(date), pattern);
    }

    public static String getDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    public static int getWeek(String str, String pattern) {
        Date date = parse(str, pattern);
        return getWeek(date);
    }

    public static int getWeek(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SUNDAY) {
            return 7;
        } else {
            return week - 1;
        }
    }

    public static Date parseDatetime(String str) {
        return parse(str, DEFAULT_DATETIME_PATTERN);
    }

    public static Date parse(String str, String pattern) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        sdf.applyPattern(pattern);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
        }

        return date;
    }

    public static Date parse(Date date, String pattern) {
        sdf.applyPattern(pattern);
        String str = sdf.format(date);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
        }

        return date;
    }

    public static Date parseWithTimeZone(String str) {
        return parseWithTimeZone(str, DEFAULT_TIMESTAMP_TIMEZONE_PATTERN);
    }

    public static Date parseWithTimeZone(String str, String pattern) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        if (DEFAULT_TIMESTAMP_TIMEZONE_PATTERN.equals(pattern)) {
            int index1 = str.indexOf(' ');
            int index2 = str.lastIndexOf(' ');
            if (index1 < index2) {
                String str2 = str.substring(0, index2 + 1);
                str2 += str.substring(index2 + 1).replace(":", "");
                str = str2;
            } else {
                return parse(str, DEFAULT_DATETIME_PATTERN);
            }
        }
        sdf.applyPattern(pattern);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
        }

        return date;
    }

    public static Date getNextDate(Date startDate, int period, String periodBenchmark) {
        if (periodBenchmark == null) {
            throw new IllegalArgumentException("periodBenchmark can not be null");
        }
        if (period == 0) {
            return startDate;
        }
        if (startDate == null) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(startDate);
        int benchmark = periodBenchmark.charAt(0);
        switch (benchmark) {
            case 'Y':
            case 'y':
                ca.add(Calendar.YEAR, period);
                break;
            case 'M':
                ca.add(Calendar.MONTH, period);
                break;
            case 'w':
            case 'W':
                ca.add(Calendar.WEEK_OF_YEAR, period);
                break;
            case 'd':
            case 'D':
                ca.add(Calendar.DATE, period);
                break;
            case 'h':
                ca.add(Calendar.HOUR, period);
                break;
            case 'm':
                ca.add(Calendar.MINUTE, period);
                break;
            case 's':
                ca.add(Calendar.SECOND, period);
                break;
        }
        return ca.getTime();
    }

    public static long getTime(String str, String pattern) {
        if (DEFAULT_TIME_PATTERN.equals(pattern)) {
            return parse("1970-01-01 " + str, DEFAULT_DATETIME_PATTERN).getTime()
                    - parse("1970-01-01 00:00:00", DEFAULT_DATETIME_PATTERN).getTime();
        }
        return DateUtil.parse(str, pattern).getTime();
    }

    public static boolean isBefore(Date date1, Date date2) {
        return date1.before(date2);
    }

    public static boolean isNotAfter(Date date1, Date date2) {
        return date1.compareTo(date2) <= 0;
    }

    public static boolean isAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    public static boolean isNotBefore(Date date1, Date date2) {
        return date1.compareTo(date2) >= 0;
    }

    public static boolean isBefore(long date1, long date2) {
        return date1 < date2;
    }

    public static boolean isNotAfter(long date1, long date2) {
        return date1 <= date2;
    }

    public static boolean isAfter(long date1, long date2) {
        return date1 > date2;
    }

    public static boolean isNotBefore(long date1, long date2) {
        return date1 >= date2;
    }
}
