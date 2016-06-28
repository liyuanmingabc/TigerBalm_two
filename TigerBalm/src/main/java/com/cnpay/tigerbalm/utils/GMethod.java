package com.cnpay.tigerbalm.utils;

/**
 * byte[] 数据拼接
 * 包            名:      com.cnpay.ppvending.comm.sp.util
 * 类            名:      GMethod
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公            司:     深圳华夏通宝信息技术有限公司
 *
 * @author yuyucheng
 * @version V1.0
 */
public class GMethod {
    /**
     * byte[] 拼接
     *
     * @param paramArrayOfByte1 原始数据
     * @param paramArrayOfByte2 新增数据
     * @return
     */
    public static byte[] ArrayAppend(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) {
        if ((paramArrayOfByte1 == null) && (paramArrayOfByte2 == null))
            return null;
        if (paramArrayOfByte1 == null) {
            byte[] arrayOfByte3 = new byte[paramArrayOfByte2.length];
            System.arraycopy(paramArrayOfByte2, 0, arrayOfByte3, 0, paramArrayOfByte2.length);
            return arrayOfByte3;
        }
        if (paramArrayOfByte2 == null) {
            byte[] arrayOfByte2 = new byte[paramArrayOfByte1.length];
            System.arraycopy(paramArrayOfByte1, 0, arrayOfByte2, 0, paramArrayOfByte1.length);
            return arrayOfByte2;
        }
        byte[] arrayOfByte1 = new byte[paramArrayOfByte1.length + paramArrayOfByte2.length];
        System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, paramArrayOfByte1.length);
        System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, paramArrayOfByte1.length, paramArrayOfByte2.length);
        return arrayOfByte1;
    }

    /**
     * BYTE[] 添加
     *
     * @param paramArrayOfByte1 当前数据
     * @param paramArrayOfByte2 数据
     * @param paramInt          数据长度
     * @return
     */
    public static byte[] ArrayAppend(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt) {
        if ((paramArrayOfByte1 == null) && (paramArrayOfByte2 == null))
            return null;
        if (paramArrayOfByte1 == null) {
            byte[] arrayOfByte3 = new byte[paramInt];
            System.arraycopy(paramArrayOfByte2, 0, arrayOfByte3, 0, paramInt);
            return arrayOfByte3;
        }
        if (paramArrayOfByte2 == null) {
            byte[] arrayOfByte2 = new byte[paramArrayOfByte1.length];
            System.arraycopy(paramArrayOfByte1, 0, arrayOfByte2, 0, paramArrayOfByte1.length);
            return arrayOfByte2;
        }
        byte[] arrayOfByte1 = new byte[paramInt + paramArrayOfByte1.length];
        System.arraycopy(paramArrayOfByte1, 0, arrayOfByte1, 0, paramArrayOfByte1.length);
        System.arraycopy(paramArrayOfByte2, 0, arrayOfByte1, paramArrayOfByte1.length, paramInt);
        return arrayOfByte1;
    }
}
