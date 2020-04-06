package com.novellatonyatt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.novellatonyatt.model.RoleModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 22:47
 * @Description:
 */
public interface RoleRepository extends BaseMapper<RoleModel> {

    @Select("SELECT c.* FROM USER AS a INNER JOIN user_role AS b ON a.id = b.`user_id` INNER JOIN role AS c ON b.`role_id` =  c.id WHERE a.username = #{username}")
    List<RoleModel> getRoleListByUsername(String username);

}
