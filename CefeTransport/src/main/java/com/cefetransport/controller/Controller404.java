package com.cefetransport.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class Controller404 implements ErrorController {

    ModelAndView mv = new ModelAndView();

    @GetMapping("/error")
    public ModelAndView paginaError() {
        
        mv.setViewName("exception/exception404");

        return mv;

    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(Exception e) {
        
        mv.setViewName("/error");
        mv.addObject("errorMessage", "Página não encontrada");
        return mv;
    }

}