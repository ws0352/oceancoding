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
public class OceanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;

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

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Column(name = "password")
    @Setter
    private String password;
}
