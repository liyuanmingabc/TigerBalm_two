package com.cnpay.tigerbalm_20.db;

import java.io.Serializable;

import xutils.db.annotation.Column;

/**
 * 包            名:      com.cnpay.tigerbalm_20.db
 * 类            名:      Base
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/29
 */

public class Base implements Serializable {

    @Column(name = "Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
