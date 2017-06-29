package com.aimportal.DataAccess.Connection;

import com.aimportal.Config.DBConf;
import com.ibm.db2.jcc.DB2PreparedStatement;
import com.ibm.db2.jcc.uw.DB2Exception;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/27.
 */
public class DB2Connection extends Connection implements IDbConnection {
    static final String  DBType = "DB2";
    private com.ibm.db2.jcc.DB2Connection DB2CONN;
    @Override
    public void close() throws SQLException {
        DB2CONN.close();
    }

    @Override
    public void open() {
        try{
            prop = DBConf.loadConfig(DBType);//load properties
            if(prop != null){
                DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());
                String URL = prop.getProperty("host");
                DB2CONN = (com.ibm.db2.jcc.DB2Connection)DriverManager.getConnection(URL,prop);

            }
            else
                throw new DB2Exception("Necessary DbConfig(properties) missing");
        } catch(DB2Exception db2ex){
            db2ex.printStackTrace();
        } catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public void commit() throws SQLException {
        DB2CONN.commit();
    }

    @Override
    public void rollback() throws SQLException {
        DB2CONN.rollback();
    }

    @Override
    public PreparedStatement CreateStatement(String SQLScript) throws SQLException {
        return DB2CONN.prepareStatement(SQLScript);
    }

    @Override
    public void setAutoCommit(boolean isAutoCommit) throws SQLException {
        DB2CONN.setAutoCommit(isAutoCommit);
    }


}
