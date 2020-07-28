package cn.felord.kono.advice;


import com.daidai.bcrecyling.base.data.Rest;
import com.daidai.bcrecyling.base.data.RestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
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
 * weChat 统一异常处理
 *
 * @author Dax
 * @since 13 :31  2019-04-11
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.daidai.bcrecyling.api.*")
public class ApiExceptionHandler {

    /**
     * Handle rest.
     *
     * @param request the request
     * @param e       the e
     * @return the rest
     */
    @ExceptionHandler(Exception.class)
    public Rest<?> handle(HttpServletRequest request, Exception e) {
        String contentType = request.getHeader("Content-Type");
        log.error("统一异常处理 uri: {} content-type: {} exception: {}", request.getRequestURI(), contentType, e.toString());
        if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            return RestBody.failure(700, objectError.getDefaultMessage());
        }

        if (e instanceof AccessDeniedException) {
            return RestBody.failure(403, "访问未授权");
        }

        if (e instanceof HttpMessageNotReadableException) {
            return RestBody.failure(700, "请求参数非法！");
        }

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
            ObjectError objectError = allErrors.get(0);
            return RestBody.failure(700, objectError.getDefaultMessage());
        }

        if (e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException) e;
            Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
                String message = first.isPresent()?first.get().getMessage():"";

            return RestBody.failure(700, message);
        }


        if (e instanceof BusinessException) {
            return RestBody.failure(700, e.getMessage());
        }

        return RestBody.failure(700, "系统出错啦");
    }

}
