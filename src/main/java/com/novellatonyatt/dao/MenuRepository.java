package com.novellatonyatt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.novellatonyatt.model.MenuModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 22:47
 * @Description:
 */
public interface MenuRepository extends BaseMapper<MenuModel> {

    @Select("SELECT a.* FROM menu AS a INNER JOIN role_menu AS b ON a.id = b.`menu_id`\n" +
            "INNER JOIN role AS c ON c.id = b.`role_id` INNER JOIN user_role AS d ON c.id = d.`role_id` \n" +
            "INNER JOIN USER AS e ON e.id = d.`user_id` WHERE e.`username` = #{username} GROUP BY a.id")
    List<MenuModel> getMenuByUsername(String username);

}
