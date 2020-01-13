package com.ytooo;

import com.ytooo.bean.Animal;
import com.ytooo.bean.Cat;
import com.ytooo.bean.People;
import com.ytooo.bean.Sensor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
class RhsTest {

    @Autowired
    private KieSession session;

    @Test
    public void update() {

        People people = new People();
        people.setName("达");
        people.setSex(0);
        people.setAge(17);
        people.setDrlType("update");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
    }

    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }
}
