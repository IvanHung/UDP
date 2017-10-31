package com.aimportal.DataAccess.Command;

import com.aimportal.DataAccess.Connection.IDbConnection;
import com.aimportal.DataAccess.Parameter.dbParameter;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public class MySQLCommand implements IDbCommand {
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
    public CachedRowSet executeQuery() throws SQLException {
        return null;
    }

    @Override
    public int executeNonQuery() throws SQLException {
        return 0;
    }

    @Override
    public void setScript(String scriptName) {

    }

    @Override
    public void loadScripts(String filepath) {

    }

    @Override
    public void setParameter(dbParameter parameter) throws SQLException {

    }
}
