package com.cefetransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cefetransport.model.Modal;
import com.cefetransport.service.ModalService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/modal")
public class ModalController {
    
    ModelAndView mv = new ModelAndView();

    @Autowired
    private ModalService modalService;

    @GetMapping("/cadastrar")
    public ModelAndView paginaCadastrarModal() {

        mv.setViewName("home/cadastrarModal");
        mv.addObject("modal", new Modal());

        return mv;

    }

    @PostMapping("/cadastrarModal")
    public ModelAndView cadastrarModel(@Valid Modal modal, BindingResult br) {

        mv.addObject("modal", new Modal());

        if (br.hasErrors()) {

            mv.setViewName("home/cadastrarModal");

        }

        modalService.cadastrarModal(modal);

        mv.setViewName("redirect:/index");

        return mv;

    }

}