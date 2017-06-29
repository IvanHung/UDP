package com.aimportal.DataAccess.Command;



import com.aimportal.DataAccess.Transaction.IDbTransaction;

import javax.sql.rowset.CachedRowSet;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Ivan Hung on 2017/4/17.
 */
public abstract class DBCommand {
    //Declaration of common variable

    protected Map<String,String> SQLScript;//SQLScript
    protected Properties prop;//Config
    protected CachedRowSet crs;//選定CachedRowSet做為離線型資料
    public Map<String,PreparedStatement> Transaction;

}
