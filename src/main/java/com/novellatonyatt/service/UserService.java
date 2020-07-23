package com.novellatonyatt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.novellatonyatt.constants.PageVO;
import com.novellatonyatt.dao.UserRepository;
import com.novellatonyatt.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Zhuang HaoTang
 * @Date: 2020/4/5 15:25
 * @Description:
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserRepository, UserModel> {

    @Autowired
    private UserRepository userRepository;

    public PageVO<UserModel> getUserList(Integer pageNo, Integer pageSize) {
        Page<UserModel> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        userRepository.selectPage(page, null);
        return new PageVO<>((int) page.getTotal(), (int) page.getSize(), (int) page.getPages(), (int) page.getCurrent(), page.getRecords());
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.selectOne(new QueryWrapper<UserModel>().eq("username", username));
    }

    public void register(UserModel userModel) {
        userRepository.insert(userModel);
    }

}
