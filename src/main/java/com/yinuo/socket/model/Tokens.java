package com.yinuo.socket.model;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class Tokens implements Serializable {
    String token;

    public Tokens() {
    }

    public Tokens(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "token='" + token + '\'' +
                '}';
    }
}
