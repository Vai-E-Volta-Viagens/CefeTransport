package com.cefetransport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cefetransport.model.Modal;

@Repository
public interface ModalRepository extends JpaRepository<Modal, Long> {
    
    boolean existsByRegistro(String registro);

    @Query(nativeQuery = true, value = """
            SELECT registro FROM modais
            """)
    List<String> buscarRegistrosModais();

    @Query(nativeQuery = true, value = """
            SELECT vagas FROM modais
            """)
    List<Integer> buscarVagasModais();

    @Query(nativeQuery = true, value = """
            SELECT status FROM modais
            WHERE registro = :registro
            """)
    String buscarStatusDoModalPorRegistro(String registro);

    @Query(nativeQuery = true, value = """
                    SELECT proprietaria_id FROM modais
                    WHERE registro = :registro
                    """)
    Long buscarProprietariaPorRegistro(String registro);

}