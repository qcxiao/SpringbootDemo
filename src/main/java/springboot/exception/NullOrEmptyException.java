package springboot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yaodao
 * @Date: 2019/1/30 10:44
 */
public class NullOrEmptyException extends Exception {
    @Getter
    @Setter
    protected String message;

    public NullOrEmptyException() {
        setMessage("Parameter is null or empty!");
    }

    public NullOrEmptyException(String message) {
        this.message = message;
    }
}

