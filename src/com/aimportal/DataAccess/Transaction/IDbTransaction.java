package com.aimportal.DataAccess.Transaction;

import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/27.
 */
public interface IDbTransaction extends AutoCloseable {
    /*Transaction 就是包含了Connection與相關的動作eg:commit、rollback與beginTransaction*/
    void commit() throws SQLException;
    void rollback() throws SQLException;
    //void beginTransaction();


}

