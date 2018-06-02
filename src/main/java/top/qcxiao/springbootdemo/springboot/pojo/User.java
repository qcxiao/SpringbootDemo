package top.qcxiao.springbootdemo.springboot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class User {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    @Min(value = 5, message = "用户名长度不能小于5位")
    @Max(value = 8, message = "用户名长度不能小于5位")
    private String username;
    private String userpwd;
}
