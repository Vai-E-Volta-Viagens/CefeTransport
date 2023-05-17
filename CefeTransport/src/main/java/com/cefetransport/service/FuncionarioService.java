package com.cefetransport.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefetransport.exception.CriptoExistException;
import com.cefetransport.exception.EmailExistException;
import com.cefetransport.model.Funcionario;
import com.cefetransport.repository.FuncionarioRepository;
import com.cefetransport.util.Util;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void salvarFuncionario(Funcionario funcionario) throws Exception {

        try {
            
            if(funcionarioRepository.findByEmail(funcionario.getEmail()) != null) {

                throw new EmailExistException("Já existe um usuário cadastrado com o e-mail: " + funcionario.getEmail());

            }

            funcionario.setSenha(Util.md5(funcionario.getSenha()));

        } catch (NoSuchAlgorithmException e) {
            
            throw new CriptoExistException("Erro na criptografia da senha");

        }

        funcionarioRepository.save(funcionario);

    }

    public Funcionario loginFuncionario(String email, String senha) throws ServiceException {

        Funcionario funcionarioLogin = funcionarioRepository.buscarLogin(email, senha);
        return funcionarioLogin;

    }

}