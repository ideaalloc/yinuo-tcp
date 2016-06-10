package com.yinuo.socket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class MessageTypeConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(MessageTypeConfig.class);

    static class MessageTypeConfigHolder {
        static final MessageTypeConfig INSTANCE = new MessageTypeConfig();
    }

    public static MessageTypeConfig getInstance() {
        return MessageTypeConfigHolder.INSTANCE;
    }

    Set<Integer> terminalInstructions;

    Set<Integer> serverInstructions;

    String version;
    String encryptType;
    String checkValue;
    String terminalType;

    private MessageTypeConfig() {
        InputStream input = getClass().getClassLoader().getResourceAsStream("message-type.yaml");
        Yaml yaml = new Yaml();
        Map<String, Object> messageTypeMap = (Map<String, Object>) yaml.load(input);
        final List<Integer> responseDeviceTypeList = (List<Integer>) messageTypeMap.get("terminalInstructions");
        terminalInstructions = new HashSet<>(responseDeviceTypeList);
        final List<Integer> instructDeviceTypeList = (List<Integer>) messageTypeMap.get("serverInstructions");
        serverInstructions = new HashSet<>(instructDeviceTypeList);
        version = (String) messageTypeMap.get("version");
        encryptType = String.valueOf(messageTypeMap.get("encryptType"));
        checkValue = (String) messageTypeMap.get("checkValue");
        terminalType = (String) messageTypeMap.get("terminalType");
    }

    public boolean isAvailableTerminalInstruction(int terminalInstruction) {
        return terminalInstructions.contains(terminalInstruction);
    }

    public boolean isAvailableServerInstruction(int serverInstruction) {
        return serverInstructions.contains(serverInstruction);
    }

    public String getVersion() {
        return version;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public String getTerminalType() {
        return terminalType;
    }
}
