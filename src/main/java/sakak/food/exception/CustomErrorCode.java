package sakak.food.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    FOOD_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Food not found"),
    INVALID_INPUT(1002, HttpStatus.BAD_REQUEST, "This is Invalid input"),
    DUPLICATE_FOOD(1003, HttpStatus.CONFLICT, "Duplicate food exists");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    CustomErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
