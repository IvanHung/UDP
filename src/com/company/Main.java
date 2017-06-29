package com.company;



import com.aimportal.DataAccess.Command.DB2Command;
import com.aimportal.DataAccess.Command.IDbCommand;
import com.aimportal.DataAccess.Command.MySQLCommand;

import javax.sql.rowset.CachedRowSet;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        //先拿自己的ID/PW測試
        DB2Command db2Command = null;
        try {
            /*Demo Example：
            * 1. Single Query
            * 2. Single DML
            * 3. Multiple DML with Transaction
            * */
            System.out.println("DB Test Start..");

            //範例1. Single Query: MySQL
            //command = new MySQLCommand();
            //command.setScript("scriptName");
            //command.setparameter("parameterName","parameterValue");
            //ResultSet rs = command.executeQuery();//回傳cachedRowSet，但ResultSet也可以接

            //範例2. Single Update:Oracle
            //command = new OracleCommand();
            //command.setScript("scriptName");
            //command.setParameter();//有參數
            //ResultSet rs command.executeNonQuery();//沒有參數:command.executeNonQuery(scriptName)

            //範例3. Multiple Update within transaction
            //command = new DB2Command();
            //command.beginTransaction();//啟動transaction
            //command.transaction.addscript("scriptName");
            //command.transaction.addparameter("scriptName","parameterName","parametervalue");
            //command.transaction.executeUpdate();
            //command.transaction.commit();//exception to rollback()

            //actual pratice
            db2Command = new DB2Command();
            IDbCommand ic = new MySQLCommand();

            db2Command.setScript("SelectCASSEMAINByCMSEQNO");
            db2Command.setParameter("CMSEQNO","201703030348");
            CachedRowSet crs = db2Command.executeQuery();
            int Rowsgettred = crs.getRow();
            System.out.println(Rowsgettred);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
