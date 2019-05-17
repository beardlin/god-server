package net.lantrack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author : lihuadong@lantrack.net
 * @description :
 * @date : 2017/11/2 15:48
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = { "classpath*:spring/spring-core.xml,classpath*:spring/spring-shiro-cas.xml"})
public class BaseJunitTest {
    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
    }
    @Test
    public  void test(){
        System.out.println("测试初始化。。。。。。。。");
    }
}


