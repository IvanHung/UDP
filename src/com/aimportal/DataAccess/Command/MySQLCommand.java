package com.aimportal.DataAccess.Command;

import com.aimportal.Config.DBConf;
import com.aimportal.DataAccess.Connection.IDbConnection;
import com.aimportal.DataAccess.Parameter.IParameter;
import com.aimportal.DataAccess.Transaction.IDbTransaction;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLDataException;

import javax.sql.rowset.CachedRowSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public class MySQLCommand implements IDbCommand,AutoCloseable {
    static final String  DBType = "MySQL";

    private Properties prop;
    private IDbConnection Conn;
    private com.mysql.jdbc.jdbc2.optional.JDBC4PreparedStatementWrapper mysqlprep;
    public MySQLCommand(){
        //Precondition of Constructor
        //1. load sql script from xml
    }
    @Override
    protected void finalize() throws Throwable {
        if(Conn != null)
            Conn.close();
        //super.finalize();
    }


    @Override
    public void close() {
        if(Conn != null){
            try {
                Conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public CachedRowSet executeQuery(String scriptName) throws SQLException {
        return null;
    }

    @Override
    public CachedRowSet executeQuery() throws SQLException {
        return null;
    }

    @Override
    public int executeNonQuery(String scriptName) throws SQLException {
        return 0;
    }

    @Override
    public int executeNonQuery() throws SQLException {
        return 0;
    }

    @Override
    public void beginTransaction() throws SQLException {

    }

    @Override
    public void setScript(String scriptName) {

    }

    @Override
    public void setParameter(String parameterName, Object parameterValue) throws SQLException {

    }
}
