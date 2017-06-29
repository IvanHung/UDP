package com.aimportal.DataAccess.Utility;

import com.aimportal.DataAccess.Base.Database;
import com.ibm.db2.jcc.DB2Driver;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by IvanHung on 2017/4/17.
 */
public class DriverUtility {
    public static void initDriver() throws SQLException {

        java.sql.Driver drv = null;
        for(Database db : Database.values()){
            switch(db){
                case DB2:
                    drv = new DB2Driver();
                    break;
                case Oracle:
                    drv = new OracleDriver();
                    break;
                case MSSQL:
                    drv = new SQLServerDriver();
                    break;
                case MySQL:
                    drv = new com.mysql.jdbc.Driver();
                    break;
            }
            DriverManager.registerDriver(drv);
        }
        //Debug Info
        Enumeration<Driver> x = DriverManager.getDrivers();
        System.out.println("已載入下列DB Driver：");
        int count = 0;
        while(x.hasMoreElements()){
            java.sql.Driver driver = x.nextElement();
            System.out.println(driver.toString());
            count++;
        }
        System.out.println("DB Driver數量：" + count);

    }
}
