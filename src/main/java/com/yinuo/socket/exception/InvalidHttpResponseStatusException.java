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
public class InvalidHttpResponseStatusException extends Exception {
    int code;

    public InvalidHttpResponseStatusException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
