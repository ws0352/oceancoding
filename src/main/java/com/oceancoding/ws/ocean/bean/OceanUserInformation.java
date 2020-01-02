package com.oceancoding.ws.ocean.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
    @Getter
    private Date gmtCreate;

    @LastModifiedDate
    @Column(name = "gmt_modify")
    @Getter
    private Date gmtModify;

    @Column(name = "user_name")
    @Setter
    @Getter
    private String userName;

    @Column(name = "phone")
    @Setter
    @Getter
    private String phone;

    @Column(name = "email")
    @Setter
    @Getter
    private String email;

    @Column(name = "role")
    @Setter
    @Getter
    private int role;

    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "sex")
    @Setter
    @Getter
    private String sex;

    @Column(name = "brith_date")
    @Setter
    @Getter
    private Date brithDate;
}
