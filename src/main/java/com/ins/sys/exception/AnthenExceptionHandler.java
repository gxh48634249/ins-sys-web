package com.ins.sys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
public class AnthenExceptionHandler {

    @ExceptionHandler(AuthenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handlerMyException(AuthenException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        result.put("error type", "AuthenException");
        return result;
    }
}
