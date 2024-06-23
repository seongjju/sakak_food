package sakak.food.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ErrorResponse {
    private int code;
    private int httpStatus;
    private String error;
    private String message;

    public ErrorResponse(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
    }
}