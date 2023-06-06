package com.cefetransport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefetransport.model.Modal;
import com.cefetransport.repository.ModalRepository;

@Service
public class ModalService {
    
    @Autowired
    private ModalRepository modalRepository;

    public void cadastrarModal(Modal modal) {

        modalRepository.save(modal);

    }

}