package com.yinuo.socket.client;

import com.yinuo.socket.cache.SessionCache;
import com.yinuo.socket.config.ServerConfig;
import com.yinuo.socket.exception.InvalidHttpResponseStatusException;
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
public class ApiClient extends CommonClient {
    static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);
    static final ServerConfig serverConfig = ServerConfig.getInstance();
    static final SessionCache sessionCache = SessionCache.getInstance();

    static class ApiClientHolder {
        static final ApiClient INSTANCE = new ApiClient();
    }

    public static ApiClient getInstance() {
        return ApiClientHolder.INSTANCE;
    }

    private ApiClient() {

    }

    @Override
    protected String getCredentials() {
        return "Bearer " + SessionCache.getInstance().retrieveToken(serverConfig.getAppId());
    }

    public String postApi(String apiUri, String jsonIn) throws IOException {
        try {
            return post(serverConfig.getHost() + apiUri, jsonIn);
        } catch (InvalidHttpResponseStatusException e) {
            sessionCache.invalidateToken(serverConfig.getAppId());
            try {
                return post(serverConfig.getHost() + apiUri, jsonIn);
            } catch (InvalidHttpResponseStatusException e1) {
                throw new RuntimeException(e1);
            }
        }
    }
}
