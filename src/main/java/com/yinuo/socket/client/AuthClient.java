package com.yinuo.socket.client;

import com.alibaba.fastjson.JSON;
import com.yinuo.socket.config.ServerConfig;
import com.yinuo.socket.exception.InvalidHttpResponseStatusException;
import com.yinuo.socket.model.Tokens;
import com.yinuo.socket.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class AuthClient extends CommonClient {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthClient.class);
    static final ServerConfig serverConfig = ServerConfig.getInstance();

    static class AuthClientHolder {
        static final AuthClient INSTANCE = new AuthClient();
    }

    public static AuthClient getInstance() {
        return AuthClientHolder.INSTANCE;
    }

    private AuthClient() {

    }

    @Override
    protected String getCredentials() {
        return "Basic " + Base64Util.encode(serverConfig.getAppId() + ":" + serverConfig.getAppSecret());
    }

    public Tokens getToken() throws IOException {
        try {
            return JSON.parseObject(post(serverConfig.getHost() + "/v1/connect"), Tokens.class);
        } catch (InvalidHttpResponseStatusException e) {
            LOGGER.error("get token error");
            throw new RuntimeException(e);
        }
    }
}
