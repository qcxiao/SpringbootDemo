package springboot.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springboot.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 自定义拦截器
 */
@Slf4j
public class EncodingInterceptor implements HandlerInterceptor {

    /**
     * 请求处理之前进行调用
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        log.info("用户:{},IP:{}, 访问资源路径:{}", request.getRequestURI(), request.getRemoteUser(), request.getRemoteAddr());
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView mv)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void returnErrorResponse(HttpServletResponse response, ServerResponse result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out=null;
        try{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(result.getMsg().getBytes("UTF-8"));
            out.flush();
        } finally{
            if(out!=null){
                out.close();
            }
        }
    }
}
