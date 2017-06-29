package com.aimportal.Config;

import com.aimportal.DataAccess.Base.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public class DBConf {
    private static final String DBCONFIG= "DBConfig.properties";
    public static Properties loadConfig(String dbName){

        String dirBase = System.getProperty("user.dir");
        dirBase += "\\config\\" + Config.getDBConfigFileName(dbName);
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(dirBase));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
