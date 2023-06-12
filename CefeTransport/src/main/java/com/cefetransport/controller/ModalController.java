package com.cefetransport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cefetransport.exception.UsuarioNaoLogadoException;
import com.cefetransport.model.Modal;
import com.cefetransport.repository.ModalRepository;
import com.cefetransport.service.ModalService;

import jakarta.servlet.http.HttpSession;

import javax.validation.Valid;

@Controller
@RequestMapping("/modal")
public class ModalController {
    
    ModelAndView mv = new ModelAndView();

    @Autowired
    private ModalService modalService;

    @Autowired
    private ModalRepository modalRepository;

    @GetMapping("/cadastrar")
    public ModelAndView paginaCadastrarModal(HttpSession session) throws UsuarioNaoLogadoException {

        if (!(session.getAttribute("funcionarioLogado") != null)) {
            
            throw new UsuarioNaoLogadoException();

        }

        mv.addObject("modal", new Modal());
        mv.setViewName("home/cadastrarModal");

        return mv;

    }

    @PostMapping("/cadastrarModal")
    public String cadastrarModel(@Valid Modal modal, BindingResult br) throws Exception {

        mv.addObject("modal", new Modal());

        if (br.hasErrors()) {

            return "home/cadastrarModal";

        } else {

            modalService.cadastrarModal(modal);
            return "redirect:/modal/modaisCadastrados";

        }

    }
    
    @GetMapping("/modaisCadastrados")
    public ModelAndView modaisCadastrados(HttpSession session) throws UsuarioNaoLogadoException {

        if (!(session.getAttribute("funcionarioLogado") != null)) {

            throw new UsuarioNaoLogadoException();

        }

        mv.addObject("modalList", modalRepository.findAll());
        mv.setViewName("home/modaisCadastrados");

        return mv;

    }

    @GetMapping("/deletar/{id}")
    public String deletarModal(@PathVariable("id") Long id) {

        modalRepository.deleteById(id);

        return "redirect:/modal/modaisCadastrados";

    }

    @ExceptionHandler(UsuarioNaoLogadoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUsuarioNaoLogadoException() {

        return "exception/exception";

    }

}