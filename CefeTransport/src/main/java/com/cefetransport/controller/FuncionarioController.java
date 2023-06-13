package com.cefetransport.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cefetransport.dto.FuncionarioDto;
import com.cefetransport.exception.UsuarioNaoLogadoException;
import com.cefetransport.model.Funcionario;
import com.cefetransport.service.FuncionarioService;
import com.cefetransport.util.Util;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;

import javax.validation.Valid;

@Controller
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Getter
    Funcionario entidadeLogado;

    ModelAndView mv = new ModelAndView();
    
    @GetMapping("/")
    public ModelAndView paginaLogin(HttpSession session) {

        session.invalidate();
        mv.addObject("funcionarioDto", new FuncionarioDto());
        mv.setViewName("loginCadastro/login");
        return mv;

    }

    @GetMapping("/cadastro")
    public ModelAndView paginaCadastro() {

        mv.addObject("funcionario", new Funcionario());
        mv.setViewName("loginCadastro/cadastro");
        return mv;

    }

    @PostMapping("/cadastrarFuncionario")
    public String cadastrarFuncionario(@Valid Funcionario funcionario, BindingResult br) throws Exception {

        mv.addObject("funcionario", new Funcionario());

        if (br.hasErrors()) {
            return "loginCadastro/cadastro";
        }

        funcionarioService.salvarFuncionario(funcionario);

        return "redirect:/";

    }

    @PostMapping("/login")
    public String login(@Valid FuncionarioDto funcionarioDto, BindingResult br, HttpSession session)
            throws NoSuchAlgorithmException {
        
        System.out.println(LocalDateTime.now());

        mv.addObject("funcionario", new Funcionario());

        if(br.hasErrors()) {

            return "loginCadastro/login";
        }

        Funcionario funcionarioLogin = funcionarioService.loginFuncionario(funcionarioDto.getEmail(), Util.md5(funcionarioDto.getSenha()));

        if(funcionarioLogin == null) {

            mv.addObject("msg", "Funcionario não encontrado. Tente novamente!");

            return "loginCadastro/login";

        } else {

            session.setAttribute("funcionarioLogado", funcionarioLogin);

            entidadeLogado = funcionarioLogin;

            return "redirect:/index";

        }

    }

    @GetMapping("/index")
    public ModelAndView paginaIndex(HttpSession session) throws UsuarioNaoLogadoException {

        if(!(session.getAttribute("funcionarioLogado") != null)) {
            throw new UsuarioNaoLogadoException();
        }

        mv.addObject("funcionario", new Funcionario());
        mv.setViewName("home/index");
        return mv;

    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/";

    }

    @ExceptionHandler(UsuarioNaoLogadoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUsuarioNaoLogadoException() {
        
        return "exception/exception";

    }

}