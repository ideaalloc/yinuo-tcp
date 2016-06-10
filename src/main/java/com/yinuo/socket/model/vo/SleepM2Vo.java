package com.yinuo.socket.model.vo;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class SleepM2Vo implements Serializable {
    String imei;
    String sleepInfo;
    String startTime;
    String endTime;
    String duration;
    Integer quietPercent;
    Integer heartPercent;
    Integer minHeart;
    Integer maxHeart;
    Double meanHeart;
    String gcount;
    String hcount;
    String timen;
    String dateTime;

    public SleepM2Vo() {
    }

    public SleepM2Vo(String imei, String sleepInfo, String startTime, String endTime, String duration, Integer quietPercent, Integer heartPercent, Integer minHeart, Integer maxHeart, Double meanHeart, String gcount, String hcount, String timen, String dateTime) {
        this.imei = imei;
        this.sleepInfo = sleepInfo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.quietPercent = quietPercent;
        this.heartPercent = heartPercent;
        this.minHeart = minHeart;
        this.maxHeart = maxHeart;
        this.meanHeart = meanHeart;
        this.gcount = gcount;
        this.hcount = hcount;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSleepInfo() {
        return sleepInfo;
    }

    public void setSleepInfo(String sleepInfo) {
        this.sleepInfo = sleepInfo;
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

    public Integer getHeartPercent() {
        return heartPercent;
    }

    public void setHeartPercent(Integer heartPercent) {
        this.heartPercent = heartPercent;
    }

    public Integer getMinHeart() {
        return minHeart;
    }

    public void setMinHeart(Integer minHeart) {
        this.minHeart = minHeart;
    }

    public Integer getMaxHeart() {
        return maxHeart;
    }

    public void setMaxHeart(Integer maxHeart) {
        this.maxHeart = maxHeart;
    }

    public Double getMeanHeart() {
        return meanHeart;
    }

    public void setMeanHeart(Double meanHeart) {
        this.meanHeart = meanHeart;
    }

    public String getGcount() {
        return gcount;
    }

    public void setGcount(String gcount) {
        this.gcount = gcount;
    }

    public String getHcount() {
        return hcount;
    }

    public void setHcount(String hcount) {
        this.hcount = hcount;
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
        return "SleepM2Vo{" +
                "imei='" + imei + '\'' +
                ", sleepInfo='" + sleepInfo + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                ", quietPercent=" + quietPercent +
                ", heartPercent=" + heartPercent +
                ", minHeart=" + minHeart +
                ", maxHeart=" + maxHeart +
                ", meanHeart=" + meanHeart +
                ", gcount='" + gcount + '\'' +
                ", hcount='" + hcount + '\'' +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
