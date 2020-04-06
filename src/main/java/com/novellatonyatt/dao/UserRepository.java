package com.novellatonyatt.dao;

import com.novellatonyatt.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:23
 * @Description:
 */
public interface UserRepository {

    String TABLE_NAME = "user";

    String QUERY_COLUMN = "id , username , password ,user_type";

    String INSERT_COLUMN = "username , password , user_type";

    @Insert("INSERT "+TABLE_NAME + "(" + INSERT_COLUMN + ") VALUE( #{username} , MD5(#{password}) ,#{userType, typeHandler=com.novellatonyatt.constants.EnumTypeHandler })")
    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="id")
    void register(UserModel userModel);

    @Select("SELECT " + QUERY_COLUMN + " FROM "+TABLE_NAME+" WHERE username = #{username} and del_flag = 0")
    UserModel getUserByUsername(String username);

    @Select("SELECT " + QUERY_COLUMN + " FROM "+TABLE_NAME+" WHERE del_flag = 0")
    @Results({@Result(property="userType", column="user_type", typeHandler=com.novellatonyatt.constants.EnumTypeHandler.class)})
    List<UserModel> getUserList();

}
