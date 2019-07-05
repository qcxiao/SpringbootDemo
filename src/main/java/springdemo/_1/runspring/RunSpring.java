package springdemo._1.runspring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * https://javadoop.com/post/spring-ioc
 * @Author: yaodao
 * @Date: 2019/6/9 23:15
 */
@Slf4j
public class RunSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springdemo.xml");
        log.info("context启动成功");
        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello world
        log.info(messageService.getMessage());
    }
}
