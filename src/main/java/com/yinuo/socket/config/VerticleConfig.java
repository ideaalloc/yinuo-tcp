package com.yinuo.socket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-05
 */
public class VerticleConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(VerticleConfig.class);

    static class VerticleConfigHolder {
        static final VerticleConfig INSTANCE = new VerticleConfig();
    }

    public static VerticleConfig getInstance() {
        return VerticleConfigHolder.INSTANCE;
    }

    Properties props;

    private VerticleConfig() {
        props = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("verticle.properties");
            props.load(input);
        } catch (IOException ex) {
            LOGGER.error("load verticle config file error");
            throw new RuntimeException("fatal error, caused by verticle config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close verticle config file error");
                }
            }
        }
    }

    public int getInstanceNum() {
        return Integer.parseInt(props.getProperty("verticle.instance.num"));
    }

    public int getInstancePort() {
        return Integer.parseInt(props.getProperty("verticle.instance.port"));
    }
}
