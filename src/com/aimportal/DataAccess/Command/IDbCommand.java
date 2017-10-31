package com.aimportal.DataAccess.Command;


import com.aimportal.DataAccess.Parameter.dbParameter;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/18.
 */
public interface IDbCommand {
    //CachedRowSet executeQuery(String scriptName) throws SQLException;
    CachedRowSet executeQuery() throws SQLException;
    //int executeNonQuery(String scriptName) throws SQLException;
    int executeNonQuery() throws  SQLException;

    void setScript(String scriptName);
    void loadScripts(String filepath);
    void setParameter(dbParameter parameter) throws SQLException;
    void close() throws SQLException;
}
