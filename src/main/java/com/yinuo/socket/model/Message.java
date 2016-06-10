package com.yinuo.socket.model;

import java.io.Serializable;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public interface Message extends Serializable {
    /**
     * End point type, T: Terminal S: Server
     *
     * @return T or S
     */
    char getEndPointType();

    /**
     * Get instruction
     *
     * @return instruction number
     */
    int getInstruction();

    /**
     * Get plain text message
     *
     * @return plain text message
     */
    String getPlainText();

    /**
     * Get IMEI
     *
     * @return Terminal IMEI, from or to
     */
    String getImei();

    /**
     * Transform into response message
     *
     * @return message
     */
    Message transform();
}
