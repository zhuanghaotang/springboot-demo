package com.novellatonyatt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.novellatonyatt.constants.UserType;
import com.novellatonyatt.dao.RoleRepository;
import com.novellatonyatt.dao.UserRepository;
import com.novellatonyatt.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/6 13:54
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusBaseMapperTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSelect() {
        // 默认为select *
        Page<UserModel> page1 = new Page();
        page1.setSize(5);
        page1.setCurrent(1);

        Page<Map<String, Object>> page2 = new Page();
        page2.setSize(5);
        page2.setCurrent(1);

        System.out.println("selectById:" + userRepository.selectById(1));
        System.out.println("selectBatchIds:" + userRepository.selectBatchIds(Arrays.asList(1, 2)));
        System.out.println("selectCount:" + userRepository.selectCount(null));
        System.out.println("selectOne:" + userRepository.selectOne(new QueryWrapper<UserModel>().eq("id", 1)));
        System.out.println("selectPage:" + userRepository.selectPage(page1, null));
        System.out.println("selectList:" + userRepository.selectList(new QueryWrapper<UserModel>().gt("id", 5)));

        Map<String, Object> columnMap = Maps.newHashMap();
        columnMap.put("id", 10);
        columnMap.put("username", null);
        columnMap.put("password", "zht191996");
        System.out.println("selectByMap:" + userRepository.selectByMap(columnMap));
        System.out.println("selectMaps:" + userRepository.selectMaps(null));
        System.out.println("selectMapsPage" + userRepository.selectMapsPage(page2, null));

    }

    @Test
    public void testInsert() {
        UserModel userModel = UserModel.builder().username("13724040555").password("test").userType(UserType.B_SIDE).build();
        System.out.println(userRepository.insert(userModel));
    }

    @Test
    public void testUpdate() {
//        UserModel userModel = UserModel.builder().username("test").password("test").userType(UserType.B_SIDE).build();
        UserModel userModel = UserModel.builder().id(10).username("testUpdate").build();
//        userRepository.updateById(userModel);
        userRepository.update(userModel, new UpdateWrapper<UserModel>().set("password", "testUpdate").eq("id", 10));
    }

    @Test
    public void testDelete() {
        userRepository.deleteById(11);
        userRepository.deleteBatchIds(Arrays.asList(1, 2));
        userRepository.delete(new UpdateWrapper<UserModel>().eq("username", "zht").eq("user_type", 1));
        Map<String, Object> params = Maps.newHashMap();
        params.put("username", "zht");
        params.put("password", null);
        userRepository.deleteByMap(params);
    }

    @Test
    public void testLambda(){
        System.out.println(userRepository.selectList(new QueryWrapper<UserModel>().lambda().eq(UserModel::getId,10)));
    }
}