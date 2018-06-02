package top.qcxiao.springbootdemo.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.qcxiao.springbootdemo.springboot.pojo.Url;
import top.qcxiao.springbootdemo.springboot.pojo.User;

import javax.validation.Valid;
import javax.xml.ws.BindingType;
import java.util.List;

@RestController
@Slf4j
public class HelloController {

    @Value("${url.imageurl}")
    private String url;

    @Value("${content}")
    private String content;

    @Autowired
    private Url urlEntity;


    @RequestMapping(value = {"/hello", "/hi"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Url hello(){
        log.info(url);

        return urlEntity;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = {"/saveUser"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void saveUser(@Valid User user, BindingResult bindingResult){
        log.info(user.toString());

        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors){
                log.info("{},{}",error.getCode(), error.getDefaultMessage());
            }
        }
    }


}
