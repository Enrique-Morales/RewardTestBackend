/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.app.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import net.app.dao.UsertypeDao;
import net.app.helper.DateHandler;
import net.app.helper.EncodingHelper;

/**
 *
 * @author emorc
 */
public class UserBean {

    @Expose
    private int id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private String password;
    @Expose
    private int usertype_id;
    @Expose
    private UserTypeBean typeuser_obj;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsertype_id() {
        return usertype_id;
    }

    public void setUsertype_id(int usertype_id) {
        this.usertype_id = usertype_id;
    }

    public UserTypeBean getTypeuser_obj() {
        return typeuser_obj;
    }

    public void setTypeuser_obj(UserTypeBean typeuser_obj) {
        this.typeuser_obj = typeuser_obj;
    }

    public UserBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        this.setId(oResultSet.getInt("id"));
        this.setFirstName(oResultSet.getString("firstName"));
        this.setLastName(oResultSet.getString("lastName"));
        this.setEmail(oResultSet.getString("email"));
        this.setPhone(oResultSet.getString("phone"));
        this.setPassword(oResultSet.getString("password"));

        if (expand > 0) {
            UsertypeDao oUsertypeDao = new UsertypeDao(oConnection, "usertype");
            int idUsertype = oResultSet.getInt("usertype_id");
            this.setTypeuser_obj(oUsertypeDao.get(idUsertype, expand -1));
            this.setUsertype_id(idUsertype);
        } else {
            this.setUsertype_id(oResultSet.getInt("usertype_id"));
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "firstName,";
        strColumns += "lastName,";
        strColumns += "email,";
        strColumns += "phone,";
        strColumns += "password,";
        strColumns += "usertype_id";

        return strColumns;
    }

    public String getValues() {

        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(firstName) + ",";
        strColumns += EncodingHelper.quotate(lastName) + ",";
        strColumns += EncodingHelper.quotate(email) + ",";
        strColumns += EncodingHelper.quotate(phone) + ",";
        strColumns += EncodingHelper.quotate(password) + ",";
        strColumns += EncodingHelper.quotate(Integer.toString(usertype_id));

        return strColumns;
    }

    public String getPairs(String ob) {

        String strPairs = "";
        strPairs += "firstName =" + EncodingHelper.quotate(firstName) + ",";
        strPairs += "lastName =" + EncodingHelper.quotate(lastName) + ",";
        strPairs += "email =" + EncodingHelper.quotate(email) + ",";
        strPairs += "phone =" + EncodingHelper.quotate(phone) + ",";
        if(password != null) {
            strPairs += "password =" + EncodingHelper.quotate(password) + ",";
        }
        strPairs += "usertype_id =" + usertype_id;

        strPairs += " WHERE id=" + id;
        return strPairs;

    }

}
