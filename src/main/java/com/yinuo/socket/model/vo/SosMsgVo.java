package com.yinuo.socket.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class SosMsgVo implements Serializable {
    String imei;
    Integer num;
    String mcc;
    String mnc;
    Integer response;
    List<Map<String, String>> mmsgX;
    String content;
    String timen;
    String dateTime;

    public SosMsgVo() {
    }

    public SosMsgVo(String imei, Integer num, String mcc, String mnc, Integer response, List<Map<String, String>> mmsgX, String content, String timen, String dateTime) {
        this.imei = imei;
        this.num = num;
        this.mcc = mcc;
        this.mnc = mnc;
        this.response = response;
        this.mmsgX = mmsgX;
        this.content = content;
        this.timen = timen;
        this.dateTime = dateTime;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Map<String, String>> getMmsgX() {
        return mmsgX;
    }

    public void setMmsgX(List<Map<String, String>> mmsgX) {
        this.mmsgX = mmsgX;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "SosMsgVo{" +
                "imei='" + imei + '\'' +
                ", num=" + num +
                ", mcc=" + mcc +
                ", mnc=" + mnc +
                ", response=" + response +
                ", mmsgX=" + mmsgX +
                ", content='" + content + '\'' +
                ", timen='" + timen + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
