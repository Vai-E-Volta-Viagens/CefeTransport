package com.cefetransport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefetransport.exception.ModalExistRegistroException;
import com.cefetransport.exception.ModalNoExistException;
import com.cefetransport.model.Modal;
import com.cefetransport.repository.ModalRepository;

@Service
public class ModalService {
    
    @Autowired
    private ModalRepository modalRepository;

    public Modal cadastrarModal(Modal modal) {

        boolean existeModalComRegistro = modalRepository.existsByRegistro(modal.getRegistro());

        if (existeModalComRegistro) {
            
            throw new ModalExistRegistroException("Já existe um modal cadastrado com registro: " + modal.getRegistro());

        } else {

            return modalRepository.save(modal);

        }

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

        // cadastrarModal(modal);

        Optional<Modal> modalAlterar = modalRepository.findById(modal.getId());

        if (modalAlterar != null) {
            
            modal.setStatus(modal.getStatus());
            modalRepository.save(modalAlterar.get());

        } else {

            throw new ModalNoExistException("Modal não encontrado com o ID: " + modal.getId());

        }

    }

}