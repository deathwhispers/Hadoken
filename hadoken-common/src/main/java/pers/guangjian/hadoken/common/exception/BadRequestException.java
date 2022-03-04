package pers.guangjian.hadoken.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Author: yanggj
 * @Description: 统一异常处理
 * @Date: 2022/03/03 11:29
 * @Version: 1.0.0
 */
public class BadRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}