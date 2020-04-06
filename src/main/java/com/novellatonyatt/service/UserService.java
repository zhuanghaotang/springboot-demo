package com.novellatonyatt.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PageVO<UserModel> getUserList(Integer pageNo, Integer pageSize) {
        Page<UserModel> page = PageHelper.startPage(pageNo, pageSize);
        userRepository.getUserList();
        return new PageVO<>((int) page.getTotal(), page.getPageSize(), page.getPages(), page.getPageNum(), page.getResult());
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void register(UserModel userModel) {
        userRepository.register(userModel);
    }

}
