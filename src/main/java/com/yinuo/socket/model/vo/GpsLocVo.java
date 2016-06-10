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
public class GpsLocVo implements Serializable {
    String imei;
    String gps;
    Double longitude;
    Double latitude;
    Double height;
    Double speed;
    Double direction;
    String timen;
    String dateTime;

    public GpsLocVo() {
    }

    public GpsLocVo(String imei, String gps, Double longitude, Double latitude, Double height, Double speed, Double direction, String timen, String dateTime) {
        this.imei = imei;
        this.gps = gps;
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
        this.speed = speed;
        this.direction = direction;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDirection() {
        return direction;
    }

    public void setDirection(Double direction) {
        this.direction = direction;
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
        return "GpsLocVo{" +
                "imei='" + imei + '\'' +
                ", gps='" + gps + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", height=" + height +
                ", speed=" + speed +
                ", direction=" + direction +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
