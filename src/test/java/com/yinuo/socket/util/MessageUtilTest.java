package com.yinuo.socket.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Bill Lv on 6/3/16.
 */
public class MessageUtilTest {
    static final Logger LOGGER = Logger.getLogger(MessageUtilTest.class);

    @Test
    public void parseMessage() throws Exception {
        final String message = "[V1.0.0,460001515535328,1,abcd,2016-06-03 23:36:35,356511170035899,S1][V1.0.0,460001515535328,1,abcd,2016-06-03 23:36:36,356511170035899,S2]";
        final List<String> groups = MessageUtil.parseMessage(message);
        LOGGER.info("groups: " + groups.toString());
    }

}