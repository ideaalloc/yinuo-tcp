package com.yinuo.socket.assembler;

import com.yinuo.socket.config.MessageTypeConfig;
import com.yinuo.socket.exception.MessageFormatException;
import com.yinuo.socket.model.Message;
import com.yinuo.socket.model.ServerMessage;
import com.yinuo.socket.model.TerminalMessage;
import com.yinuo.socket.util.DateUtil;
import com.yinuo.socket.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

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
public class MessageAssembler implements Assembler {
    static final Logger LOGGER = LoggerFactory.getLogger(MessageAssembler.class);
    static final MessageTypeConfig messageTypeConfig = MessageTypeConfig.getInstance();

    @Override
    public Message handle(String message) throws MessageFormatException {
        if (StringUtils.isBlank(message)) {
            LOGGER.info("blank message");
            return null;
        }
        final String msg = message.trim();
        if (!(msg.startsWith("[") && msg.endsWith("]"))) {
            LOGGER.error("not start with [ and end with ]");
            return null;
        }

        final String msgBody = msg.substring(1, msg.length() - 1);
        final String[] props = msgBody.split(",");
        if (props.length < 7) {
            throw new MessageFormatException("props number incorrect");
        }
        if (!messageTypeConfig.getVersion().equals(StringUtils.trim(props[0]))) {
            throw new MessageFormatException("invalid protocol version");
        }
        if (!messageTypeConfig.getEncryptType().equals(StringUtils.trim(props[2]))) {
            throw new MessageFormatException("invalid encrypt type");
        }
        if (!messageTypeConfig.getCheckValue().equals(StringUtils.trim(props[3]))) {
            throw new MessageFormatException("invalid check value");
        }
        final String identifier = StringUtils.trim(props[1]);
        if (StringUtils.isEmpty(identifier)) {
            throw new MessageFormatException("invalid identifier");
        }
        if (!DateUtil.isValidDatetime(props[4])) {
            throw new MessageFormatException("invalid date time");
        }
        final String time = StringUtils.trim(props[4]);
        if (messageTypeConfig.getTerminalType().equals(StringUtils.trim(props[5]))) {
            // Terminal endpoint message intent
            if (props.length < 9) {
                throw new MessageFormatException("props number incorrect for terminal endpoint message");
            }
            final TerminalMessage terminalMessage = new TerminalMessage();
            terminalMessage.setIdentifier(identifier);
            terminalMessage.setTime(time);
            if (!DateUtil.isValidTimezone(props[7])) {
                throw new MessageFormatException("invalid time zone");
            }
            terminalMessage.setTimezone(StringUtil.trim(props[7]));
            final String imei = StringUtils.trim(props[6]);
            if (StringUtils.isEmpty(imei)) {
                throw new MessageFormatException("invalid imei");
            }
            terminalMessage.setImei(imei);
            final String messageType = StringUtils.trim(props[8]);
            if (StringUtil.isEmpty(messageType)) {
                throw new MessageFormatException("invalid message type: empty");
            }
            if (!messageType.startsWith("T")) {
                throw new MessageFormatException("invalid message type: not start with T");
            }
            if (messageType.length() <= 1) {
                throw new MessageFormatException("invalid message type: length should be greater than 1");
            }
            getTypeNumber(messageType);
            terminalMessage.setMessageType(messageType);
            if (props.length == 9) {
                terminalMessage.setParameters(new ArrayList<>(0));
            }
            final ArrayList<String> parameters = new ArrayList<>(props.length - 9);
            for (int pos = 9; pos < props.length; pos++) {
                parameters.add(StringUtils.trim(props[pos]));
            }
            terminalMessage.setParameters(parameters);
            return terminalMessage;
        } else {
            // IMEI intent, Server endpoint message intent
            final String imei = StringUtils.trim(props[5]);
            if (StringUtils.isEmpty(imei)) {
                throw new MessageFormatException("invalid imei");
            }
            final ServerMessage serverMessage = new ServerMessage();
            serverMessage.setIdentifier(identifier);
            serverMessage.setImei(imei);
            serverMessage.setTime(time);
            final String messageType = StringUtils.trim(props[6]);
            if (StringUtil.isEmpty(messageType)) {
                throw new MessageFormatException("invalid message type: empty");
            }
            if (!messageType.startsWith("S")) {
                throw new MessageFormatException("invalid message type: not start with S");
            }
            if (messageType.length() <= 1) {
                throw new MessageFormatException("invalid message type: length should be greater than 1");
            }
            getTypeNumber(messageType);
            serverMessage.setMessageType(messageType);
            if (props.length == 7) {
                serverMessage.setParameters(new ArrayList<>(0));
            }
            final ArrayList<String> parameters = new ArrayList<>(props.length - 7);
            for (int pos = 7; pos < props.length; pos++) {
                parameters.add(StringUtils.trim(props[pos]));
            }
            serverMessage.setParameters(parameters);
            return serverMessage;
        }
    }

}
