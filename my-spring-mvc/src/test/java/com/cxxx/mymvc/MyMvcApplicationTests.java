package com.cxxx.mymvc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)  // Junit提供的扩展接口，这里指定使用SpringJUnit4ClassRunner作为Junit测试环境
class MyMvcApplicationTests  {

    public static void main(String[] args) {
        father father = new father();
        if (father instanceof son){
            son son= (com.cxxx.mymvc.son) father;
            System.out.println("sss");
        }

    }
}

class son extends father{

}
class  father{

}
