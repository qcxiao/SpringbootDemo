package springboot.controller.jvm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 死循环
 * @Author: yaodao
 * @Date: 2018/11/6 16:09
 */

@RestController
@Slf4j
public class DeadLoopController {

    String str;

    @RequestMapping("deadLoop")
    public void deadLoop() {
        log.warn("enter into deadLoop...");
        while (true){
            str = new String();
        }
    }
}
