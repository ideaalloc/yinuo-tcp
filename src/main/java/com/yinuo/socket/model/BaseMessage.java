package com.yinuo.socket.model;

import com.yinuo.socket.config.MessageTypeConfig;
import com.yinuo.socket.exception.MessageFormatException;

import java.util.List;

import static com.yinuo.socket.util.MessageUtil.getTypeNumber;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public abstract class BaseMessage implements Message {
    static final MessageTypeConfig messageTypeConfig = MessageTypeConfig.getInstance();

    protected String version = messageTypeConfig.getVersion();
    protected String identifier;
    protected String encryptType = messageTypeConfig.getEncryptType();
    protected String checkValue = messageTypeConfig.getCheckValue();
    protected String time;
    protected String imei;
    protected String messageType;
    protected List<String> parameters;

    public BaseMessage() {
    }

    public BaseMessage(String identifier, String time, String imei, String messageType, List<String> parameters) {
        this.identifier = identifier;
        this.time = time;
        this.imei = imei;
        this.messageType = messageType;
        this.parameters = parameters;
    }

    @Override
    public int getInstruction() {
        try {
            return getTypeNumber(messageType);
        } catch (MessageFormatException e) {
            return 0;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "version='" + version + '\'' +
                ", identifier='" + identifier + '\'' +
                ", encryptType='" + encryptType + '\'' +
                ", checkValue='" + checkValue + '\'' +
                ", time='" + time + '\'' +
                ", imei='" + imei + '\'' +
                ", messageType='" + messageType + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
