package com.cnpay.tigerbalm.sp;

import java.io.Serializable;

/**
 * 串口工具实体-返回
 * 包            名:      com.cnpay.tigerbalm.sp
 * 类            名:      BaseSp
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/29
 */
public class BaseSp implements Serializable {

    private static final long serialVersionUID = 6481276085655750501L;
    /**
     * 错误信息
     */
    public String errorinfo = "";
    /**
     * 1:正常,0:错误,2:不确定交易
     */
    public int trackstatus = 1;
    /**
     * 返回的数据
     */
    public String msg;


    @Override
    public String toString() {
        return "BaseSp{" +
                "errorinfo='" + errorinfo + '\'' +
                ", trackstatus=" + trackstatus +
                ", msg='" + msg + '\'' +
                '}';
    }
}
