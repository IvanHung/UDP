package com.aimportal.DataAccess.Transaction;


import com.aimportal.DataAccess.Command.DBCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by IvanHung on 2017/4/27.
 */
public class DB2Transaction extends DbTransaction implements IDbTransaction{


    public DB2Transaction(){
        commandList = new ArrayList<DBCommand>();
    }
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
    public void add(DBCommand prep) {
        commandList.add(prep);
    }

    @Override
    public void execute() {
        try {
            for(DBCommand dbCommand : commandList)
                dbCommand.executeNonQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beginTransaction() {

    }

}
