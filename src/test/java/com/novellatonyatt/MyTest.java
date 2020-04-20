package com.novellatonyatt;

import com.novellatonyatt.config.MyConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-04-20 16:42
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Autowired
    private MyConfig myConfig;

    @Test
    public void test(){
        System.out.println(myConfig);
    }

}
