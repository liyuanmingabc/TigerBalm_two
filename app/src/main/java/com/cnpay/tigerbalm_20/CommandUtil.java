package com.cnpay.tigerbalm_20;

/**
 * 包            名:      com.cnpay.tigerbalm_20
 * 类            名:      CommandUtil
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/5/10
 */
public class CommandUtil {

    public static void main(String[] arges) {
        //21 01 E1 43 03 E1 00 05
        //21 01 E1 43 03 E1 00 05
        byte[] bytes = {0x7E, 0x21, 0x01, (byte) 0xE1, 0x43, 0x03, (byte) 0xE1, 0x00, 0x05, (byte) 0xFF, (byte) 0xD1, 0x0D};
        int cabno = Byte.parseByte(String.valueOf(1), 16);
        int time = 5;
//        int chs = 0x21 + cabno + 0xE1 + 0x43 + 0x03 + 0xE1 + ((time >> 8) & 0xff) + (time & 0xff);
        int chs = 0x21 + 0x01 + 0xE1 + 0x83 + 0x01 + 0x01;
//        byte chs = (byte) (bytes[1] + bytes[2] + bytes[3] + bytes[4] + bytes[5] + bytes[6] + bytes[7] + bytes[8]);
        System.out.println("-----------chs = " + chs);
        int check = (~chs % 0xFFFF) + 1;
        System.out.println("-----------check = " + Integer.toHexString(check));
        int c1 = ((check >> 8) & 0xff);
        int c2 = (check & 0xff);
        System.out.println("-----------C1 =  " + Integer.toHexString(c1));
        System.out.println("-----------C2 =  " + Integer.toHexString(c2));

       /* int xx = 45;
        int xx1 = ((xx >> 8) & 0xff);
        int xx2 = (xx & 0xff);
        System.out.println("-----------xx1 =  " + Integer.toHexString(xx1));
        System.out.println("-----------xx2 =  " + Integer.toHexString(xx2));*/
    }
}
