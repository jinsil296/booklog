package com.booklog.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        ModelAndView mv = new ModelAndView("/error/default");
        mv.addObject("exception", exception);
        mv.addObject("request", request);
        log.error("exception >> ", exception);
        return mv;
    }
}
