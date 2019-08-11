/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.app.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kevin
 */
public class SqlBuilder {

    public static String buildSqlOrder(HashMap<String, String> hmOrder) {
        String strSQLOrder;
        if (hmOrder != null) {
            strSQLOrder = " ORDER BY ";
            for (Map.Entry<String, String> oPar : hmOrder.entrySet()) {
                strSQLOrder += "`"+oPar.getKey()+"`";
                strSQLOrder += " ";
                strSQLOrder += oPar.getValue();
                strSQLOrder += ",";
            }
            strSQLOrder = strSQLOrder.substring(0, strSQLOrder.length() - 1);
        } else {
            strSQLOrder = "";
        }
        return strSQLOrder; //regresas sentencia SQL
    }

}
