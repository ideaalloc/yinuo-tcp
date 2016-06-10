package com.yinuo.socket.service;

import com.alibaba.fastjson.JSON;
import com.yinuo.socket.client.ApiClient;
import com.yinuo.socket.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * API服务器提供的服务.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class ClientService {
    static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    static final ApiClient apiClient = ApiClient.getInstance();

    public CommonResponse addHeartRate(HeartRateVo heartRateVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/heart-rate/add", JSON.toJSONString(heartRateVo)),
                CommonResponse.class);
    }

    public CommonResponse addBodyTemp(BodyTempVo bodyTempVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/body-temp/add", JSON.toJSONString(bodyTempVo)),
                CommonResponse.class);
    }

    public CommonResponse addLbsLoc(LbsLocVo lbsLocVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/loc/lbs/add", JSON.toJSONString(lbsLocVo)),
                CommonResponse.class);
    }

    public CommonResponse addMlbsLoc(MlbsLocVo mlbsLocVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/loc/mlbs/add", JSON.toJSONString(mlbsLocVo)),
                CommonResponse.class);
    }

    public CommonResponse addGpsLoc(GpsLocVo gpsLocVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/loc/gps/add", JSON.toJSONString(gpsLocVo)),
                CommonResponse.class);
    }

    public CommonResponse addStepsCount(StepsCountVo stepsCountVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/steps-count/add", JSON.toJSONString(stepsCountVo)),
                CommonResponse.class);
    }

    public CommonResponse addBloodPres(BloodPresVo bloodPresVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/blood-pres/add", JSON.toJSONString(bloodPresVo)),
                CommonResponse.class);
    }

    public CommonResponse addBloodG(BloodGVo bloodGVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/blood-g/add", JSON.toJSONString(bloodGVo)),
                CommonResponse.class);
    }

    public CommonResponse addSleepM1(SleepM1Vo sleepM1Vo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/sleep-m1/add", JSON.toJSONString(sleepM1Vo)),
                CommonResponse.class);
    }

    public CommonResponse addSleepM2(SleepM2Vo sleepM2Vo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/physiological-index/sleep-m2/add", JSON.toJSONString(sleepM2Vo)),
                CommonResponse.class);
    }

    public CommonResponse addBatteryNotice(NoticeVo noticeVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/notice/battery/add", JSON.toJSONString(noticeVo)),
                CommonResponse.class);
    }

    public CommonResponse addSitNotice(NoticeVo noticeVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/notice/sit/add", JSON.toJSONString(noticeVo)),
                CommonResponse.class);
    }

    public CommonResponse addSosMsg(SosMsgVo sosMsgVo) throws IOException {
        return JSON.parseObject(
                apiClient.postApi("/v1/notice/sos/add", JSON.toJSONString(sosMsgVo)),
                CommonResponse.class);
    }

}
