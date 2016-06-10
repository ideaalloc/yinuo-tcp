package com.yinuo.socket.constant;

import com.yinuo.socket.config.VerticleConfig;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class Constants {
    public static final Integer PORT = VerticleConfig.getInstance().getInstancePort();
    public static final String ENCODING = "UTF-8";
    public static final String JDBC_CLIENT_ID = "yinuomsg";
}
