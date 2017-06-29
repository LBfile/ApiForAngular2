package com.service;

import com.model.entity.SYSUser;

import java.util.List;

/**
 * Created by CDC3715 on 2017/6/15.
 */
public interface ISYSUserService {

    List<SYSUser> getAll();

    SYSUser getById(String id);

    void insert(SYSUser user);

    void update(SYSUser user);

    void delete(String id);
}
