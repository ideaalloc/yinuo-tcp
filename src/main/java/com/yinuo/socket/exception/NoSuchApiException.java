package com.yinuo.socket.exception;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-06
 */
public class NoSuchApiException extends Exception {
    public NoSuchApiException() {
        super();
    }

    public NoSuchApiException(String message) {
        super(message);
    }

    public NoSuchApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchApiException(Throwable cause) {
        super(cause);
    }
}
