package com.ytooo;


import com.ytooo.bean.NumCount;
import com.ytooo.bean.People;
import com.ytooo.globa.GloableService;
import org.drools.core.marshalling.impl.ProtobufMessages;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class RhsTest {

    @Autowired
    private KieSession session;

    @Autowired
    private KieBase kieBase;

    @Test
    public void update() {

        People people = new People();
        people.setName("达");
        people.setSex(0);
        people.setAge(17);
        people.setDrlType("update");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
        System.out.println("test执行====" + people.toString());
    }

    @Test
    public void impot() {
        People people = new People();
        people.setDrlType("impot");
        session.insert(people);//插入
        session.fireAllRules();//执行规则
    }

    @Test
    public void global() {
        People people = new People();
        people.setDrlType("global");
        session.insert(people);//插入
        //配置全局变量
        List<People> list = new ArrayList<>();
        NumCount numCount = new NumCount();
        GloableService service = new GloableService();
        session.setGlobal("list", list);
        session.setGlobal("numCount", numCount);
        session.setGlobal("service", service);
        session.fireAllRules();//执行规则
        //取出全局变量值
        System.out.println(session.getGlobal("list").toString());
        System.out.println((session.getGlobal("numCount")).toString());

    }

    @Test
    public void query() {
        session.insert(new People(1, "春", "query"));
        session.insert(new People(2, "夏", "query"));
        session.insert(new People(3, "秋", "query"));
        session.insert(new People(4, "冬", "query"));
        session.insert(new People(5, "达", "query"));
        QueryResults results = session.getQueryResults("queryPeople", "达", 5);
        for (QueryResultsRow row : results) {
            People p = (People) row.get("$p");
            System.out.println(p);
        }
    }
    @Test
    public void declare() throws IllegalAccessException, InstantiationException {

        FactType factType = kieBase.getFactType("com.ytooo.declar","Love");
        Object obj = factType.newInstance();
        factType.set(obj,"feel","sad");
        factType.set(obj,"continued","永远");
        session.insert(obj);
        session.fireAllRules();
    }

    @AfterEach
    public void runDispose() {
        session.dispose();//释放资源
    }
}
