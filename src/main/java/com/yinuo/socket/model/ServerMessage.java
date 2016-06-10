package com.yinuo.socket.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器发送给终端的消息.
 * <p>
 * 服务器下发报文格式：
 * [协议版本号,唯一标识,加密方式,包校验值,时间,终端IMEI,报文类型,参数1,参数2,参数3 ……]
 * 示例
 * [V1.0.0,460001515535328,1,abcd,2011-12-15 20:00:00,355372020827303,S1,参数1,参数2,参数3,……]
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public class ServerMessage extends BaseMessage {
    public ServerMessage() {
    }

    public ServerMessage(String identifier, String time, String imei, String messageType, List<String> parameters) {
        super(identifier, time, imei, messageType, parameters);
    }

    @Override
    public String toString() {
        return "ServerMessage{} " + super.toString();
    }

    @Override
    public char getEndPointType() {
        return 'S';
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
        builder.append(imei).append(',');
        builder.append(messageType);
        parameters.forEach(p -> builder.append(',').append(p));
        builder.append(']');
        return builder.toString();
    }

    /**
     * Transform to the Terminal message
     * <p>
     * Default timezone to +08:00
     *
     * @return server message
     */
    @Override
    public Message transform() {
        return new TerminalMessage(identifier, time, imei, messageType.replace('S', 'T'), "8", new ArrayList<>(0));
    }
}
