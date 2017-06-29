package com.service.impl;

import com.dao.SYSUserDao;
import com.model.entity.SYSUser;
import com.service.ISYSUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by CDC3715 on 2017/6/15.
 */
@Component
public class SYSUserServiceImpl implements ISYSUserService {

    @Autowired
    private SYSUserDao sysUserDao;

    @Override
    public List<SYSUser> getAll() {
        return sysUserDao.getAll();
    }

    @Override
    public SYSUser getById(String id) {
        return sysUserDao.getById(id);
    }

    @Override
    public void insert(SYSUser user) {
        sysUserDao.insert(user);
    }

    @Override
    public void update(SYSUser user) {
        sysUserDao.update(user);
    }

    @Override
    public void delete(String id) {
        sysUserDao.delete(id);
    }
}
