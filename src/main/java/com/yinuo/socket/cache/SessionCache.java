package com.yinuo.socket.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yinuo.socket.client.AuthClient;
import com.yinuo.socket.config.DataSourceConfig;
import io.vertx.core.Vertx;
import io.vertx.ext.jdbc.JDBCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.yinuo.socket.constant.Constants.JDBC_CLIENT_ID;

/**
 * Session Cache.
 * <p>
 * To be replace by Redis storage in near future.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class SessionCache {
    static final Logger LOGGER = LoggerFactory.getLogger(SessionCache.class);
    static final AuthClient authClient = AuthClient.getInstance();

    static class SessionCacheHolder {
        static final SessionCache INSTANCE = new SessionCache();
    }

    public static SessionCache getInstance() {
        return SessionCacheHolder.INSTANCE;
    }

    Cache<String, String> tokenCache;
    Cache<String, String> actorCache;
    Cache<String, JDBCClient> jdbcClientCache;

    private SessionCache() {
        tokenCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build();
        actorCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(67, TimeUnit.MINUTES)
                .build();
        jdbcClientCache = CacheBuilder.newBuilder()
                .maximumSize(1)
                .build();
    }

    public JDBCClient getJDBCClient(Vertx vertx) {
        try {
            return jdbcClientCache.get(JDBC_CLIENT_ID, () -> JDBCClient.create(vertx, DataSourceConfig.getInstance().getDataSource()));
        } catch (ExecutionException e) {
            LOGGER.error("create jdbc client error");
            return null;
        }
    }

    public synchronized String retrieveToken(String clientId) {
        try {
            return tokenCache.get(clientId, () -> authClient.getToken().getToken());
        } catch (ExecutionException e) {
            LOGGER.error("call client service error");
            return null;
        }
    }

    public synchronized void invalidateToken(String clientId) {
        tokenCache.invalidate(clientId);
    }

    public synchronized void registerActor(String imei, String actor) {
        actorCache.put(imei, actor);
    }

    public synchronized String retrieveActor(String imei) {
        return actorCache.getIfPresent(imei);
    }

    public synchronized void invalidateActor(String actor) {
        final ConcurrentMap<String, String> imeiActorMap = actorCache.asMap();
        String theKey = null;
        for (String imei : imeiActorMap.keySet()) {
            if (imeiActorMap.get(imei).equals(actor)) {
                theKey = imei;
                break;
            }
        }
        if (theKey != null) {
            imeiActorMap.remove(theKey);
        }
    }
}
