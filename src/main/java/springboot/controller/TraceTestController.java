//package springboot.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.websocket.SendResult;
//
///**
// *http://wuwenliang.net/2019/02/14/SkyWalking%E5%88%86%E5%B8%83%E5%BC%8F%E9%93%BE%E8%B7%AF%E8%BF%BD%E8%B8%AA%E9%83%A8%E7%BD%B2%E5%88%9D%E6%8E%A2/#%E6%95%88%E6%9E%9C%E9%AA%8C%E8%AF%81
// * @Author: yaodao
// * @Date: 2019/2/18 20:20
// */
//@Controller
//public class TraceTestController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TraceTestController.class);
//
//    private static final String MESSAGE = "{\"code\":\"400\",\"msg\":\"FAIL\",\"desc\":\"触发限流\"}";
//
//    @ResponseBody
//    @RequestMapping("ratelimiter")
//    @RateLimiter(key = "ratedemo:1.0.0", limit = 5, expire = 10, message = MESSAGE)
//    public String sendPayment(HttpServletRequest request) throws Exception {
//
//        this.publish();
//        return "正常请求";
//    }
//
//    @Resource(name = "rocketMQSimpleProducerAgent")
//    RocketMQSimpleProducerAgent rocketMQProducerAgent;
//
//    @PostConstruct
//    void init() throws Exception {
//        rocketMQProducerAgent.init(new RocketMQProducerConfig(
//                "group-snowalker",
//                "172.30.83.100:9876"
//        )).start();
//    }
//
//    public void publish() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (int i = 0; i < 10; i++) {
//            try {
//                MessageBean msg = new MessageBean("rocketmq-simple-msg-test",
//                        "SNOWALKER_TEST",
//                        "SNOWALKER_TEST-TAG",
//                        "localhost.localdomain",
//                        "测试消息简单发送------第" + i + "次",
//                        "10",
//                        "simple-msg-test-" + i);
//                String message  = objectMapper.writeValueAsString(msg);
//
//                Message sendMessage = new Message(
//                        msg.getTopicName(), msg.getTagName(), message.getBytes());
//
//                rocketMQProducerAgent.getProducer().send(sendMessage, new SendCallback() {
//                    @Override
//                    public void onSuccess(SendResult sendResult) {
//                        LOGGER.info("消息id={}, 发送结果={}" ,sendResult.getMsgId(), sendResult.getSendStatus());
//                    }
//
//                    @Override
//                    public void onException(Throwable throwable) {
//                        LOGGER.info("消息主题={}, 消息体={}" ,sendMessage.getTopic(), new String(sendMessage.getBody()));
//                        throwable.printStackTrace();
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
