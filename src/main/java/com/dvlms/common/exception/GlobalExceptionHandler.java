package com.dvlms.common.exception;

import com.dvlms.common.lang.Renum;
import com.dvlms.common.lang.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class GlobalExceptionHandler<T> {
    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理运行时出异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Object handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return new Result<>(e.getMessage(),400,"运行时异常");
    }

    /**
     * 处理Assert的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Object handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e.getMessage());
        return new Result<>(e.getMessage(),400,"Assert异常");
    }

    /**
     * 处理Spring Security的异常,比如没有权限，用户登录异常 401
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result handler(AccessDeniedException e) {
        log.info("security权限不足：----------------{}", e.getMessage());
        return new Result<>(Renum.WITHOUT_AUTHORITY);
    }

    /**
     * 处理实体校验的异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        log.info("实体校验异常：----------------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new Result<>(objectError.getDefaultMessage(),400,"实体校验异常");
    }
}