package com.cefetransport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefetransport.model.Modal;
import com.cefetransport.repository.ModalRepository;

@Service
public class ModalService {
    
    @Autowired
    private ModalRepository modalRepository;

    public Modal cadastrarModal(Modal modal) {

        return modalRepository.save(modal);

    }

    public void modaisCadastrados() {

        modalRepository.findAll();

    }

    public Modal buscarModalPorId(Long id) {

        return modalRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Dado de cadastro não encontrado"));

    }

    public void alterarModal(Modal modal) {

        cadastrarModal(modal);

    }

    public void deletarModal(Long id) {

        modalRepository.deleteById(id);

    }

}