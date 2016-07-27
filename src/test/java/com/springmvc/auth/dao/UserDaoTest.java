package com.springmvc.auth.dao;

import com.springmvc.auth.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dello on 2016/7/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationContext.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testSaveUser() throws Exception {
        userDao.saveUser(new User("caozhiduan","123456"));
    }

    @Test
    public void testDeleteById() throws Exception {
        userDao.deleteById(Long.valueOf(1));
    }

    @Test
    public void testUpdate() throws Exception {
        userDao.update(new User((long)2,"tanjie","123"));
    }

    @Test
    public void testFindById() throws Exception {
        User byId = userDao.findById(2L);
        System.out.println(byId);
    }

    @Test
    public void testFindByIds() throws Exception {
        List<User> byIds = (List<User>) userDao.findByIds(Arrays.asList(2L,3L));
        for(User user:byIds){
            System.out.println(user);
        }
    }
}
