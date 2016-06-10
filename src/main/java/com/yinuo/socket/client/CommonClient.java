package com.yinuo.socket.client;

import com.yinuo.socket.config.ClientConfig;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-26
 */
public abstract class CommonClient extends BaseClient {
    static final ClientConfig clientConfig = ClientConfig.getInstance();

    @Override
    protected CloseableHttpClient createClient() {
        Integer requestTimeout = clientConfig.getRequestTimeout();
        Integer connectTimeout = clientConfig.getConnectTimeout();
        Integer socketTimeout = clientConfig.getSocketTimeout();

        RequestConfig requestConfig = RequestConfig.custom().
                setConnectionRequestTimeout(requestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();

        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
        return builder.build();
    }

    @Override
    protected String getUserAgent() {
        return "Mozilla/5.0";
    }
}
