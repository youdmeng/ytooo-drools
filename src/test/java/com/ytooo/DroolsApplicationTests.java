package com.ytooo;

import com.ytooo.bean.Animal;
import com.ytooo.bean.Cat;
import com.ytooo.bean.People;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DroolsApplicationTests {

    @Autowired
    private KieSession session;

    @Test
    public void people() {

        People people = new People();
        people.setName("达");
        people.setSex(1);
        people.setDrlType("people");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
    }
    @Test
    public void cat() {
        Cat cat = new Cat();
        cat.setName("金");
        cat.setSex(1);
        session.insert(cat);//插入
        session.fireAllRules();//执行规则
    }
    @Test
    public void from() {
        People p1 = new People(1,"达","from");
        People p2 = new People(0,"秋","from");
        People p3 = new People(3,"金","from");
        Animal animal = new Animal();
        animal.setPeoples(new ArrayList<>());
        animal.getPeoples().add(p1);
        animal.getPeoples().add(p2);
        animal.getPeoples().add(p3);
        session.insert(animal);//插入
        session.fireAllRules();//执行规则
    }
   @Test
    public void collect() {
       session.insert(new People(1, "达","collect"));
       session.insert(new People(0, "秋","collect"));
       session.insert(new People(0, "春","collect"));
       session.insert(new People(1, "夏","collect"));
       session.insert(new People(0, "冬","collect"));
       session.insert(new People(3, "金","collect"));

        session.fireAllRules();//执行规则
    }

    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }
}
