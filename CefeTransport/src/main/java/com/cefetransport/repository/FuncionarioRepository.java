package com.cefetransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cefetransport.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("select e from Funcionario e where e.email = :email")
    public Funcionario findByEmail(String email);
    
    @Query("select u from Funcionario u where u.email = :email and u.senha = :senha")
    public Funcionario buscarLogin(String email, String senha);

}