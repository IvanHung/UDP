package com.aimportal.DataAccess.Transaction;

import com.aimportal.DataAccess.Command.DBCommand;

import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/27.
 */
public interface IDbTransaction {
    /*Transaction 就是包含了Connection與相關的動作eg:commit、rollback與beginTransaction*/
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void add(DBCommand command);
    void execute();
    void beginTransaction();


}

