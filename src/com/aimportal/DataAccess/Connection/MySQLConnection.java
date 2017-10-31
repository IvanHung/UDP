package com.aimportal.DataAccess.Connection;

import com.aimportal.Config.DBConf;
import com.mysql.jdbc.exceptions.MySQLDataException;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IvanHung on 2017/4/27.
 */
public class MySQLConnection extends Connection implements IDbConnection{

    static final String  DBType = "MySQL";
    private com.mysql.jdbc.MySQLConnection MySQLConn;

    @Override
    public void close() {
        try {
            MySQLConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void open(Properties prop) {
        try{
            prop = DBConf.loadConfig(DBType);//load properties
            if(prop != null){
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                String URL = prop.getProperty("host");
                MySQLConn = (com.mysql.jdbc.MySQLConnection) DriverManager.getConnection(URL,prop);

            }
            else
                throw new MySQLDataException("Necessary DbConfig(properties) missing");
        } catch(MySQLDataException mysqlex){
            mysqlex.printStackTrace();
        } catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }

    @Override
    public void commit() throws SQLException {
        MySQLConn.commit();
    }

    @Override
    public void rollback() throws SQLException {
        MySQLConn.rollback();
    }

    @Override
    public PreparedStatement CreateStatement(String SQLScript) throws SQLException {
        return  MySQLConn.prepareStatement(SQLScript);

    }
}
