package com.aimportal.DataAccess.Transaction;

import com.aimportal.DataAccess.Connection.IDbConnection;

import java.sql.PreparedStatement;
import java.util.Map;

/**
 * Created by IvanHung on 2017/4/28.
 */
public abstract class DbTransaction {
    IDbConnection Con;
    Map<String, PreparedStatement> Transaction;
}
