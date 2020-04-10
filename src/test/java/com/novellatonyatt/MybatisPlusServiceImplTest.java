package com.novellatonyatt;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.AbstractChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.novellatonyatt.constants.UserType;
import com.novellatonyatt.dao.UserRepository;
import com.novellatonyatt.model.UserModel;
import com.novellatonyatt.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-04-07 11:33
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void testSelect() {
        // get只返回一个实例
        System.out.println("===========get===========");
        UserRepository userRepository = userService.getBaseMapper();
        System.out.println("getById:" + userService.getById(1));
        System.out.println("ge11     tMap:" + userService.getMap(new QueryWrapper<UserModel>().eq("username", "15603078105")));
        System.out.println("getOne:" + userService.getOne(new QueryWrapper<UserModel>().eq("id", 1)));

        System.out.println("===========list===========");
        // list列表查询
        System.out.println("list:" + userService.list());
        System.out.println("list:" + userService.list(new QueryWrapper<UserModel>().eq("del_flag", 0)));
        System.out.println("listById:" + userService.listByIds(Arrays.asList(1, 2)));

        Map<String, Object> params = Maps.newHashMap();
        params.put("username", "15603078105");
        System.out.println("listByMap:" + userService.listByMap(params));
        System.out.println("listMaps:" + userService.listMaps(new QueryWrapper<UserModel>().eq("del_flag", 0)));
        System.out.println("listMaps:" + userService.listMaps());
        System.out.println("listObject:" + userService.listObjs()); // 只返回第一个字段
    }

    @Test
    public void testInsert() {
        UserModel userModel1 = UserModel.builder().username("test").password("test").userType(UserType.C_SIDE).build();
        UserModel userModel2 = UserModel.builder().username("test").password("test").userType(UserType.B_SIDE).build();
//        userService.save(userModel1);
//        userService.saveBatch(Lists.newArrayList(userModel1,userModel2));
//        userService.saveBatch(Lists.newArrayList(userModel1,userModel2),2);
//        userModel1.setId(20);
//        userService.saveOrUpdate(userModel1);
//        userModel2.setId(19);

//        userService.saveOrUpdateBatch(Lists.newArrayList(userModel1,userModel2));
        userService.saveOrUpdate(userModel1, new UpdateWrapper<UserModel>().eq("del_flag", 1));
    }

    @Test
    public void testDelete(){
//        userService.removeById(20);
//        userService.removeByIds(Arrays.asList(18,19));

//        Map<String , Object> params = new HashMap<>();
//        params.put("id",20);
//        params.put("username","zht");
//        userService.removeByMap(params);
//        userService.remove(new UpdateWrapper<UserModel>().eq("id","20").eq("username","zht"));
    }

    @Test
    public void testUpdate(){
        UserModel userModel = UserModel.builder().id(20).username("15603078105").build();
        UserModel userMode2 = UserModel.builder().id(21).username("15603078105").build();
//        userService.updateById(userModel);
//        userService.updateBatchById(Lists.newArrayList(userModel,userMode2));
//        userService.updateBatchById(Lists.newArrayList(userModel,userMode2),1);
//        userService.update(userModel, new UpdateWrapper<UserModel>().eq("username","20"));
//        userService.update();
    }

    @Test
    public void testPage(){
        Page<Map<String,Object>> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);
//        System.out.println(JSON.toJSONString(userService.page(page)));
//        System.out.println(JSON.toJSONString(userService.page(page,new QueryWrapper<UserModel>().like("username",156))));
//        System.out.println(userService.pageMaps(page).getRecords());
        System.out.println(userService.pageMaps(page,new QueryWrapper<UserModel>().like("username",156)).getRecords());
    }

    @Test
    public void testCount(){
//        System.out.println(userService.count());
        System.out.println(userService.count(new QueryWrapper<UserModel>().like("username",156)));
    }

    @Test
    public void testLambda(){

        System.out.println(userService.list(new QueryWrapper<UserModel>().lambda().eq(UserModel::getId , 10)));

    }

}
