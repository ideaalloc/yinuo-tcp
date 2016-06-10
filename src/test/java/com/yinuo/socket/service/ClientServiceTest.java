package com.yinuo.socket.service;

import com.google.common.collect.ImmutableMap;
import com.yinuo.socket.model.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Bill Lv on 5/31/16.
 */
public class ClientServiceTest {
    static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceTest.class);

    ClientService clientService;

    @Before
    public void setUp() {
        clientService = new ClientService();
    }

    @Test
    public void addHeartRate() throws Exception {
        final CommonResponse commonResponse = clientService.addHeartRate(new HeartRateVo("88888882268", "8.8", "78", "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addBodyTemp() throws Exception {
        final CommonResponse commonResponse = clientService.addBodyTemp(new BodyTempVo("888885882", "37.2", 8.2, "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addLbsLoc() throws Exception {
        final CommonResponse commonResponse = clientService.addLbsLoc(new LbsLocVo("888886262", "892.245", "623.222", "263.262", "62626562", "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addMlbsLoc() throws Exception {
        final CommonResponse commonResponse = clientService.addMlbsLoc(new MlbsLocVo("8888266223", 12.5, 1, "8.8", "8.8", 0, Arrays.asList(ImmutableMap.of("key", "value")), "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addGpsLoc() throws Exception {
        final CommonResponse commonResponse = clientService.addGpsLoc(new GpsLocVo("88888822", "82.222", 88.25, 8.99, 17.8, 300.9, 282.2, "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addStepsCount() throws Exception {
        final CommonResponse commonResponse = clientService.addStepsCount(new StepsCountVo("88888822", "88", 10000, 10, 538.8, "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addBloodPres() throws Exception {
        final CommonResponse commonResponse = clientService.addBloodPres(new BloodPresVo("888882828", "892.2", 882, 999, 8, "11:09:23", "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addBloodG() throws Exception {
        final CommonResponse commonResponse = clientService.addBloodG(new BloodGVo("88888259", "882.3", 9256.2, "8", "11:10:32", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addSleepM1() throws Exception {
        final CommonResponse commonResponse = clientService.addSleepM1(new SleepM1Vo("8825626", "62235", "09:08:08", "11:08:28", "32", 8, 8, "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addSleepM2() throws Exception {
        final CommonResponse commonResponse = clientService.addSleepM2(new SleepM2Vo("82625625", "25252", "09:08:22", "10:23:22", "25", 8, 2, 8, 8, 112.2, "22", "22", "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addBatteryNotice() throws Exception {
        final CommonResponse commonResponse = clientService.addBatteryNotice(new NoticeVo("888826828", "1", "8", "2016-05-31 09:45:00", 1));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addSitNotice() throws Exception {
        final CommonResponse commonResponse = clientService.addSitNotice(new NoticeVo("88882562", "1", "8", "2016-05-31 09:45:00", 1));
        LOGGER.info(commonResponse.toString());
    }

    @Test
    public void addSosMsg() throws Exception {
        final CommonResponse commonResponse = clientService.addSosMsg(new SosMsgVo("882522525", 8, "8", "8", 1, Arrays.asList(ImmutableMap.of("key", "value")), "88", "8", "2016-05-31 09:45:00"));
        LOGGER.info(commonResponse.toString());
    }
}