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
public class HeartRateVo implements Serializable {
    String imei;
    String mtPulse;
    String heartRate;
    String timen;
    String dateTime;

    public HeartRateVo() {
    }

    public HeartRateVo(String imei, String mtPulse, String heartRate, String timen, String dateTime) {
        this.imei = imei;
        this.mtPulse = mtPulse;
        this.heartRate = heartRate;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMtPulse() {
        return mtPulse;
    }

    public void setMtPulse(String mtPulse) {
        this.mtPulse = mtPulse;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
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
        return "HeartRateVo{" +
                "imei='" + imei + '\'' +
                ", mtPulse='" + mtPulse + '\'' +
                ", heartRate='" + heartRate + '\'' +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
