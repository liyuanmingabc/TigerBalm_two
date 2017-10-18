package com.cnpay.tigerbalm_20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*public void save(View v) throws DbException {
        User u = new User();
        u.setAge("100");
        u.setUserId("1");
        u.setName("张三");
        EntityDBUtil().saveOrUpdate(u);
    }


    private DbManager EntityDBUtil() {
        DbManager.DaoConfig config = new DbManager.DaoConfig();
        config.setDbName("TEST");
        config.setDbVersion(1);
        String dbPath = SimpleUtil.getSDCardPath() + "/test";
        config.setDbDir(new File(dbPath));//需要修改成 数据库外放
        config.setDbUpgradeListener(new DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                // TODO: ...
                // db.addColumn(...);
                // db.dropTable(...);
                // ...
            }
        });
        return xutils.x.getDb(config);
    }*/
}
