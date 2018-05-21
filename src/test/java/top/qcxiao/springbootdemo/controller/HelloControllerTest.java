package top.qcxiao.springbootdemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloControllerTest {

    // mock api模拟http请求
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void before(){
        // 安装独立测试
        //mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();//指定controller类
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//泛指应用的所有上下文
    }

    @Test
    public void test() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/hello").accept(MediaType.ALL)).andDo(print());
    }
}
