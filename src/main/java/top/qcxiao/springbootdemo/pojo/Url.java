package top.qcxiao.springbootdemo.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "url")
@Data
@Component
public class Url {
    private String imageurl;
    private String fileurl;
}
