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
public class BloodPresVo implements Serializable {
    String imei;
    String bloodPressure;
    Integer systolicPressures;
    Integer diastolicPressures;
    Integer blood;
    String time;
    String timen;
    String dateTime;

    public BloodPresVo() {
    }

    public BloodPresVo(String imei, String bloodPressure, Integer systolicPressures, Integer diastolicPressures, Integer blood, String time, String timen, String dateTime) {
        this.imei = imei;
        this.bloodPressure = bloodPressure;
        this.systolicPressures = systolicPressures;
        this.diastolicPressures = diastolicPressures;
        this.blood = blood;
        this.time = time;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getSystolicPressures() {
        return systolicPressures;
    }

    public void setSystolicPressures(Integer systolicPressures) {
        this.systolicPressures = systolicPressures;
    }

    public Integer getDiastolicPressures() {
        return diastolicPressures;
    }

    public void setDiastolicPressures(Integer diastolicPressures) {
        this.diastolicPressures = diastolicPressures;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "BloodPresVo{" +
                "imei='" + imei + '\'' +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", systolicPressures=" + systolicPressures +
                ", diastolicPressures=" + diastolicPressures +
                ", blood=" + blood +
                ", time='" + time + '\'' +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
