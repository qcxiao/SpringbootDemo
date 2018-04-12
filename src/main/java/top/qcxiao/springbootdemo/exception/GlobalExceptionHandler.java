package top.qcxiao.springbootdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import top.qcxiao.springbootdemo.common.ResponseCode;
import top.qcxiao.springbootdemo.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public static final String GLOBAL_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response,
                               Exception e){
        e.printStackTrace();
        log.error("URL：{}，exception：{}", request.getRequestURL(), e.getMessage());
        if (isAjax(request)) {
            return ServerResponse.exception(555, e.getMessage());
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("excetion", e);
            mav.setViewName(GLOBAL_ERROR_VIEW);
            return mav;
        }
    }


    /**
     * @Description: 判断是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest httpRequest){
        return  (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( httpRequest.getHeader("X-Requested-With").toString()) );
    }

}
