package com.aimportal.DataAccess.Transaction;



import com.aimportal.DataAccess.Connection.DB2Connection;
import com.aimportal.DataAccess.Connection.IDbConnection;

import java.sql.SQLException;

/**
 * Created by IvanHung on 2017/4/27.
 */
public class DB2Transaction extends DbTransaction implements IDbTransaction,AutoCloseable{


    @Override
    public void commit() throws SQLException {
        if(Con != null)
            Con.commit();
    }
    @Override
    public void rollback() throws SQLException {
        if(Con != null)
            Con.rollback();

    }


    @Override
    public void close() throws SQLException {
        if(Con != null)
            Con.close();
    }
}
