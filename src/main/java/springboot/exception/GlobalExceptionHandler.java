package springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springboot.common.ErrorMessage;
import springboot.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseBody
    public ErrorMessage<String> sessionNotFoundExceptionHandler(HttpServletRequest request, SessionNotFoundException exception) throws Exception {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    @ExceptionHandler(NullOrEmptyException.class)
    @ResponseBody
    public ErrorMessage<String> nullOrEmptyExceptionHandler(HttpServletRequest request, NullOrEmptyException exception) throws Exception {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    @ExceptionHandler(IllegalPropertiesException.class)
    @ResponseBody
    public ErrorMessage<String> illegalPropExceptionHandler(HttpServletRequest request, IllegalPropertiesException exception) throws Exception {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage<String> exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        return handleErrorInfo(request, exception.getMessage(), exception);
    }

    private ErrorMessage<String> handleErrorInfo(HttpServletRequest request, String message, Exception exception) {
        ErrorMessage<String> errorMessage = new ErrorMessage<>();
        errorMessage.setMessage(message);
        errorMessage.setCode(ErrorMessage.ERROR);
        errorMessage.setData(message);
        errorMessage.setUrl(request.getRequestURL().toString());
        return errorMessage;
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
