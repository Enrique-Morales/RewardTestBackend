package net.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import net.app.bean.UserTypeBean;
import net.app.helper.SqlBuilder;

public class UsertypeDao {

    Connection oConnection;
    String ob = null;

    public UsertypeDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public UserTypeBean get(int id, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        UserTypeBean oUsertypeBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsertypeBean = new UserTypeBean();

                oUsertypeBean.fill(oResultSet, oConnection, expand);

            } else {
                oUsertypeBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error in Dao get of " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsertypeBean;
    }

    public int remove(int id) throws Exception {
        int iRes = 0;
        String strSQL = "DELETE FROM " + ob + " WHERE id=?";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iRes = oPreparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error in Dao remove of " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iRes;
    }

    public int getcount() throws Exception {
        String strSQL = "SELECT COUNT(id) FROM " + ob;
        int res = 0;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                res = oResultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new Exception("Error in Dao get of " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return res;
    }

    public UserTypeBean create(UserTypeBean oUsertypeBean) throws Exception {

        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oUsertypeBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oUsertypeBean.getValues() + ")";

        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oUsertypeBean.setId(oResultSet.getInt(1));
            } else {
                oUsertypeBean.setId(0);
            }
        } catch (SQLException e) {
            throw new Exception("Error in Dao create of " + ob + "-------" + e.getMessage(), e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oUsertypeBean;
    }

    public int update(UserTypeBean oUsertypeBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET ";

        strSQL += oUsertypeBean.getPairs(ob);

        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);

            iResult = oPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error in Dao update of " + ob, e);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    public ArrayList<UserTypeBean> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob;
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<UserTypeBean> alUsertypeBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alUsertypeBean = new ArrayList<UserTypeBean>();
                while (oResultSet.next()) {
                    UserTypeBean oUsertypeBean = new UserTypeBean();
                    oUsertypeBean.fill(oResultSet, oConnection, expand);
                    alUsertypeBean.add(oUsertypeBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error in Dao getpage of " + ob, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        } else {
            throw new Exception("Error in Dao getpage of " + ob);
        }
        return alUsertypeBean;
    }

}
