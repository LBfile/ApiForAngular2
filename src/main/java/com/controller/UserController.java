package com.controller;

import org.codehaus.jackson.map.util.JSONPObject;
import com.model.entity.SYSUser;
import com.service.impl.SYSUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CDC3715 on 2017/6/14.
 */
@RestController
public class UserController {

    @Autowired
    private SYSUserServiceImpl sysUserService;

    @RequestMapping("/getAll")
    public Object getAll(@PathVariable("str") String str) {
        System.out.print(str);
        List<SYSUser> users=sysUserService.getAll();
        return users;
    }

    @RequestMapping("/getById/{id}")
    public SYSUser getById(@PathVariable("id") String id) {
        SYSUser user=sysUserService.getById(id);
        return user;
    }

    @RequestMapping("/insert")
    public void save(SYSUser user) {
        sysUserService.insert(user);
    }

    @RequestMapping(value="update")
    public void update(SYSUser user) {
        sysUserService.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        sysUserService.delete(id);
    }
}
