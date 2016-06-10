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
public class BloodGVo implements Serializable {
    String imei;
    String bloodSugar;
    Double bloodGlucoseValue;
    String timen;
    String time;
    String dateTime;

    public BloodGVo() {
    }

    public BloodGVo(String imei, String bloodSugar, Double bloodGlucoseValue, String timen, String time, String dateTime) {
        this.imei = imei;
        this.bloodSugar = bloodSugar;
        this.bloodGlucoseValue = bloodGlucoseValue;
        this.timen = timen;
        this.time = time;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public Double getBloodGlucoseValue() {
        return bloodGlucoseValue;
    }

    public void setBloodGlucoseValue(Double bloodGlucoseValue) {
        this.bloodGlucoseValue = bloodGlucoseValue;
    }

    public String getTimen() {
        return timen;
    }

    public void setTimen(String timen) {
        this.timen = timen;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "BloodGVo{" +
                "imei='" + imei + '\'' +
                ", bloodSugar='" + bloodSugar + '\'' +
                ", bloodGlucoseValue=" + bloodGlucoseValue +
                ", timen='" + timen + '\'' +
                ", time='" + time + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
