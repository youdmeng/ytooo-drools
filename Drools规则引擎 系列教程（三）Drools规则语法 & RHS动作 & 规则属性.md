---
title: Drools规则引擎 系列教程（三）Drools规则语法 & RHS 动作
date: 2020-01-08 07:12:40
tags:
  - Spring Boot
  - drools
  - java
  - 规则引擎
categories:
  - java后端
keywords:
  - Spring Boot
  - SpringBoot
  - 规则引擎
  - drools
  - drools规则引擎 系列教程
  - RHS 动作
  - Drools规则语法
---
[Drools规则引擎 系列教程（一）SpringBoot整合 & 快速集成上手](https://www.ytooo.ml/34871.html)

[Drools规则引擎 系列教程（二）Drools规则语法 & LHS 条件](https://www.ytooo.ml/38470.html#more)


教程代码已提交到[ytooo-drools](https://github.com/youdmeng/ytooo-drools)，欢迎star

### 1. RHS动作
&emsp;&emsp;RHS 部分定义了当LHS满足是要进行的操作,规则操作的主要目的是在Drools引擎的工作内存中插入，删除或修改数据。RHS中可以编写代码，可以使用LHS 部分当中定义的绑定变量名以及drl头部定义的全局变量。在RHS当中如果需要条件判断,那么请重新考虑将其放在 LHS 当中,否则就违背了使用规则的初衷。
#### 1.2. 主要操作

| 动作 | 描述 |
| :----: | :----: |
| set | 给属性赋值 |
| modify | 将改变通知drolls引擎 |
| update | 将改变通知drolls引擎 |
| insert | 将新实事插入到drools引擎的工作 |
| insertLogical | insert增强版，需声明撤回事件，或待不在匹配条件后自动撤回 |
| delete | 删除实事 |

#### 1.3. Update
&emsp;&emsp;Update用于将数据的更改更新到引擎，并通知引擎重新匹配该事实
```drools
package com.ytooo.updat
dialect "java"
import com.ytooo.bean.People

rule 'update1'
    when
       $p : People(drlType == "update" && sex == 0)
    then
        System.out.println("update1执行====" + $p);
        $p.setSex(1);
        update($p)
    end
rule 'update2'
    when
       $p : People(drlType == "update" && sex == 1)
    then
        System.out.println("update2执行====" + $p);
    end
```
```java
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
```
当规则1执行后，通过update($p)改变对象值，并重新触发规则2
```text
update1执行====People(sex=0, name=达, age=17, drlType=update)
update2执行====People(sex=1, name=达, age=17, drlType=update)
```
##### modify 用法与 update类似
```drools
rule 'modify'
    when
      $p : People(drlType == "update" && sex == 1)
    then
       System.out.println("update3执行====" + $p);
       modify($p){
          setSex(-1)
       }
    end
```
```text
update1执行====People(sex=0, name=达, age=17, drlType=update)
update2执行====People(sex=1, name=达, age=17, drlType=update)
update3执行====People(sex=1, name=达, age=17, drlType=update)
```
##### 特别注意，当在then中改变变量属性值，但不使用update语句时，在调用测试方法中，打印people对象时，值已经被改变，只是不出发规则执行
&emsp;&emsp;修改后的测试代码：
```java
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
```
&emsp;&emsp;修改后的规则：
```drools
package com.ytooo.updat
dialect "java"
import com.ytooo.bean.People

rule 'update1'
    when
       $p : People(drlType == "update" && sex == 0)
    then
        System.out.println("update1执行====" + $p);
        $p.setSex(1);
//        update($p)
    end
rule 'update2'
    when
       $p : People(drlType == "update" && sex == 1)
    then
        System.out.println("update2执行====" + $p);
    end
rule 'modify'
    when
      $p : People(drlType == "update" && sex == 1)
    then
       System.out.println("update3执行====" + $p);
       modify($p){
         setSex(-1)
       }
    end
```
&emsp;&emsp;执行结果：
```text
update1执行====People(sex=0, name=达, age=17, drlType=update)
test执行====People(sex=1, name=达, age=17, drlType=update)
```
### 2. drools API
<div id="global_detail"></div>

#### 2.1 Global 全局变量






































教程代码已提交到[ytooo-drools](https://github.com/youdmeng/ytooo-drools)，欢迎star

[Drools规则引擎 系列教程（二）Drools规则语法 & LHS 条件](https://www.ytooo.ml/38470.html#more)

