package com.oceancoding.ws.ocean.bean;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class OceanUserInformation {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer Id;

    @CreatedDate
    @Column(name = "gmt_create")
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private int role;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "brith_date")
    private Date brithDate;

    public OceanUserInformation(){

    }
    public OceanUserInformation(Integer id, Date gmtCreate, Date gmtModify, String userName, String phone, String email, int role, String name, String sex, Date brithDate) {
        Id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.name = name;
        this.sex = sex;
        this.brithDate = brithDate;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }

    @Override
    public String toString() {
        return "OceanUserInformation{" +
                "Id=" + Id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", brithDate=" + brithDate +
                '}';
    }
}
