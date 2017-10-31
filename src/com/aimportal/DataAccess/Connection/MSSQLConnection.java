package com.aimportal.DataAccess.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MSSQLConnection extends Connection implements IDbConnection {
    @Override
    public void close() throws SQLException {

    }

    @Override
    public void open(Properties prop) throws SQLException {

    }

    @Override
    public void commit() throws SQLException {

    }

    @Override
    public void rollback() throws SQLException {

    }

    @Override
    public PreparedStatement CreateStatement(String SQLScript) throws SQLException {
        return null;
    }
}
