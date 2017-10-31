package com.aimportal.DataAccess.Transaction;

import com.aimportal.DataAccess.Command.DBCommand;
import com.aimportal.DataAccess.Connection.IDbConnection;

import java.util.List;

/**
 * Created by IvanHung on 2017/4/28.
 */
public abstract class DbTransaction {
    IDbConnection Con;
    List<DBCommand> commandList;
}
