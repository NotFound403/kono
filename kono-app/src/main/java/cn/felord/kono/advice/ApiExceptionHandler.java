package cn.felord.kono.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * 统一异常处理
 *
 * @author Dax
 * @since 13 :31  2019-04-11
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Rest<?> handle(HttpServletRequest request, BindException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return RestBody.failure(700, objectError.getDefaultMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Rest<?> handle(HttpServletRequest request, MethodArgumentNotValidException e) {
        logger(request, e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return RestBody.failure(700, objectError.getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Rest<?> handle(HttpServletRequest request, ConstraintViolationException e) {
        logger(request, e);
        Optional<ConstraintViolation<?>> first = e.getConstraintViolations().stream().findFirst();
        String message = first.isPresent() ? first.get().getMessage() : "";
        return RestBody.failure(700, message);
    }


    @ExceptionHandler(Exception.class)
    public Rest<?> handle(HttpServletRequest request, Exception e) {
        logger(request, e);
        return RestBody.failure(700, e.getMessage());
    }


    private void logger(HttpServletRequest request, Exception e) {
        String contentType = request.getHeader("Content-Type");
        log.error("统一异常处理 uri: {} content-type: {} exception: {}", request.getRequestURI(), contentType, e.toString());
    }
}
