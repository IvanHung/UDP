package com.aimportal.Xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IvanHung on 2017/4/19.
 */
public class Xml {
    public Map<String,String> Script;
    private Document doc;//document

    public void loadXMLFile(String filePath) throws DocumentException {
        try{
            //SAXReader saxReader = new SAXReader();
            //String dir = System.getProperty("user.dir");
            //dir += "\\BasicSQL.xml";
            //doc = saxReader.read(new File(dir));
            try{
                doc = new SAXReader().read(new File(filePath));
                if(doc != null)
                    parseSQLScript();
            }
            catch(DocumentException ioex){
                throw ioex;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void clear(){
        if(Script != null)
            Script.clear();
    }
    public String getScript(String scriptName){
        return Script.get(scriptName);
    }
    private void parseSQLScript(){
        Script = new HashMap<String,String>();
        try{
            Element root = doc.getRootElement();//get roo
            // t element
            System.out.println("the root element is :" + root.getName());
            Pattern p = Pattern.compile("\t|\r|\n");

            List<Node> nodes = doc.selectNodes("/SqlCollection/SqlCommands/SQL");
            System.out.println("Get the number of command :" + nodes.size());
            for(Node node : nodes){
                System.out.println("\nCurrent Element :" + node.getName());
                String SQLName = node.valueOf("@Name");
                System.out.println("SQL Name :" + SQLName);

                System.out.println("Summary : " + node.selectSingleNode("Summary").getText());
                System.out.println("SQL Script : " + node.selectSingleNode("Command").getText());
                String CommandText = node.selectSingleNode("Command").getText();
                Matcher m = p.matcher(CommandText);
                CommandText = m.replaceAll(" ");
                Script.put(SQLName,CommandText);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
