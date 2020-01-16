package springboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
        /**
         * 安装独立测试
         */
        //指定controller类
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        //泛指应用的所有上下文，推荐使用
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void hello() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/hello").accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }

    @Test
    public void saveUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                .param("username", "h")).andDo(print());

    }
}
