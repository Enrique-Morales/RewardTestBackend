package net.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import net.app.bean.ReviewBean;
import net.app.helper.SqlBuilder;

public class ReviewDao {

    Connection oConnection;
    String ob = null;

    public ReviewDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public ReviewBean get(int id, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
        ReviewBean oReviewBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oReviewBean = new ReviewBean();

                oReviewBean.fill(oResultSet, oConnection, expand);

            } else {
                oReviewBean = null;
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
        return oReviewBean;
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

    public ReviewBean create(ReviewBean oReviewBean) throws Exception {

        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oReviewBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oReviewBean.getValues() + ")";

        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oReviewBean.setId(oResultSet.getInt(1));
            } else {
                oReviewBean.setId(0);
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
        return oReviewBean;
    }

    public int update(ReviewBean oReviewBean) throws Exception {
        int iResult = 0;
        String strSQL = "UPDATE " + ob + " SET ";

        strSQL += oReviewBean.getPairs(ob);

        Statement oStatement = null;
        try {
            oStatement = oConnection.createStatement();

            iResult = oStatement.executeUpdate(strSQL, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            throw new Exception("Error in Dao update of " + ob, e);
        } finally {
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return iResult;
    }

    public ArrayList<ReviewBean> getpage(int iRpp, int iPage, HashMap<String, String> hmOrder, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob;
        strSQL += SqlBuilder.buildSqlOrder(hmOrder);
        ArrayList<ReviewBean> alReviewBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alReviewBean = new ArrayList<ReviewBean>();
                while (oResultSet.next()) {
                    ReviewBean oReviewBean = new ReviewBean();
                    oReviewBean.fill(oResultSet, oConnection, expand);
                    alReviewBean.add(oReviewBean);
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
        return alReviewBean;
    }

    public ArrayList<ReviewBean> getpageByUser(Integer user_id, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE user_id = " + user_id;
        ArrayList<ReviewBean> alReviewBean;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alReviewBean = new ArrayList<ReviewBean>();
                while (oResultSet.next()) {
                    ReviewBean oReviewBean = new ReviewBean();
                    oReviewBean.fill(oResultSet, oConnection, expand);
                    alReviewBean.add(oReviewBean);
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
        return alReviewBean;
    }
    
    public ArrayList<ReviewBean> getByReviewlist(Integer reviewlist_id,Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE reviewlist_id = " + reviewlist_id;
        ArrayList<ReviewBean> alReviewBean;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alReviewBean = new ArrayList<ReviewBean>();
                while (oResultSet.next()) {
                    ReviewBean oReviewBean = new ReviewBean();
                    oReviewBean.fill(oResultSet, oConnection, expand);
                    alReviewBean.add(oReviewBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error in Dao getByReviewlist of " + ob, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        
        return alReviewBean;
    }
    
    public ArrayList<ReviewBean> getNonSubmittedByUser(Integer user_id, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE user_id = " + user_id + " AND submitted=0";
        ArrayList<ReviewBean> alReviewBean;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oResultSet = oPreparedStatement.executeQuery();
                alReviewBean = new ArrayList<ReviewBean>();
                while (oResultSet.next()) {
                    ReviewBean oReviewBean = new ReviewBean();
                    oReviewBean.fill(oResultSet, oConnection, expand);
                    alReviewBean.add(oReviewBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error in Dao getByReviewlist of " + ob, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        
        return alReviewBean;
    }

}
