package com.cnpay.tigerbalm_20.db;

import xutils.db.annotation.Column;
import xutils.db.annotation.Table;

/**
 * 包            名:      com.cnpay.tigerbalm_20.db
 * 类            名:      User
 * 修 改 记 录:     // 修改历史记录，包括修改日期、修改者及修改内容
 * 版 权 所 有:     版权所有(C)2010-2015
 * 公             司:     深圳华夏通宝信息技术有限公司
 *
 * @author liyuanming
 * @version V1.0
 * @date 2016/4/29
 */
@Table(name = "User")
public class User extends Base {

    @Column(name = "userId", isId = true)
    private String userId;

    @Column(name = "age")
    private String age;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
