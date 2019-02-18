package springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springboot.exception.IllegalPropertiesException;
import springboot.exception.NullOrEmptyException;
import springboot.exception.SessionNotFoundException;
import springboot.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 异常信息演示
 */
@RestController
@RequestMapping("err")
public class ExceptionDemoController {
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


    @RequestMapping(value = "/user")
    public ResponseEntity<?> user(HttpServletRequest request, HttpSession session) throws Exception {
        String sessionId = (String) session.getAttribute("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionNotFoundException("session失效");
        }

        String userPlainText = request.getParameter("user");
        if (StringUtils.isBlank(userPlainText) || StringUtils.equalsIgnoreCase("{}", userPlainText)) {
            throw new NullOrEmptyException();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userPlainText, User.class);

        if (StringUtils.isBlank(user.getUsername())) {
            throw new IllegalPropertiesException("username");
        }

        return ResponseEntity.ok("Successful");
    }
}
