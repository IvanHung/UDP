package com.company;


import com.aimportal.DataAccess.Command.DB2Command;
import com.aimportal.DataAccess.Constant.JDBCType;
import com.aimportal.DataAccess.Parameter.dbParameter;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.Properties;

/*
* UDP-Universal Data Provider alpha 0.1
* AP人員只需要操面對兩種Class：DbCommand,DbTransaction
* DbCommand:
* 一次執行一個SQL，可在同一個Connection內，多次執行不同SQL(SingleSQL or Batch)
* 適用情境：
* 1.Single Select
* 2.Single DML
* 3.Multiple Select within one connection
* 4.Multiple DML with one connection
*
* DbTransaction:
* 一次執行多種SQL，Transaction，支援commit與Rollback。
* 適用情境：
* 與JDBC內相同，任何需要Transaction的地方均可。
*
* 特色：
* 1.基於JDBC整合不同DB Driver，操作相同的程式碼，即可執行SQL
* 2.將SQL與Java Code 拆分，利於後續維護與SQL Debug
* 3.支援DB2, Oracle, MySQL, SQLServer
*
* future extend(To-Do):
* 1. ConfigureManager: 整合管理ConnectionString資訊
* 2. XML-SQL 再細緻化
* */
public class Main {

    public static void main(String[] args) throws SQLException {


        try {
            /*Demo Example：
            * 1. Single Query
            * 2. Single DML
            * 3. Multiple DML with Transaction
            * */
            System.out.println("DB Test Start..");
            //System.setProperty("db2.jcc.charsetDecoderEncoder","3");
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
            String[] arr = {"10004J","12004J","14B04D"};
            CachedRowSet crs = null;

            //測試用的prop
            Properties prop = new Properties();
            //prop.setProperty("enableNamedParameterMarkers","1");

            //正式環境
            //prop.setProperty("host","jdbc:db2://TCPVGHK:446/DSNP");
            //prop.setProperty("user","APBIL01");
            //prop.setProperty("password","BQCJM12");
            //測試環境
            prop.setProperty("host","jdbc:db2://TSTVGHK:447/DSNT");
            prop.setProperty("user","DB2USER");
            prop.setProperty("password","DB2USER");

            //測試用的xmlFile-AP自行指定
            String dir = System.getProperty("user.dir");
            dir += "\\BasicSQL.xml";
            DB2Command command = null;
            try{
                command = new DB2Command(prop);
                command.loadScripts(dir);//先載入sql
                command.setScript("SELECTAC");
                command.setParameter(new dbParameter("CYYMM","201708" ,JDBCType.VARCHAR));
                crs = command.executeQuery();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            finally{
                command.close();
            }

            while(crs.next()){
                System.out.println(crs.getString("CYYMM"));
            }
            System.out.println("共" + crs.size() + "筆");



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
