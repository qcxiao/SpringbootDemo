package top.qcxiao.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常信息演示
 */
@Controller
@RequestMapping("err")
public class ExceptionDemo {
    /**
     * 页面异常
     * @return
     */
    @RequestMapping(value = "/error")
    public String error(){
        int i = 1/0;
        return "templates/error.html";
    }

    /**
     * ajax异常
     */
    @RequestMapping(value = "/ajaxerror")
    @ResponseBody
    public String ajaxerror(){
        int i = 1/0;
        return null;
    }
}
