/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.app.bean;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import net.app.dao.ReviewlistDao;
import net.app.dao.UserDao;
import net.app.helper.EncodingHelper;

/**
 *
 * @author emorc
 */
public class ReviewBean {

    @Expose
    private int id;
    @Expose
    private int rating;
    @Expose
    private String comment;
    @Expose
    private boolean submitted;
    @Expose
    private UserBean user_obj;
    @Expose
    private int user_id;
    @Expose
    private ReviewListBean reviewlist_obj;
    @Expose
    private int reviewlist_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public UserBean getUser_obj() {
        return user_obj;
    }

    public void setUser_obj(UserBean user_obj) {
        this.user_obj = user_obj;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public ReviewListBean getReviewlist_obj() {
        return reviewlist_obj;
    }

    public void setReviewlist_obj(ReviewListBean reviewlist_obj) {
        this.reviewlist_obj = reviewlist_obj;
    }

    public int getReviewlist_id() {
        return reviewlist_id;
    }

    public void setReviewlist_id(int reviewlist_id) {
        this.reviewlist_id = reviewlist_id;
    }
    
    
    
    public ReviewBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {

        this.setId(oResultSet.getInt("id"));
        this.setRating(oResultSet.getInt("rating"));
        this.setComment(oResultSet.getString("comment"));
        this.setSubmitted(oResultSet.getBoolean("submitted"));

        if (expand > 0) {
            UserDao oUserDao = new UserDao(oConnection, "user");
            ReviewlistDao oReviewlistDao = new ReviewlistDao(oConnection, "reviewlist");
            this.setUser_id(oResultSet.getInt("user_id"));
            this.setUser_obj(oUserDao.get(oResultSet.getInt("user_id"), expand - 1));   
            this.setReviewlist_id(oResultSet.getInt("reviewlist_id"));         
            this.setReviewlist_obj(oReviewlistDao.get(oResultSet.getInt("reviewlist_id"), expand - 1));
        } else {
            this.setUser_id(oResultSet.getInt("user_id"));   
            this.setReviewlist_id(oResultSet.getInt("reviewlist_id"));         
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "rating,";
        strColumns += "comment,";
        strColumns += "submitted,";
        strColumns += "user_id,";
        strColumns += "reviewlist_id";

        return strColumns;
    }

    public String getValues() {

        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(Integer.toString(rating)) + ",";
        strColumns += EncodingHelper.quotate(comment) + ",";
        strColumns += submitted + ",";
        strColumns += EncodingHelper.quotate(Integer.toString(user_id)) + ",";
        strColumns += EncodingHelper.quotate(Integer.toString(reviewlist_id));

        return strColumns;
    }

    public String getPairs(String ob) {

        String strPairs = "";
        strPairs += "rating =" + EncodingHelper.quotate(Integer.toString(rating)) + ",";
        strPairs += "comment =" + EncodingHelper.quotate(comment) + ",";
        strPairs += "submitted =" + submitted + ",";
        strPairs += "user_id =" + EncodingHelper.quotate(Integer.toString(user_id)) + ",";
        strPairs += "reviewlist_id =" + EncodingHelper.quotate(Integer.toString(reviewlist_id));
        strPairs += " WHERE id=" + id;
        return strPairs;

    }

}
