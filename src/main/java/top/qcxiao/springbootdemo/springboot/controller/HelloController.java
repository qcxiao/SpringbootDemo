package top.qcxiao.springbootdemo.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.qcxiao.springbootdemo.springboot.pojo.Url;

@RestController
@Slf4j
public class HelloController {

    @Value("${url.imageurl}")
    private String url;

    @Value("${content}")
    private String content;

    @Autowired
    private Url urlEntity;


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = {"/hello", "/hi"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(){
        log.info(url);

        return urlEntity.toString();
    }



}
