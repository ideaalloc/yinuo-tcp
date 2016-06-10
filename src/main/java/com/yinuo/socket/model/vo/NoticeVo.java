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
public class NoticeVo implements Serializable {
    String imei;
    String type;
    String timen;
    String dateTime;
    Integer status;

    public NoticeVo() {
    }

    public NoticeVo(String imei, String type, String timen, String dateTime, Integer status) {
        this.imei = imei;
        this.type = type;
        this.timen = timen;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NoticeVo{" +
                "imei='" + imei + '\'' +
                ", type='" + type + '\'' +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", status=" + status +
                '}';
    }
}
