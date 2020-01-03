package com.oceancoding.ws.ocean.dao;

import com.oceancoding.ws.ocean.bean.OceanUser;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OceanUserDao extends JpaRepository<OceanUser, Integer> {

    OceanUser findUserById(String Id);

}
