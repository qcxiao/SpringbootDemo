package springboot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yaodao
 * @Date: 2019/1/30 10:31
 */
public class IllegalPropertiesException extends Exception {
    @Getter
    @Setter
    protected String message;

    public IllegalPropertiesException() {
        setMessage("Prop is illegal!");
    }

    public IllegalPropertiesException(String message) {
        this.message = message;
        setMessage(String.format("Prop: %s is illegal!", message));
    }
}

