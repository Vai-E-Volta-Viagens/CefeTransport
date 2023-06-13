package com.cefetransport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefetransport.model.Atividades;
import com.cefetransport.model.Funcionario;
import com.cefetransport.model.Modal;
import com.cefetransport.repository.AtividadeRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class AtividadeService {
    
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void salvarAtividade(Funcionario funcionario, Modal modal, String descricaoAtividade) {

        Atividades atividade = new Atividades();

        Atividades atividades = entityManager.merge(atividade);

        Funcionario funcionarioAtual = entityManager.merge(funcionario);

        atividades.setFuncionario(funcionarioAtual);
        atividades.setModal(modal);
        atividades.setAtividade(descricaoAtividade);

        atividadeRepository.save(atividades);

    }

    public void deletarAtividade(Long id) {

        atividadeRepository.deleteById(id);

    }

    public Long buscarAtividadePorId(Long id) {
        
        return atividadeRepository.buscarIdAtividadePorIdModal(id);

    }

}