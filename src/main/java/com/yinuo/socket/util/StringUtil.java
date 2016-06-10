package com.yinuo.socket.util;

import java.security.MessageDigest;
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
 * @since 2016-05-29
 */
public class StringUtil {
    public static final String EMPTY = "";

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private StringUtil() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isBlank(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isNotBlank(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static String blankIfNull(String str) {
        return str == null ? "" : str;
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : str.trim();
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    public static int indexOf(String str, char searchChar) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.indexOf(searchChar);
    }

    public static int indexOf(String str, char searchChar, int startPos) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.indexOf(searchChar, startPos);
    }

    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }

    public static int indexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }

        if (searchStr.length() == 0 && startPos >= str.length()) {
            return str.length();
        }
        return str.indexOf(searchStr, startPos);
    }

    public static int lastIndexOf(String str, char searchChar) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.lastIndexOf(searchChar);
    }

    public static int lastIndexOf(String str, char searchChar, int startPos) {
        if (isEmpty(str)) {
            return -1;
        }
        return str.lastIndexOf(searchChar, startPos);
    }

    public static int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.lastIndexOf(searchStr);
    }

    public static int lastIndexOf(String str, String searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.lastIndexOf(searchStr, startPos);
    }

    public static boolean contains(String str, char searchChar) {
        if (isEmpty(str)) {
            return false;
        }
        return str.indexOf(searchChar) >= 0;
    }

    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    public static String substring(String str, int starting) {
        int start = starting;

        if (str == null) {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return EMPTY;
        }

        return str.substring(start);
    }

    public static String substring(String str, int starting, int ending) {
        int start = starting;
        int end = ending;

        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    public static String substringBetween(String str, String tag) {
        return substringBetween(str, tag, tag);
    }

    public static String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public static String[] split(String str) {
        return split(str, null, -1, false);
    }

    public static String[] split(String str, char separatorChar) {
        return split(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return split(str, separatorChars, -1, false);
    }

    public static String[] split(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        List<String> list = new ArrayList<String>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }

            lastMatch = false;

            match = true;
            i++;
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static String[] split(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            // Null separator means use whitespace
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }

                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }

                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            // standard case
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }

                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || (preserveAllTokens && lastMatch)) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    public static String replace(String text, String repl, String with, int maximum) {
        int max = maximum;

        if (text == null || isEmpty(repl) || with == null || max == 0) {
            return text;
        }

        StringBuilder buf = new StringBuilder(text.length());
        int start = 0, end = 0;
        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    public static String replaceAll(String text, String regex, String replacement, boolean caseSensitive) {
        Pattern pattern;
        if (!caseSensitive) {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regex);
        }
        return pattern.matcher(text).replaceAll(replacement);
    }

    /**
     * 将String中的所有regex匹配的字串全部替换掉
     *
     * @param string      代替换的字符串
     * @param regex       替换查找的正则表达式
     * @param replacement 替换函数
     * @return
     */
    public static String replaceAll(String string, String regex, ReplaceCallBack replacement) {
        return replaceAll(string, Pattern.compile(regex), replacement);
    }

    /**
     * 将String中的所有pattern匹配的字串替换掉
     *
     * @param string      代替换的字符串
     * @param pattern     替换查找的正则表达式对象
     * @param replacement 替换函数
     * @return
     */
    public static String replaceAll(String string, Pattern pattern, ReplaceCallBack replacement) {
        if (string == null) {
            return null;
        }
        Matcher m = pattern.matcher(string);
        if (m.find()) {
            StringBuffer sb = new StringBuffer();
            int index = 0;
            while (true) {
                m.appendReplacement(sb, replacement.replace(m.group(0), index++, m));
                if (!m.find()) {
                    break;
                }
            }
            m.appendTail(sb);
            return sb.toString();
        }
        return string;
    }

    /**
     * 将String中的regex第一次匹配的字串替换掉
     *
     * @param string      代替换的字符串
     * @param regex       替换查找的正则表达式
     * @param replacement 替换函数
     * @return
     */
    public static String replaceFirst(String string, String regex, ReplaceCallBack replacement) {
        return replaceFirst(string, Pattern.compile(regex), replacement);
    }

    /**
     * 将String中的pattern第一次匹配的字串替换掉
     *
     * @param string      代替换的字符串
     * @param pattern     替换查找的正则表达式对象
     * @param replacement 替换函数
     * @return
     */
    public static String replaceFirst(String string, Pattern pattern, ReplaceCallBack replacement) {
        if (string == null) {
            return null;
        }
        Matcher m = pattern.matcher(string);
        StringBuffer sb = new StringBuffer();
        if (m.find()) {
            m.appendReplacement(sb, replacement.replace(m.group(0), 0, m));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static interface ReplaceCallBack {
        /**
         * 将text转化为特定的字串返回
         *
         * @param text    指定的字符串
         * @param index   替换的次序
         * @param matcher Matcher对象
         * @return
         */
        public String replace(String text, int index, Matcher matcher);
    }

    /**
     * 抽象的字符串替换接口
     * <p>
     * 主要是添加了$(group)方法来替代matcher.group(group)
     *
     * @author yeyong
     */
    public static abstract class AbstractReplaceCallBack implements ReplaceCallBack {
        protected Matcher matcher;

        /*
         * (non-Javadoc)
         * @see utils.ReplaceCallBack#replace(java.lang.String, int,
         * java.util.regex.Matcher)
         */
        final public String replace(String text, int index, Matcher matcher) {
            this.matcher = matcher;
            try {
                return doReplace(text, index, matcher);
            } finally {
                this.matcher = null;
            }
        }

        /**
         * 将text转化为特定的字串返回
         *
         * @param text    指定的字符串
         * @param index   替换的次序
         * @param matcher Matcher对象
         * @return
         */
        public abstract String doReplace(String text, int index, Matcher matcher);

        /**
         * 获得matcher中的组数据
         * <p>
         * 等同于matcher.group(group)
         * <p>
         * 该函数只能在{@link #doReplace(String, int, Matcher)} 中调用
         *
         * @param group
         * @return
         */
        protected String $(int group) {
            String data = matcher.group(group);
            return data == null ? "" : data;
        }
    }

    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    public static int countMatches(String str, char c) {
        if (isEmpty(str)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(c, idx)) != -1) {
            count++;
            idx += 1;
        }
        return count;
    }

    public static boolean isAlpha(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) || (str.charAt(i) == '.') || (i == 0 && (str.charAt(i) == '-'))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    public static final String bytesToHexString(byte[] bs) {
        if (bs == null) {
            return null;
        }
        if (bs.length == 0) {
            return "";
        }
        String hexDigits = "0123456789ABCDEF";
        StringBuilder ret = new StringBuilder(bs.length * 2);
        ret.append("0x");
        for (byte b : bs) {
            ret.append(hexDigits.charAt(0x0F & (b >> 4)));
            ret.append(hexDigits.charAt(0x0F & b));
        }
        return ret.toString();
    }

    public static final String bytesToHexStringWithoutHeader(byte[] bs) {
        return bytesToHexStringWithoutHeader(bs, null);
    }

    public static final String bytesToHexStringWithoutHeader(byte[] bs, String prefix) {
        if (bs == null) {
            return null;
        }
        if (bs.length == 0) {
            return EMPTY;
        }
        String hexDigits = "0123456789ABCDEF";
        StringBuilder ret = new StringBuilder(bs.length * (2 + (prefix == null ? 0 : prefix.length())));
        for (byte b : bs) {
            if (prefix != null) {
                ret.append(prefix);
            }
            ret.append(hexDigits.charAt(0x0F & (b >> 4)));
            ret.append(hexDigits.charAt(0x0F & b));
        }
        return ret.toString();
    }

    private static byte convertHex(char chr) {
        byte us;
        if (chr >= '0' && chr <= '9') {
            us = (byte) (chr - '0');
        } else if (chr >= 'a' && chr <= 'f') {
            us = (byte) (chr - 'a' + 10);
        } else if (chr >= 'A' && chr <= 'F') {
            us = (byte) (chr - 'A' + 10);
        } else {
            return -1;
        }
        return us;
    }

    public static final byte[] hexStringToBytes(String s) {
        return hexStringToBytes(s, -1);
    }

    public static final byte[] hexStringToBytes(String s, int length) {
        String str = s;

        if (str == null) {
            return null;
        }

        byte[] bs = new byte[0];
        boolean flag = false;

        str = str.trim();
        if (str.indexOf("0x") == 0 || str.indexOf("0X") == 0) {
            str = str.substring(2, str.length());
        }

        if (length > 0 && str.length() > (length * 2)) {
            str = str.substring(0, length * 2);
        }

        if (str.length() == 0) {
            return bs;
        }

        char[] chr = str.toCharArray();
        char[] bsChr;
        int len = chr.length;
        int i;

        if (len % 2 == 0) {
            bsChr = chr;
        } else {
            len += 1;
            bsChr = new char[len];
            bsChr[0] = '0';
            for (i = 0; i < len - 1; i++) {
                bsChr[i + 1] = chr[i];
            }
        }
        bs = new byte[len / 2];

        int pos = 0;
        byte bt, bt2;
        for (i = 0; i < bsChr.length; i += 2) {
            bt = convertHex(bsChr[i]);
            bt2 = convertHex(bsChr[i + 1]);
            if (bt == -1 || bt2 == -1) {
                flag = true;
                break;
            }
            bs[pos++] = (byte) (bt * 16 + bt2);
        }

        if (flag == true) {
            bs = str.getBytes();
        }

        return bs;
    }

    public static String byte2binary(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toBinaryString(b[n] & 0XFF));
            for (int i = 1; i <= 8 - stmp.length(); i++)
                hs = hs + "0";
            hs += stmp;

            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    /**
     * 验证合法的用户名
     *
     * @param str
     * @return
     */
    public static boolean checkGoodName(String str) {
        int length = str.length();
        if (length == 0)
            return false;

        char c = 0;

        for (int i = 0; i < length; i++) {
            c = str.charAt(i);
            if ((c >= 'a') && (c <= 'z')) {
            } else if ((c >= 'A') && (c <= 'Z')) {
            } else if ((c >= '0') && (c <= '9')/* && (i != 0) */) {
            } else if (((c == '_') /* || (c == '.') || (c == '@') */) && (i != 0)) {
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证身份证号
     *
     * @param str
     * @return
     */
    public static boolean checkGoodIdcard(String str) {
        if (str == null || str.length() == 0)
            return false;
        return str.matches("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
    }

    public static String trimCommaBeginEnd(String str) {
        if (str != null && str.trim().length() > 0) {
            if (str.startsWith(",")) {
                str = str.substring(1);
            }
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String addCommaBeginEnd(String str) {
        if (str != null && str.trim().length() > 0) {
            if (!str.startsWith(",")) {
                str = "," + str;
            }
            if (!str.endsWith(",")) {
                str = str + ",";
            }
        }
        return str;
    }

    public static String md5(String s) {
        return md5(s, "UTF-8");
    }

    public static String md5(String s, String encoding) {
        try {
            byte[] strTemp;
            if (encoding != null) {
                strTemp = s.getBytes(encoding);
            } else {
                strTemp = s.getBytes();
            }
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            return bytesToHexStringWithoutHeader(md);
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "shortUrl";

        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        // 对传入网址进行 MD5 加密

        String sMD5EncryptResult = md5(key + url);
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
            // long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }

            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars;
        }
        return resUrl;
    }
}
