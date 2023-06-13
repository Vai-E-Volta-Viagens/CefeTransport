package com.cefetransport.repository;

// import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cefetransport.model.Atividades;

public interface AtividadeRepository extends JpaRepository<Atividades, Long> {
    
    @Query(nativeQuery = true, value = """
            SELECT atividades.id
            FROM atividades
            WHERE atividades.modal_id = :id
            """)
    public Long buscarIdAtividadePorIdModal(Long id);

}