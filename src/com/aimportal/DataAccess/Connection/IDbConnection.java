package com.aimportal.DataAccess.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by IvanHung on 2017/4/17.
 *
 * DB2 Driver : db2jcc.jar
 * MySQL Driver : mysql-connector-java-5.1.41-bin.jar
 * Oracle Driver : ojdbc6.jar
 * SQLServer Driver : sqljdbc41.jar(Jre7)
 *
/*
 * Created by IvanHung on 2017/4/27.
 */
public interface IDbConnection{
    void close() throws SQLException;
    void open() throws SQLException;
    void commit() throws SQLException;
    void rollback() throws SQLException;
    PreparedStatement CreateStatement(String SQLScript) throws SQLException;
    void setAutoCommit(boolean isAutoCommit) throws SQLException;


}
