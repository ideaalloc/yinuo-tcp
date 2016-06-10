package com.yinuo.socket.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bill Lv on 5/30/16.
 */
public class MessageTypeConfigTest {
    static final Logger LOGGER = LoggerFactory.getLogger(MessageTypeConfigTest.class);

    @Test
    public void isAvailableTerminalInstruction() throws Exception {
        final boolean shouldResponse = MessageTypeConfig.getInstance().isAvailableTerminalInstruction(89);
        LOGGER.info(String.valueOf(shouldResponse));
    }

    @Test
    public void isAvailableServerInstruction() throws Exception {
        final boolean shouldInstruct = MessageTypeConfig.getInstance().isAvailableServerInstruction(0);
        LOGGER.info(String.valueOf(shouldInstruct));
    }

}