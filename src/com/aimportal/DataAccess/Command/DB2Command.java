package com.aimportal.DataAccess.Command;

import com.aimportal.DataAccess.Connection.DB2Connection;
import com.aimportal.Xml.Xml;
import com.ibm.db2.jcc.DB2PreparedStatement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import com.ibm.db2.jcc.DB2ResultSet;

/**
 * Created by IvanHung on 2017/4/17.
 */
public class DB2Command extends DBCommand implements IDbCommand {
    /*建立DBCommand時，載入 SQL Script，不採取事先將Script載入PreparedStatement，會造成DB有Parse但未execute的成本
    *
    * */
    static final String  DBType = "DB2";
    //private IDbConnection Conn;
    private DB2Connection DB2CONN;
    private DB2PreparedStatement db2prep;
    private DB2ResultSet db2Rst;
    private String CommandScript;
    public Map<String,PreparedStatement> Transaction;

    /*
    * 現在的問題是：
    * 如何處理sql script,
    * option1:建立HashMap<scriptname,preparedstatement>
    *
    * */
    public DB2Command(){
        //Precondition of Constructor
        //1. load sql script from xml
        try{
            SQLScript = new HashMap<String,String>();
            //Test
            Xml x = new Xml();
            x.loadXMLFile();
            SQLScript = x.parseSQLScript();
            //EndTest
            DB2CONN = new com.aimportal.DataAccess.Connection.DB2Connection();
            DB2CONN.open();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if(DB2CONN != null)
            DB2CONN.close();
        //super.finalize();
    }


    @Override
    public CachedRowSet executeQuery(String scriptName) throws SQLException{
        try{
            String exeSql = SQLScript.get(scriptName);

            db2prep = (DB2PreparedStatement)DB2CONN.CreateStatement(exeSql);
            db2Rst = (com.ibm.db2.jcc.am.ResultSet)db2prep.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(db2Rst);
        }
        catch(SQLException sqlex){
            throw sqlex;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DB2CONN.close();
        }
        return crs;
    }

    @Override
    public CachedRowSet executeQuery() throws SQLException {
        try{

            //DB2CONN = new com.aimportal.DataAccess.Connection.DB2Connection();
            //DB2CONN.open();
            //db2prep = (DB2PreparedStatement)DB2CONN.CreateStatement(CommandScript);
            db2Rst = (com.ibm.db2.jcc.am.ResultSet)db2prep.executeQuery();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(db2Rst);
        }
        catch(SQLException sqlex){
            throw sqlex;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            DB2CONN.close();
        }
        return crs;

    }

    @Override
    public int executeNonQuery(String scriptName) throws SQLException {
        int rowsNum = 0;
        try {
            DB2CONN = new com.aimportal.DataAccess.Connection.DB2Connection();
            DB2CONN.open();

            String exeSql = SQLScript.get(scriptName);
            db2prep = (DB2PreparedStatement)DB2CONN.CreateStatement(exeSql);
            rowsNum = db2prep.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            db2prep.close();
        }
        return rowsNum;
    }

    @Override
    public int executeNonQuery() throws SQLException {
        int rowsNum = 0;
        return rowsNum;
    }

    @Override
    public void beginTransaction() throws SQLException {
        if(DB2CONN != null)
            DB2CONN.setAutoCommit(true);
        else
            throw new SQLException("Fatal Error：setAutoCommit on null Connection.");
        Transaction = new HashMap<String,PreparedStatement>();
    }

    @Override
    public void setScript(String scriptName) {
        try{
            CommandScript = SQLScript.get(scriptName);
            db2prep = (DB2PreparedStatement)DB2CONN.CreateStatement(CommandScript);
            //CommandScript = SQLScript.get(scriptName);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void setParameter(String parameterName, Object parameterValue) throws SQLException {
        String type = parameterValue.getClass().getSimpleName();
        switch(type.toLowerCase()){
            case "integer":
                db2prep.setJccIntAtName(parameterName,(int)parameterValue);
                break;
            case "string":
                db2prep.setJccStringAtName(parameterName,(String)parameterValue);
                break;
            case "float":
                db2prep.setJccFloatAtName(parameterName,(float)parameterValue);
                break;
            case "date":
                db2prep.setJccDateAtName(parameterName,(Date) parameterValue);
                break;
            case "time":
                db2prep.setJccTimeAtName(parameterName,(Time) parameterValue);
                break;

        }
    }

    @Override
    public void close() throws SQLException {
        if(DB2CONN != null)
            DB2CONN.close();
    }
}
