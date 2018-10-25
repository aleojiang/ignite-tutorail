package pjiang.tutorial.datajpa.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  15:36 2018/10/24.
 */
@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
