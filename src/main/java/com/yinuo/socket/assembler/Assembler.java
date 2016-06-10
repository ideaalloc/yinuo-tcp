package com.yinuo.socket.assembler;

import com.yinuo.socket.exception.MessageFormatException;
import com.yinuo.socket.model.Message;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public interface Assembler {
    Message handle(String message) throws MessageFormatException;
}
