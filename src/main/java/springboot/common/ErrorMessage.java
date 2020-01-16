package springboot.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: yaodao
 * @Date: 2019/1/30 10:25
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ErrorMessage<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;

    private Integer code;
    private String message;
    private String url;
    private T data;
}
