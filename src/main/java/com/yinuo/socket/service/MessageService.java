package com.yinuo.socket.service;

import com.yinuo.socket.exception.NoSuchApiException;
import com.yinuo.socket.model.TerminalMessage;
import com.yinuo.socket.model.vo.*;
import com.yinuo.socket.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public class MessageService {
    static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    final ClientService clientService;

    public MessageService(ClientService clientService) {
        this.clientService = clientService;
    }

    public int invokeRemoteApi(final TerminalMessage device) throws IOException, NoSuchApiException {
        switch (device.getInstruction()) {
            case 28:
                HeartRateVo heartRateVo = new HeartRateVo();
                heartRateVo.setImei(device.getImei());
                heartRateVo.setMtPulse(device.getParameters().get(0));
                heartRateVo.setHeartRate(device.getParameters().get(1));
                heartRateVo.setTimen(device.getParameters().get(2));
                heartRateVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addHeartRate(heartRateVo).getCode();
            case 51:
                BodyTempVo bodyTempVo = new BodyTempVo();
                bodyTempVo.setImei(device.getImei());
                bodyTempVo.setTemperature(device.getParameters().get(0));
                bodyTempVo.setTemp(new BigDecimal(device.getParameters().get(1)).doubleValue());
                bodyTempVo.setTimen(device.getParameters().get(2));
                bodyTempVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addBodyTemp(bodyTempVo).getCode();
            case 52:
                LbsLocVo lbsLocVo = new LbsLocVo();
                lbsLocVo.setImei(device.getImei());
                lbsLocVo.setLbs(device.getParameters().get(0));
                lbsLocVo.setMnc(device.getParameters().get(1));
                lbsLocVo.setLac(device.getParameters().get(2));
                lbsLocVo.setCellid(device.getParameters().get(3));
                lbsLocVo.setTimen(device.getParameters().get(4));
                lbsLocVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addLbsLoc(lbsLocVo).getCode();
            case 86:
                MlbsLocVo mlbsLocVo = new MlbsLocVo();
                mlbsLocVo.setImei(device.getImei());
                mlbsLocVo.setVoltage(new BigDecimal(device.getParameters().get(0)).doubleValue());
                mlbsLocVo.setTimen(device.getParameters().get(2));
                String str = device.getParameters().get(1);

                String[] array = str.split("\\$");
                int num = Integer.parseInt(array[1]);
                String mcc = array[2];
                String mnc = array[3];
                str = array[4];
                String response = str;
                if (num > 0) {
                    int index = str.indexOf('#');
                    response = str.substring(0, index);
                    str = str.substring(index + 1);
                }

                mlbsLocVo.setNum(num);
                mlbsLocVo.setMcc(mcc);
                mlbsLocVo.setMnc(mnc);
                mlbsLocVo.setResponse(Integer.parseInt(response));
                mlbsLocVo.setMmsgX(getMmsgX(num, array, str));
                mlbsLocVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addMlbsLoc(mlbsLocVo).getCode();
            case 53:
                GpsLocVo gpsLocVo = new GpsLocVo();
                gpsLocVo.setImei(device.getImei());
                gpsLocVo.setGps(device.getParameters().get(0));
                gpsLocVo.setLongitude(new BigDecimal(device.getParameters().get(1)).doubleValue());
                gpsLocVo.setLatitude(new BigDecimal(device.getParameters().get(2)).doubleValue());
                gpsLocVo.setHeight(new BigDecimal(device.getParameters().get(3)).doubleValue());
                gpsLocVo.setSpeed(new BigDecimal(device.getParameters().get(4)).doubleValue());
                gpsLocVo.setDirection(new BigDecimal(device.getParameters().get(5)).doubleValue());
                gpsLocVo.setTimen(device.getParameters().get(6));
                gpsLocVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addGpsLoc(gpsLocVo).getCode();
            case 30:
                StepsCountVo stepsCountVo = new StepsCountVo();
                stepsCountVo.setImei(device.getImei());
                stepsCountVo.setPedo(device.getParameters().get(0));
                stepsCountVo.setTotalSteps(Integer.parseInt(device.getParameters().get(1)));
                stepsCountVo.setTotalDis(Integer.parseInt(device.getParameters().get(2)));
                stepsCountVo.setCalories(new BigDecimal(device.getParameters().get(3)).doubleValue());
                stepsCountVo.setTimen(device.getParameters().get(4));
                stepsCountVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addStepsCount(stepsCountVo).getCode();
            case 45:
                BloodPresVo bloodPresVo = new BloodPresVo();
                bloodPresVo.setImei(device.getImei());
                bloodPresVo.setBloodPressure(device.getParameters().get(0));
                bloodPresVo.setSystolicPressures(Integer.parseInt(device.getParameters().get(1)));
                bloodPresVo.setDiastolicPressures(Integer.parseInt(device.getParameters().get(2)));
                bloodPresVo.setBlood(Integer.parseInt(device.getParameters().get(3)));
                bloodPresVo.setTimen(device.getParameters().get(4));
                bloodPresVo.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addBloodPres(bloodPresVo).getCode();
            case 62:
                BloodPresVo bloodPresWithT = new BloodPresVo();
                bloodPresWithT.setImei(device.getImei());
                bloodPresWithT.setBloodPressure(device.getParameters().get(0));
                bloodPresWithT.setSystolicPressures(Integer.parseInt(device.getParameters().get(1)));
                bloodPresWithT.setDiastolicPressures(Integer.parseInt(device.getParameters().get(2)));
                bloodPresWithT.setBlood(Integer.parseInt(device.getParameters().get(3)));
                bloodPresWithT.setTime(device.getParameters().get(4));
                bloodPresWithT.setTimen(device.getParameters().get(5));
                bloodPresWithT.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addBloodPres(bloodPresWithT).getCode();
            case 63:
                BloodGVo bloodGT = new BloodGVo();
                bloodGT.setImei(device.getImei());
                bloodGT.setBloodSugar(device.getParameters().get(0));
                bloodGT.setBloodGlucoseValue(new BigDecimal(device.getParameters().get(1)).doubleValue());
                bloodGT.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                bloodGT.setTime(device.getParameters().get(2));
                bloodGT.setTimen(device.getParameters().get(3));
                return clientService.addBloodG(bloodGT).getCode();
            case 56:
                BloodGVo bloodG = new BloodGVo();
                bloodG.setImei(device.getImei());
                bloodG.setBloodSugar(device.getParameters().get(0));
                bloodG.setBloodGlucoseValue(new BigDecimal(device.getParameters().get(1)).doubleValue());
                bloodG.setTimen(device.getParameters().get(2));
                bloodG.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addBloodG(bloodG).getCode();
            case 90:
                SleepM1Vo sleepM1 = new SleepM1Vo();
                sleepM1.setImei(device.getImei());
                sleepM1.setSleepinfo(device.getParameters().get(0));
                sleepM1.setStartTime(device.getParameters().get(1));
                sleepM1.setEndTime(device.getParameters().get(2));
                sleepM1.setDuration(device.getParameters().get(3));
                sleepM1.setQuietPercent(Integer.parseInt(device.getParameters().get(4)));
                sleepM1.setDeepPercent(Integer.parseInt(device.getParameters().get(5)));
                sleepM1.setTimen(device.getParameters().get(6));
                sleepM1.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addSleepM1(sleepM1).getCode();
            case 91:
                SleepM2Vo sleepM2 = new SleepM2Vo();
                sleepM2.setImei(device.getImei());
                sleepM2.setSleepInfo(device.getParameters().get(0));
                sleepM2.setStartTime(device.getParameters().get(1));
                sleepM2.setEndTime(device.getParameters().get(2));
                sleepM2.setDuration(device.getParameters().get(3));
                sleepM2.setQuietPercent(Integer.parseInt(device.getParameters().get(4)));
                sleepM2.setHeartPercent(Integer.parseInt(device.getParameters().get(5)));
                sleepM2.setMinHeart(Integer.parseInt(device.getParameters().get(6)));
                sleepM2.setMaxHeart(Integer.parseInt(device.getParameters().get(7)));
                sleepM2.setMeanHeart(new BigDecimal(device.getParameters().get(8)).doubleValue());
                str = device.getParameters().get(9);
                int index = str.indexOf("|");
                if (index != -1) {
                    sleepM2.setGcount(str.substring(0, index));
                    sleepM2.setHcount(str.substring(index + 1));
                }
                sleepM2.setTimen(device.getParameters().get(10));
                sleepM2.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addSleepM2(sleepM2).getCode();
            case 82:
                NoticeVo notice = new NoticeVo();
                notice.setImei(device.getImei());
                notice.setType(device.getParameters().get(0));
                notice.setTimen(device.getParameters().get(1));
                notice.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addBatteryNotice(notice).getCode();
            case 88:
                notice = new NoticeVo();
                notice.setImei(device.getImei());
                notice.setTimen(device.getParameters().get(0));
                notice.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addSitNotice(notice).getCode();
            case 89:
                SosMsgVo sosMsg = new SosMsgVo();
                sosMsg.setImei(device.getImei());

                str = device.getParameters().stream().collect(Collectors.joining(","));
                index = str.lastIndexOf(',');
                String timen = str.substring(index + 1);
                str = str.substring(0, index - 1);
                index = str.lastIndexOf(",|");
                String content = str.substring(index + 2);
                str = str.substring(0, index);

                array = str.split("\\$");
                num = Integer.parseInt(array[1]);
                mcc = array[2];
                mnc = array[3];
                str = array[4];
                response = str;
                if (num > 0) {
                    index = str.indexOf('#');
                    response = str.substring(0, index);
                    str = str.substring(index + 1);
                }
                sosMsg.setNum(num);
                sosMsg.setMcc(mcc);
                sosMsg.setMnc(mnc);
                sosMsg.setResponse(Integer.parseInt(response));
                sosMsg.setContent(content);
                sosMsg.setTimen(timen);
                sosMsg.setMmsgX(getMmsgX(num, array, str));
                sosMsg.setDateTime(DateUtil.uploadDate(device.getTime(), device.getTimezone()));
                return clientService.addSosMsg(sosMsg).getCode();
            default:
                throw new NoSuchApiException();
        }
    }

    private List<Map<String, String>> getMmsgX(int num, String[] array, String str) {
        if (num > 0) {
            List<Map<String, String>> mmsgX = new ArrayList<>();
            array = str.split("#");
            for (String a : array) {
                String[] array2 = a.split("\\|");
                Map<String, String> mmsg = new HashMap<>();
                mmsg.put("lacX", array2[0]);
                mmsg.put("cellidX", array2[1]);
                mmsg.put("arfcnX", array2[2]);
                mmsg.put("rxlevX", array2[3]);
                mmsgX.add(mmsg);
            }
            return mmsgX;
        }
        return null;
    }
}
