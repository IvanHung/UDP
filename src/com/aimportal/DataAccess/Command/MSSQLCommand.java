package com.aimportal.DataAccess.Command;

import com.aimportal.DataAccess.Parameter.dbParameter;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class MSSQLCommand implements IDbCommand {
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

    @Override
    public void close() throws SQLException {

    }
}
