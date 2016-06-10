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
public class BodyTempVo implements Serializable {
    String imei;
    String temperature;
    Double temp;
    String timen;
    String dateTime;

    public BodyTempVo() {
    }

    public BodyTempVo(String imei, String temperature, Double temp, String timen, String dateTime) {
        this.imei = imei;
        this.temperature = temperature;
        this.temp = temp;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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
        return "BodyTempVo{" +
                "imei='" + imei + '\'' +
                ", temperature='" + temperature + '\'' +
                ", temp=" + temp +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
