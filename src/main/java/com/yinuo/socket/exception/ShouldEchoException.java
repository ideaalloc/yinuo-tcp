package com.yinuo.socket.exception;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-30
 */
public class ShouldEchoException extends Exception {
    public ShouldEchoException() {
        super();
    }

    public ShouldEchoException(String message) {
        super(message);
    }

    public ShouldEchoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShouldEchoException(Throwable cause) {
        super(cause);
    }
}
