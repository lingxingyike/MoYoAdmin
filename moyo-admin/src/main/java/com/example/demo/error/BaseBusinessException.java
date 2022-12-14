package com.example.demo.error;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: cch
 * @Date: 2020/9/21 16:03
 * @Description: 自定义异常返回结果实体类
 */
@Data
@NoArgsConstructor
public class BaseBusinessException extends RuntimeException{

    private BaseError error = DefaultError.SYSTEM_INTERNAL_ERROR;
    private String extMessage = null;

    public BaseBusinessException(String message) {
        super(message);
        this.extMessage = message;
    }
    public BaseBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.extMessage = message;
    }
    public BaseBusinessException(Throwable cause) {
        super(cause);
    }
    public BaseBusinessException(BaseError error) {
        this.error = error;
    }
    public BaseBusinessException(String message, BaseError error) {
        super(message);
        this.extMessage = message;
        this.error = error;
    }
    public BaseBusinessException(String message, Throwable cause, BaseError error) {
        super(message, cause);
        this.extMessage = message;
        this.error = error;
    }
    public BaseBusinessException(Throwable cause, BaseError error) {
        super(cause);
        this.error = error;
    }
}

