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
 * @since 2016-05-31
 */
public class ServerConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(ServerConfig.class);

    static class ServerConfigHolder {
        static final ServerConfig INSTANCE = new ServerConfig();
    }

    public static ServerConfig getInstance() {
        return ServerConfigHolder.INSTANCE;
    }

    Properties props;

    private ServerConfig() {
        props = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("server.properties");
            props.load(input);
        } catch (IOException ex) {
            LOGGER.error("load server config file error");
            throw new RuntimeException("fatal error, caused by server config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close server config file error");
                }
            }
        }
    }

    public String getHost() {
        return props.getProperty("server.host");
    }

    public String getAppId() {
        return props.getProperty("server.appId");
    }

    public String getAppSecret() {
        return props.getProperty("server.appSecret");
    }
}
