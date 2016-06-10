package com.yinuo.socket.model;

import com.yinuo.socket.config.MessageTypeConfig;
import com.yinuo.socket.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 终端发送给服务器的消息.
 * <p>
 * 终端上传报文格式：
 * [协议版本号,唯一标识,加密方式,包校验值,时间,终端类型,终端IMEI,时区,报文类型,参数1,参数2,参数3 ……]
 * 示例
 * [V1.0.0,460001515535328,1,abcd,2011-12-15 10:00:00,1-2,355372020827303,8,T1,参数1,参数2,参数3,……]
 * [V1.0.0,a1d83kdeio3fg3k,1,abcd,2011-12-15 10:00:00,1-2,355372020827303,8,T88,1267511609]
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public class TerminalMessage extends BaseMessage {
    static final MessageTypeConfig messageTypeConfig = MessageTypeConfig.getInstance();

    protected String terminalType = messageTypeConfig.getTerminalType();
    protected String timezone;

    public TerminalMessage() {
    }

    public TerminalMessage(String identifier, String time, String imei, String messageType, String timezone, List<String> parameters) {
        super(identifier, time, imei, messageType, parameters);
        this.timezone = timezone;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "TerminalMessage{" +
                "terminalType='" + terminalType + '\'' +
                ", timezone='" + timezone + '\'' +
                "} " + super.toString();
    }

    @Override
    public char getEndPointType() {
        return 'T';
    }

    @Override
    public String getPlainText() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        builder.append(version).append(',');
        builder.append(identifier).append(',');
        builder.append(encryptType).append(',');
        builder.append(checkValue).append(',');
        builder.append(time).append(',');
        builder.append(terminalType).append(',');
        builder.append(imei).append(',');
        builder.append(timezone).append(',');
        builder.append(messageType);
        parameters.forEach(p -> builder.append(',').append(p));
        builder.append(']');
        return builder.toString();
    }

    /**
     * Transform to the Server message
     *
     * @return server message
     */
    @Override
    public Message transform() {
        return new ServerMessage(identifier, DateUtil.sendDate(timezone), imei, messageType.replace('T', 'S'), new ArrayList<>(0));
    }
}
