package com.cefetransport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cefetransport.model.Modal;

@Repository
public interface ModalRepository extends JpaRepository<Modal, Long> {
    
    boolean existsByRegistro(String registro);

}