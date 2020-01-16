package springboot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yaodao
 * @Date: 2019/1/30 10:44
 */
public class SessionNotFoundException extends Exception {
    @Getter
    @Setter
    protected String message;

    public SessionNotFoundException() {
        setMessage("Session is not found!");
    }

    public SessionNotFoundException(String message) {
        this.message = message;
    }
}

