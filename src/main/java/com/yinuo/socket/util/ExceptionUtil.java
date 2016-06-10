package com.yinuo.socket.util;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-05
 */
public class ExceptionUtil {
    public static String getRootCauseMessage(final Throwable t) {
        Throwable tCursor = t;
        while (tCursor.getCause() != null) {
            tCursor = tCursor.getCause();
        }
        return tCursor.getMessage();
    }
}
