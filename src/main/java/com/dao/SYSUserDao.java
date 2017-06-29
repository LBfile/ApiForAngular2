package com.dao;

import com.model.entity.SYSUser;

import java.util.List;

/**
 * Created by CDC3715 on 2017/6/14.
 */
public interface SYSUserDao {
    List<SYSUser> getAll();

    SYSUser getById(String id);

    void insert(SYSUser user);

    void update(SYSUser user);

    void delete(String id);
}
