package sakak.food.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final int code;
    private final HttpStatus httpStatus;

    public CustomException(CustomErrorCode errorCode) {
        super(errorCode.getMessage()); //RuntimeException 에서 상속
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }
}