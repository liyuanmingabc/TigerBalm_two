package com.cnpay.tigerbalm.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 */
public class Base64 {
    /**
     *
     */
    static final char[] CHAR_TAB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public Base64() {
    }

    /**
     * @param data data
     * @return String
     */
    public static String encode(byte[] data) {
        return encode(data, 0, data.length, /*(StringBuffer)*/null).toString();
    }

    /**
     * @param data  data
     * @param start start
     * @param len   len
     * @param buf   buf
     * @return StringBuffer
     */
    public static StringBuffer encode(byte[] data, int start, int len, StringBuffer buf) {
        if (buf == null) {
            buf = new StringBuffer(data.length * 3 / 2);
        }

        int end = len - 3;
        int i = start;
        int n = 0;

        int d;
        while (i <= end) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8 | data[i + 2] & 255;
            buf.append(CHAR_TAB[d >> 18 & 63]);
            buf.append(CHAR_TAB[d >> 12 & 63]);
            buf.append(CHAR_TAB[d >> 6 & 63]);
            buf.append(CHAR_TAB[d & 63]);
            i += 3;
            if (n++ >= 14) {
                n = 0;
                buf.append("\r\n");
            }
        }

        if (i == start + len - 2) {
            d = (data[i] & 255) << 16 | (data[i + 1] & 255) << 8;
            buf.append(CHAR_TAB[d >> 18 & 63]);
            buf.append(CHAR_TAB[d >> 12 & 63]);
            buf.append(CHAR_TAB[d >> 6 & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            d = (data[i] & 255) << 16;
            buf.append(CHAR_TAB[d >> 18 & 63]);
            buf.append(CHAR_TAB[d >> 12 & 63]);
            buf.append("==");
        }

        return buf;
    }

    /**
     * @param c c
     * @return int
     */
    static int decode(char c) {
        if (c >= 65 && c <= 90) {
            return c - 65;
        } else if (c >= 97 && c <= 122) {
            return c - 97 + 26;
        } else if (c >= 48 && c <= 57) {
            return c - 48 + 26 + 26;
        } else {
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c);
            }
        }
    }

    /**
     * @param s s
     * @return byte[]
     */
    public static byte[] decode(String s) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            decode(s, bos);
        } catch (IOException var3) {
            throw new RuntimeException();
        }

        return bos.toByteArray();
    }

    /**
     * @param s  s
     * @param os os
     * @throws IOException IOException
     */
    public static void decode(String s, OutputStream os) throws IOException {
        int i = 0;
        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= 32) {
                ++i;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + decode(s.charAt(i + 3));
            os.write(tri >> 16 & 255);
            if (s.charAt(i + 2) == 61) {
                break;
            }

            os.write(tri >> 8 & 255);
            if (s.charAt(i + 3) == 61) {
                break;
            }

            os.write(tri & 255);
            i += 4;
        }

    }
}
