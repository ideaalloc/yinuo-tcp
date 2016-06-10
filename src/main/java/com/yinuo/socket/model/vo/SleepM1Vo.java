package com.yinuo.socket.model.vo;

import java.io.Serializable;

/**
 * 睡眠1.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class SleepM1Vo implements Serializable {
    String imei;
    String sleepinfo;
    String startTime;
    String endTime;
    String duration;
    Integer quietPercent;
    Integer deepPercent;
    String timen;
    String dateTime;

    public SleepM1Vo() {
    }

    public SleepM1Vo(String imei, String sleepinfo, String startTime, String endTime, String duration, Integer quietPercent, Integer deepPercent, String timen, String dateTime) {
        this.imei = imei;
        this.sleepinfo = sleepinfo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.quietPercent = quietPercent;
        this.deepPercent = deepPercent;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSleepinfo() {
        return sleepinfo;
    }

    public void setSleepinfo(String sleepinfo) {
        this.sleepinfo = sleepinfo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getQuietPercent() {
        return quietPercent;
    }

    public void setQuietPercent(Integer quietPercent) {
        this.quietPercent = quietPercent;
    }

    public Integer getDeepPercent() {
        return deepPercent;
    }

    public void setDeepPercent(Integer deepPercent) {
        this.deepPercent = deepPercent;
    }

    public String getTimen() {
        return timen;
    }

    public void setTimen(String timen) {
        this.timen = timen;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "SleepM1Vo{" +
                "imei='" + imei + '\'' +
                ", sleepinfo='" + sleepinfo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                ", quietPercent=" + quietPercent +
                ", deepPercent=" + deepPercent +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
