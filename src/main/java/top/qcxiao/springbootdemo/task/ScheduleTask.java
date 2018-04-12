package top.qcxiao.springbootdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时执行任务
 */
@Component
@Slf4j
public class ScheduleTask {

    // http://cron.qqe2.com/ springboot不支持年的配置
    @Scheduled(fixedDelay = 600000)
    public void doTask() {
        log.info("正在自动执行");
    }
}
