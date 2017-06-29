package com.aimportal.DataAccess.Command;


import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by IvanHung on 2017/4/18.
 */
public interface IDbCommand<T> {
    CachedRowSet executeQuery(String scriptName) throws SQLException;
    CachedRowSet executeQuery() throws SQLException;
    int executeNonQuery(String scriptName) throws SQLException;
    int executeNonQuery() throws  SQLException;
    void beginTransaction() throws SQLException;
    void setScript(String scriptName);
    void setParameter(String Parametername, T ParameterValue) throws SQLException;
    void close() throws SQLException;
}
