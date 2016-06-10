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
public class StepsCountVo implements Serializable {
    String imei;
    String pedo;
    Integer totalSteps;
    Integer totalDis;
    Double calories;
    String timen;
    String dateTime;

    public StepsCountVo() {
    }

    public StepsCountVo(String imei, String pedo, Integer totalSteps, Integer totalDis, Double calories, String timen, String dateTime) {
        this.imei = imei;
        this.pedo = pedo;
        this.totalSteps = totalSteps;
        this.totalDis = totalDis;
        this.calories = calories;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getPedo() {
        return pedo;
    }

    public void setPedo(String pedo) {
        this.pedo = pedo;
    }

    public Integer getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
    }

    public Integer getTotalDis() {
        return totalDis;
    }

    public void setTotalDis(Integer totalDis) {
        this.totalDis = totalDis;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
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
        return "StepsCountVo{" +
                "imei='" + imei + '\'' +
                ", pedo='" + pedo + '\'' +
                ", totalSteps=" + totalSteps +
                ", totalDis=" + totalDis +
                ", calories=" + calories +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
