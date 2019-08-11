/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.app.bean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.app.dao.ReviewDao;
import net.app.dao.UserDao;
import net.app.helper.EncodingHelper;

/**
 *
 * @author emorc
 */
public class ReviewListBean {

    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private int author_id;
    @Expose
    private UserBean author_obj;
    @Expose
    private int employee_id;
    @Expose
    private UserBean employee_obj;
    @Expose
    private ArrayList<ReviewBean> reviewList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public UserBean getAuthor_obj() {
        return author_obj;
    }

    public void setAuthor_obj(UserBean author_obj) {
        this.author_obj = author_obj;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public UserBean getEmployee_obj() {
        return employee_obj;
    }

    public void setEmployee_obj(UserBean employee_obj) {
        this.employee_obj = employee_obj;
    }

    public ArrayList<ReviewBean> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<ReviewBean> reviewList) {
        this.reviewList = reviewList;
    }
    
    public ReviewListBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setTitle(oResultSet.getString("title"));

        if (expand > 0) {
            UserDao oUserDao = new UserDao(oConnection, "user");
            ReviewDao oReviewDao = new ReviewDao(oConnection, "review");
            this.setAuthor_id(oResultSet.getInt("author_id"));
            this.setAuthor_obj(oUserDao.get(oResultSet.getInt("author_id"), expand - 1));   
            this.setEmployee_id(oResultSet.getInt("employee_id"));         
            this.setEmployee_obj(oUserDao.get(oResultSet.getInt("employee_id"), expand - 1));
            this.setReviewList(oReviewDao.getByReviewlist(id, expand));
        } else {
            this.setAuthor_id(oResultSet.getInt("author_id"));  
            this.setEmployee_id(oResultSet.getInt("employee_id"));         
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "title,";
        strColumns += "author_id,";
        strColumns += "employee_id";

        return strColumns;
    }

    public String getValues() {

        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(title) + ",";
        strColumns += EncodingHelper.quotate(Integer.toString(author_id)) + ",";
        strColumns += EncodingHelper.quotate(Integer.toString(employee_id));

        return strColumns;
    }

    public String getPairs(String ob) {

        String strPairs = "";
        strPairs += "title =" + EncodingHelper.quotate(title) + ",";
        strPairs += "author_id =" + EncodingHelper.quotate(Integer.toString(author_id)) + ",";
        strPairs += "employee_id =" + EncodingHelper.quotate(Integer.toString(employee_id));
        strPairs += " WHERE id=" + id;
        return strPairs;

    }

}
