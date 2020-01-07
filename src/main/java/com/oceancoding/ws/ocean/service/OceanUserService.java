package com.oceancoding.ws.ocean.service;

import com.oceancoding.ws.ocean.bean.OceanUser;

public interface OceanUserService {
    public OceanUser getUser(Integer id);
    public void insertUser(OceanUser oceanUser);
}
