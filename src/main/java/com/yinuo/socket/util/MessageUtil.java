package com.yinuo.socket.util;

import com.yinuo.socket.exception.MessageFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-03
 */
public class MessageUtil {
    public static int getTypeNumber(final String messageType) throws MessageFormatException {
        try {
            return Integer.parseInt(messageType.substring(1));
        } catch (NumberFormatException e) {
            throw new MessageFormatException("invalid message type: should append with integer");
        }
    }

    public static List<String> parseMessage(final String message) {
        final String patternString = "\\[.*?\\]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(message);
        List<String> groups = new ArrayList<>();
        while (matcher.find()) {
            groups.add(matcher.group());
        }
        return groups;
    }
}
