package com.aimportal.DataAccess.Base;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public class Config {
    public static String getDBConfigFileName(String dbName){
        String fileName = "";
        Database db = Database.valueOf(dbName);
        switch(db){
            case DB2:
                fileName = "DB2Config.properties";
                break;
            case MSSQL:
                fileName = "MSSQLConfig.properties";
                break;
            case MySQL:
                fileName = "MySQLConfig.properties";
                break;
            case Oracle:
                fileName = "OracleConfig.properties";
                break;
        }
        return fileName;
    }
    public static String getURLPrefix(String dbName){
        String prefix = "";
        Database db = Database.valueOf(dbName);
        switch(db){
            case DB2:
                prefix = "jdbc:db2://";
                break;
            case MSSQL:
                prefix = "jdbc:sqlserver://";
                break;
            case MySQL:
                prefix = "jdbc:mysql://";
                break;
            case Oracle:
                prefix = "jdbc:oracle:";//oracle不太一樣
                break;
        }
        return prefix;
    }
/*Oracle connection: note:
*   option1:jdbc:oracle:<drivertype>:@<database>
*   option2:jdbc:oracle:<drivertype>:<user>/<password>@<database>
*
* drivertype can be thin, oci or kprb.
* atabase can be in the form of hostname:port:SID or a TNSNAMES entry listed in the file tnsnames.ora
* reside on the client computer. The default port is 1521.
*
* Thin Driver
For client-side use without an Oracle installation
thin
OCI Driver
For client-side use with an Oracle installation
oci
Server-Side Thin Driver
Same as Thin Driver, but runs inside an Oracle server to access a remote server
thin
Server-Side Internal Driver
Runs inside the target server
kprb
* */
}
