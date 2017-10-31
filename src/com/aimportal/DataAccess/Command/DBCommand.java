package com.aimportal.DataAccess.Command;


import com.aimportal.DataAccess.Connection.IDbConnection;
import com.aimportal.Xml.Xml;

import javax.sql.rowset.CachedRowSet;
import java.util.Properties;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public abstract class DBCommand implements IDbCommand{
    //Declaration of common variable
    protected Properties prop;//Config for Connection
    protected CachedRowSet crs;//選定CachedRowSet做為離線型資料
    protected Xml sqlxml;
    protected  IDbConnection DbConnection;
    //public Map<String,PreparedStatement> Transaction;

}
