package com.springmvc.auth.dao;

import com.springmvc.auth.common.BaseDao;
import com.springmvc.auth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by dello on 2016/7/27.
 * UserDao实体类
 */
@Repository
public class UserDao extends BaseDao {

    private Logger logger= LoggerFactory.getLogger(UserDao.class);

    /**
     * UserRowMapper User对象的映射类
     */
    private class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user=new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPwd(resultSet.getString("pwd"));
            return user;
        }
    }

    /**
     *  根据用户名密码查询用户
     * @param name  用户名
     * @param password 密码
     * @return 返回的实体
     */
    public User getUser(String name,String password){
        String sql="select * from auth_user where name =? and pwd =?";

        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{name, password}, new UserRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存User对象
     * @param user user的实体类
     */
    public void saveUser(User user){
        String sql="insert into auth_user(name,pwd) values(?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getPwd());
    }

    /**
     * 根据id删除记录
     * @param id 要删除的ID
     */
    public void deleteById(Long id){
        String sql="delete from auth_user where id= ?";
        jdbcTemplate.update(sql,id);
    }

    /**
     *  更新User对象
     * @param user  更新的user对象
     */
    public void update(User user){
        String sql="update auth_user set name=?,pwd=? where id= ?";
        jdbcTemplate.update(sql,user.getName(),user.getPwd(),user.getId());
    }

    /**
     * 查找User对象
     * @param id 要查找的User对象的id
     * @return  返回User对象
     */
    public User findById(Long id){
        String sql="select * from auth_user where id =?";
        try {
            return jdbcTemplate.queryForObject(sql,new Object[]{id},new UserRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据集合ids查找User对象
     * @param ids   集合ids元素
     * @return  返回User集合对象
     */
    public Collection<User> findByIds(Collection<Long> ids){
        StringBuilder sb=new StringBuilder("select * from auth_user where id in (");
        ids.forEach((id) -> sb.append(id).append(","));
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        logger.info("findByIds sql"+sb);
        return jdbcTemplate.query(sb.toString(),new UserRowMapper());
    }
}
