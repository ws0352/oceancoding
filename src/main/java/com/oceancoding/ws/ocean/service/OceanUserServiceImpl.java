package com.oceancoding.ws.ocean.service;

import com.oceancoding.ws.ocean.bean.OceanUser;
import com.oceancoding.ws.ocean.dao.OceanUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OceanUserServiceImpl {

    @Autowired
    OceanUserDao oceanUserDao;

    public OceanUser getUser(Integer id){
        return oceanUserDao.getOceanUserById(id);
    }

    public void insertUser(OceanUser oceanUser){
        oceanUserDao.save(oceanUser);
    }

}
