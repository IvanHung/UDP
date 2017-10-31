package com.aimportal.DataAccess.Command;

import com.aimportal.DataAccess.Connection.DB2Connection;
import com.aimportal.DataAccess.Constant.JDBCType;
import com.aimportal.DataAccess.Parameter.dbParameter;
import com.aimportal.Xml.Xml;
import com.ibm.db2.jcc.DB2PreparedStatement;
import com.ibm.db2.jcc.DB2ResultSet;
import com.sun.rowset.CachedRowSetImpl;
import org.dom4j.DocumentException;

import javax.sql.rowset.CachedRowSet;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * Created by IvanHung on 2017/4/17.
 */
public class DB2Command extends DBCommand{
    /* 建立DBCommand時，載入 SQL Script，
    *  不採取事先將Script載入PreparedStatement，
    *  會造成DB有Parse但未execute的成本
    *
    * */
    //private DB2Connection DB2CONN;
    private DB2PreparedStatement db2prep;
    private DB2ResultSet db2Rst;
    private String CommandScript;


    /*
    *
    * */
    public DB2Command(Properties p){
        try{
            prop = p;
            sqlxml = new Xml();
            DbConnection = new DB2Connection();
            DbConnection.open(prop);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if(DbConnection != null)
            DbConnection.close();
    }

    @Override
    public CachedRowSet executeQuery() throws SQLException {
        try{
            db2Rst = (com.ibm.db2.jcc.am.ResultSet)db2prep.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(db2Rst);
        }
        catch(SQLException sqlex){
            throw sqlex;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            db2prep.close();
        }
        return crs;

    }

    @Override
    public int executeNonQuery() throws SQLException {
        int rowsNum = 0;
        try {
            String exeSql = sqlxml.getScript(CommandScript);
            db2prep = (DB2PreparedStatement)DbConnection.CreateStatement(exeSql);
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
    public void setScript(String scriptName) {
        try{
            if(sqlxml != null){
                CommandScript = sqlxml.getScript(scriptName);
                db2prep = (DB2PreparedStatement)DbConnection.CreateStatement(CommandScript);
            }

            else
                throw new FileNotFoundException("File Not Found");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    @Override
    public void loadScripts(String filePath) {
        try {
            sqlxml.loadXMLFile(filePath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setParameter(dbParameter parameter) throws SQLException {
        try {
            JDBCType type = parameter.getJDBCType();
            switch(type){
                case INTEGER:
                case BIGINT:
                    db2prep.setJccIntAtName(parameter.getParameterName(),Integer.class.cast(parameter.getParameterVale()));
                    break;
                case VARCHAR:
                case CHAR:
                    db2prep.setJccStringAtName(parameter.getParameterName(),String.class.cast(parameter.getParameterVale()));
                    break;
                case ARRAY:
                    //Exception Error:目標伺服器上不支援資料類型 ARRAY
                    throw new UnsupportedOperationException();
                case FLOAT:
                    db2prep.setJccFloatAtName(parameter.getParameterName(),Float.class.cast(parameter.getParameterVale()));
                    break;
                case DATE:
                    java.util.Date d = java.util.Date.class.cast(parameter.getParameterVale());
                    java.sql.Date sqld = new java.sql.Date(d.getTime());
                    db2prep.setJccDateAtName(parameter.getParameterName(),sqld);
                    break;
                case TIME:
                    db2prep.setJccTimeAtName(parameter.getParameterName(),Time.class.cast(parameter.getParameterVale()));
                    break;
                case TIMESTAMP:
                    db2prep.setJccTimestampAtName(parameter.getParameterName(),Timestamp.class.cast(parameter.getParameterVale()));
                    break;
                case JAVA_OBJECT:
                    db2prep.setJccObjectAtName(parameter.getParameterName(),parameter.getParameterVale());
                    break;
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            throw sqlex;
        }
    }

    @Override
    public void close() throws SQLException {
        if(DbConnection != null)
            DbConnection.close();
    }
}
